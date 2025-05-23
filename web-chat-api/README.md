# "MoodChat" Social Network API
## - Brief -
>## Is the backend part of the project, a RESTful application that aims to fully implement the functionality available for messaging.
## - Features -
- ### ⚙️ Registration / authentication & authorization
> ##### Each user can go to the home page of the application, log in to an existing account, or register if they don't have an account. 
> ##### A non-authenticated user will not be granted access to the basic functionality.
- ### 👤 Scrolling through the list of users
> ##### The user has the ability to view the existing users and to write (create a chat room) to them by clickable button.
- ### 🧑‍💻 Chatting
> ##### In an open chat room with a user, you will be able to send and receive messages in real time immediately.

## - Project structure
### The project back-end part is based on `three-tier architecture` with `Model-View-Controller` scheme which makes it more comfortable to use in practice and further expansion of the project.
- ### Controller
>##### Level of software operation, interaction with the service layer, intuitive for the user.
- ### Service
> ##### Layer that organises the business logic of the programme, operating on commands from the top layer.
- ### Repository
> ##### Full interaction with the database and logical layer.

## - Technologies
- ### Java, Maven
- ### Spring Boot (Web, Data, Security, WebSocket, Mail)
- ### MySQL

## - Project launch
1. ### Copy project link
2. ### Create new Intellij IDEA project from Version Control System
3. ### Edit application.properties file to establish connection to your own DataBase
4. ### Run project