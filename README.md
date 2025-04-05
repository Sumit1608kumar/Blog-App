# BlogApp Blog Application
Blog Application is a software program that enables users to easily create and manage blog posts. It is built using React and SpringBoot
 
##### HOME PAGE
![Home](Screenshots/Home.PNG)

##### LOGIN PAGE
![Login](Screenshots/Login.PNG)

##### SIGNUP PAGE
![Sign-up](Screenshots/Sign-up.PNG)

##### ALLPOSTS PAGE
![All-posts](Screenshots/All-posts.PNG)

##### FULLPOSTVIEW PAGE
![Profile](Screenshots/Profile.PNG)

##### ADDPOST PAGE
![Post](Screenshots/Post.PNG)

### QUICK GUIDE/STEPS TO RUN THE PROJECT:
1. Clone the project.
2. Download "STS [ Spring Tool Suite ] IDE and Configure It.
3. Download/Install/Configure Java 17 or above.
4. Download and install "mysql-installer-community-8.0.36.0" [ MySQL Workbench, MySQL command line, MySQL Server ]
5. The Projects needs to have database created as below and rest of the table creation will be taken care by Hibernate itself : 

```
create database blogapp_db;
```
6. Open STS IDE and import the cloned Project [BlogApp-Backend] , Run the project as SpringBoot App.
7. Install NodeJS and Node Package Manager, Move inside [BlogApp-Frontend] folder and open it in vscode and Run npm install , npm start
8. Visit http://localhost:3000/home for home page in your browser.

#### RECENT/ADDITIONAL CHANGES :
* You can now open the Swagger Documentation at : http://localhost:8080/swagger-ui/index.html
* Docker File and Docker Compose File has been attached. Move to the BlogApp-Backend folder and run below command :
  ```
  docker compose -p "BlogApp-Backend" up --build
  ```


Enjoy BlogApp Blog Application !! 😊
