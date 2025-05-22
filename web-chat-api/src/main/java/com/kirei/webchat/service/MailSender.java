package com.kirei.webchat.service;

public interface MailSender {
    boolean send(String emailTo, String subject, String message);
}
