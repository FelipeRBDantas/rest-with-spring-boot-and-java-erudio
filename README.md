# Observações

Em desenvolvimento!

# Rest With Spring Boot Erudio

Esse projeto é uma API Restful orientado a testes, com Ci/CD, monitoramento, documentação, Content Negotiation, prática de versionamento de API, versionamento de base de dados, banco de dados relacional MySQL, configuração de CORS e segurança.

## Tools

:coffee: Java 8

:hammer_and_wrench: Intellij IDEA

:hammer_and_wrench: TDD

:hammer_and_wrench: JUnit5

:hammer_and_wrench: Mockito

:hammer_and_wrench: Spring Boot

:hammer_and_wrench: Spring Data (JPA/Hibernate)

:hammer_and_wrench: Spring Security

:hammer_and_wrench: JWT

:hammer_and_wrench: Swagger

:hammer_and_wrench: MySQL

:hammer_and_wrench: Flyway

## Features

### Person

- [X] Save
- [X] Update
- [X] Delete
- [X] FindAll
- [X] FindById

## Run Migrate Flyway

```mvn clean flyway:migrate```

## Run Build and Spring Boot Application

Executa a aplicação e o build especificando que não será executado os testes, apenas compilado.

```mvn clean package spring-boot:run -DskipTests```

Propriedades:

> -DskipTests	Compila mas não executa os testes.

> Dmaven.test.skip=true	Nem compila, nem executa — ignora completamente a fase de testes.

## Database Configurations

Crie uma base de dados chamada "rest_with_spring_boot_erudio" e adicione no arquivo "application.properties" as configurações abaixo:

```
#application.yml

spring:
  application:
    name: <application name>
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/rest_with_spring_boot_erudio?useTimezone=true&serverTimezone=UTC
    username: <username>
    password: <password>
  jpa:
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
    show-sql: false
    open-in-view: false
logging:
  level:
#    root: WARN
    <package name>: DEBUG
```

## Usage

### Content Negotiation

Com o Content Negotiation, a aplicação se encontra orientada a Server-driven e Agent-driven, possibilitando que o cliente negocie a representação ou conteúdo de sua solicitação para JSON ou XML. Para negociar um determinado conteúdo, adicione em "Headers" a chave "Accept" com o valor "application/json" ou "application/xml".

### Documentation

http://localhost:8080/swagger-ui.html
