# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The project is built based on Spring Boot 2.4.5 

The following guides help you to adjust the configuration to adapt your environment:
* Change the url, username and password in **AppConfig.java**(D:\ProgramFiles\DST-group-project-login-\src\main\java\cn\wmyskxz\springboot\) 
```java
            //Modify to your database url below
            this.jdbcUrl = "jdbc:mysql://127.0.0.1:3306/login?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT&useSSL=false";
            //Modify to your database username below          
            this.jdbcUsername = "root";
            //Modify to your dadtabase password below
            this.jdbcPassword = "dd976789595";
```
   and **application.yml**(...\FinalWeb-master\src\main\resources)
```thymeleafexpressions
  datasource:
    #Modify to your database url below
    url: jdbc:mysql://127.0.0.1:3306/login?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT
    #Modify to your database username below
    username: root
    #Modify to your dadtabase password below
    password: dd976789595
```
* Create the tables by extracting SQL syntax in **schema.sql**(...\FinalWeb-master\src\main\java\cn\wmyskxz\springboot\sql)
* Make sure to reload **pom.xml**
* The username is **admin** and the password is **123456**
* If you deploy the project on your device first time, it needs some minutes to initiate the crawler information. Therefore, you may find no data shown in the "Drugs", "Drug Labels" and "Dosing Guideline" pages. 
* It is recommended to use the matching function few minutes later after you firstly deploy the project on your device.
* Known BUG: After logining out, there is a snapshot left, which means when you first go back to the last page, you will see the snapshot of it without filter. However, if you refresh the snapshot or try to go to any other filtered pages, the blocker will work normally.

* [After finishing above, you can click here to enjoy](http://localhost:8080/login)
* You can also find this project on [https://github.com/wzj9050/FinalWeb](https://github.com/wzj9050/FinalWeb) when you need to revert to it

