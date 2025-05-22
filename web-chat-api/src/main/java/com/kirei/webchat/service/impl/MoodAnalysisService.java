package com.kirei.webchat.service.impl;

import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.stereotype.Service;

@Service
public class MoodAnalysisService {

    private final StanfordCoreNLP pipeline;

    public MoodAnalysisService() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public int analyzeMood(String text) {
        Annotation annotation = pipeline.process(text);
        int mainSentiment = 0;
        int sentenceCount = 0;

        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            mainSentiment += convertSentiment(sentiment);
            sentenceCount++;
        }

        return mainSentiment / (sentenceCount == 0 ? 1 : sentenceCount);
    }

    private int convertSentiment(String sentiment) {
        switch (sentiment.toLowerCase()) {
            case "very positive":
                return 4;
            case "positive":
                return 3;
            case "neutral":
                return 2;
            case "negative":
                return 1;
            case "very negative":
                return 0;
            default:
                return 2;
        }
    }
}

