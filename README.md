# Observações

Em desenvolvimento!

# Rest With Spring Boot Erudio

Esse projeto é uma API Restful orientado a testes, com CI/CD, conteinerização e orquestração de containers, monitoramento com logs e Spring Actuator, documentação, content negotiation, HATEOAS, serialização de JSON, prática de versionamento de API, versionamento de base de dados, banco de dados relacional MySQL, configuração de CORS, segurança, relatórios, envio de e-mail, design patterns e padrões arquiteturais.

## Tools

:coffee: Java 21

:hammer_and_wrench: Spring Boot

:hammer_and_wrench: Spring Data (JPA/Hibernate)

:hammer_and_wrench: Spring Security

:hammer_and_wrench: Spring Mail

:hammer_and_wrench: Spring Actuator

:hammer_and_wrench: Intellij IDEA

:hammer_and_wrench: TDD

:hammer_and_wrench: BDD

:hammer_and_wrench: JUnit5

:hammer_and_wrench: Mockito

:hammer_and_wrench: AssertJ

:hammer_and_wrench: REST Assured

:hammer_and_wrench: TestContainers

:hammer_and_wrench: Mockito

:hammer_and_wrench: JWT

:hammer_and_wrench: Swagger (Open API)

:hammer_and_wrench: MySQL

:hammer_and_wrench: JPA / Hibernate

:hammer_and_wrench: Flyway

:hammer_and_wrench: Jasper Reports

:hammer_and_wrench: Lombok

:hammer_and_wrench: JSON Serialization

:hammer_and_wrench: Logs com SLF4j

:hammer_and_wrench: Content Negotiation

:hammer_and_wrench: HATEOAS com HAL

:hammer_and_wrench: Docker / Docker Compose

:hammer_and_wrench: Kubernetes (K8s)

:hammer_and_wrench: Design Patterns

:hammer_and_wrench: Padrões Arquiteturais / Enterprise

:hammer_and_wrench: Deploy na GCP com Google Cloud Run, Google Cloud SQL, GKE e Artifact Registry

:hammer_and_wrench: Continuous Deployment na GCP com GitHub Actions, Docker e Google Kubernetes Engine (GKE)

:hammer_and_wrench: Deploy na AWS utilizando Amazon ECS, Amazon RDS, Amazon ECR e IAM

:hammer_and_wrench: Continuous Deployment na AWS com GitHub Actions, Docker, Amazon ECR e Amazon ECS

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

### ACID

O padrão ACID é um conjunto de propriedades fundamentais que garantem confiabilidade e consistência em sistemas de banco de dados transacionais. É a base para o design de transações em qualquer aplicação corporativa. Vamos destrinchar cada letra com visão prática e corporativa.

1️⃣ A → Atomicidade (Atomicity)

Definição: Uma transação é indivisível; ou tudo ocorre, ou nada ocorre.

Exemplo: Transferência bancária:

Débito de uma conta e crédito em outra devem acontecer juntos.

Se uma falhar, a transação inteira é desfeita (rollback).

Objetivo corporativo: Evitar dados parciais que causem inconsistência ou prejuízo.

2️⃣ C → Consistência (Consistency)

Definição: Após a transação, o banco de dados deve permanecer em um estado consistente, respeitando todas as regras de negócio, restrições e integridade referencial.

Exemplo:

Se o saldo de contas não pode ser negativo, a transação deve garantir essa regra.

Qualquer violação resulta em rollback.

Objetivo corporativo: Garantir que regras de negócio nunca sejam quebradas por falhas de transação.

3️⃣ I → Isolamento (Isolation)

Definição: Cada transação deve ser independente das demais que ocorrem simultaneamente.

Fenômenos que podem ocorrer sem isolamento:

Dirty reads: ler dados não commitados.

Non-repeatable reads: ler dados diferentes na mesma transação.

Phantom reads: linhas adicionais aparecem em consultas repetidas.

Objetivo corporativo: Evitar inconsistências de dados em sistemas concorrentes.

4️⃣ D → Durabilidade (Durability)

Definição: Uma vez que a transação é confirmada (commit), suas alterações são permanentes, mesmo em caso de falha de sistema ou queda de energia.

Exemplo:

Um pagamento confirmado não pode ser perdido mesmo que o servidor caia imediatamente depois.

Objetivo corporativo: Garantir confiabilidade absoluta e confiança nos dados.

<br />

| Propriedade  | Objetivo Corporativo                      |
| ------------ | ----------------------------------------- |
| Atomicidade  | Evitar dados parciais ou inconsistentes   |
| Consistência | Garantir regras de negócio sempre válidas |
| Isolamento   | Evitar efeitos de concorrência            |
| Durabilidade | Garantir que dados commitados persistam   |

<br />

#### Propagation (Spring/Hibernate):

| Tipo            | Comportamento resumido                                                           |
| --------------- | -------------------------------------------------------------------------------- |
| `REQUIRED`      | Usa a transação existente ou cria uma nova se não houver.                        |
| `REQUIRES_NEW`  | Sempre cria uma nova transação, suspendendo a existente.                         |
| `SUPPORTS`      | Executa dentro da transação existente, se houver; caso contrário, sem transação. |
| `MANDATORY`     | Exige uma transação existente, caso contrário lança exceção.                     |
| `NOT_SUPPORTED` | Executa fora de qualquer transação, suspendendo a existente.                     |
| `NEVER`         | Nunca deve haver transação; se houver, lança exceção.                            |
| `NESTED`        | Cria uma transação aninhada dentro da existente (usando savepoints).             |

<br />

#### Níveis de isolamento (Spring/Hibernate):

| Nível                 | Comportamento resumido                                                             |
| --------------------- | ---------------------------------------------------------------------------------- |
| **DEFAULT**           | Usa o padrão do banco (geralmente READ COMMITTED).                                 |
| **READ\_UNCOMMITTED** | Permite **dirty reads**, leitura suja de dados não commitados.                     |
| **READ\_COMMITTED**   | Evita dirty reads, mas ainda permite **non-repeatable reads**.                     |
| **REPEATABLE\_READ**  | Evita dirty e non-repeatable reads, mas **phantom reads** ainda podem ocorrer.     |
| **SERIALIZABLE**      | Isolamento máximo: evita todos os fenômenos, mas reduz concorrência e performance. |

<br />

#### Resumo estratégico por CRUD no uso do ACID (Spring/Hibernate):

| Operação | Propagation | Isolation        | readOnly | Observação corporativa                  |
| -------- | ----------- | ---------------- | -------- | --------------------------------------- |
| Create   | REQUIRED    | READ\_COMMITTED  | false    | Atomicidade simples, evita dirty reads. |
| Read     | SUPPORTS    | READ\_COMMITTED  | true     | Consulta segura, performance otimizada. |
| Update   | REQUIRED    | REPEATABLE\_READ | false    | Protege contra non-repeatable reads.    |
| Delete   | REQUIRED    | REPEATABLE\_READ | false    | Evita inconsistências durante remoção.  |

<br />

#### Relação com níveis de isolamento no uso do ACID (Spring/Hibernate):

| Isolation Level   | Dirty Reads Permitidos? |
| ----------------- | ----------------------- |
| READ\_UNCOMMITTED | ✅ Permitido             |
| READ\_COMMITTED   | ❌ Evitado               |
| REPEATABLE\_READ  | ❌ Evitado               |
| SERIALIZABLE      | ❌ Evitado               |

<br />

#### 🔑 Insights corporativos no uso do ACID (Spring/Hibernate):

Leitura pura → readOnly + SUPPORTS para performance.

Alteração crítica → REQUIRED + REPEATABLE_READ para consistência.

CRUD simples → READ_COMMITTED suficiente.

Alta concorrência e integridade máxima → considerar SERIALIZABLE, mas só quando necessário, devido ao impacto de performance.
