## Using Mysql + Redis + Security in Spring Boot via Spring Data JPA

In this application, we're gonna build a Spring Boot Rest API example with Maven that use Spring Data JPA to interact with Mysql database. We will use:

- Hexagonal architecture + DDD
- Builder Pattern
- SOLID Principles
- Spring Web + JPA + Redis + Spring Security
- Open Api to document

### Configurations

Open the `application.yml` file and set your own configurations.

### Prerequisites

- Java 17
- Maven > 3.0

### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

### From docker

Go on the project's root folder, then type:

    $ docker-compose up -d

Urls:

    APP http://localhost:9654/bkool/v1

    SWAGGER http://localhost:9654/bkool/v1/swagger-ui.html

    Adminer http://localhost:8070/?server=host.docker.internal%3A3306&username=root&db=bkool


### How to use

#### Login:

    user: user

    pass: 1234

#### In bike Controller add Authorizacion Bearer

## Support
Please enter an issue in the repo for any questions or problems.
<br> Alternatively, please contact us at jcarrasco018@gmail.com