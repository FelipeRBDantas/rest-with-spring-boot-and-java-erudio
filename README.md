# Rest With Spring Boot Erudio

> **Status**: 🚧 Em desenvolvimento

Este projeto é uma **API RESTful** orientada a testes, com pipeline de **CI/CD**, conteinerização e orquestração de containers, monitoramento com logs e **Spring Actuator**, documentação, content negotiation, **HATEOAS**, serialização JSON, versionamento de API e de base de dados, uso de **MySQL**, migração e conversão de banco de dados, configuração de **CORS**, segurança, relatórios, envio de e-mails, aplicação de **design patterns** e padrões arquiteturais.

---

## 🛠️ Tools & Tech Stack

- ☕ **Java 21**
- 🧩 Spring Boot • Spring Data (JPA/Hibernate) • Spring Security • Spring Mail • Spring Actuator
- 🧪 TDD • BDD • JUnit 5 • Mockito • AssertJ • REST Assured • TestContainers
- 🔑 JWT • Swagger (OpenAPI)
- 🗄️ MySQL • Flyway • Jasper Reports • SQLines
- ⚙️ Lombok • JSON Serialization • SLF4j Logs • Content Negotiation • HATEOAS (HAL)
- 🐳 Docker / Docker Compose • Kubernetes (K8s)
- 🏗️ Design Patterns • Padrões Arquiteturais / Enterprise
- ☁️ Deploy GCP: Cloud Run • Cloud SQL • GKE • Artifact Registry  
  CI/CD: GitHub Actions + Docker + GKE
- ☁️ Deploy AWS: ECS • RDS • ECR • IAM  
  CI/CD: GitHub Actions + Docker + ECS
- 🛠️ IDE: IntelliJ IDEA

---

## ✨ Features

### Person
- [x] Save
- [x] Update
- [x] Delete
- [x] FindAll
- [x] FindById

---

## 🚀 Run & Build

### Migrate Flyway
```bash
mvn clean flyway:migrate
```

### Build & Run Spring Boot (sem testes)
```bash
mvn clean package spring-boot:run -DskipTests
```
**Propriedades:**
- `-DskipTests` → Compila mas não executa testes.
- `-Dmaven.test.skip=true` → Nem compila nem executa testes.

---

## 🗃️ Database Configuration

Crie a base `rest_with_spring_boot_erudio` e configure no `application.yml`:

```yaml
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
    show-sql: false
    open-in-view: false
logging:
  level:
    <package name>: DEBUG
```

---

## 📡 Usage

### Content Negotiation
Suporte a **Server-driven** e **Agent-driven**.  
Defina no *Header* `Accept`:
- `application/json`
- `application/yaml`
- `application/xml`

### API Docs
`http://localhost:8080/swagger-ui.html`
`http://localhost:8080/v3/api-docs`

---

## 🔍 ACID Overview

O padrão **ACID** garante confiabilidade e consistência em bancos de dados transacionais.

| Propriedade  | Objetivo Corporativo                      |
|-------------|--------------------------------------------|
| Atomicidade | Evitar dados parciais ou inconsistentes    |
| Consistência| Garantir regras de negócio sempre válidas  |
| Isolamento  | Evitar efeitos de concorrência             |
| Durabilidade| Garantir que dados commitados persistam    |

### Propagation (Spring/Hibernate)
| Tipo          | Comportamento                                                           |
|-------------- |-------------------------------------------------------------------------|
| REQUIRED      | Usa a transação existente ou cria uma nova se não houver.               |
| REQUIRES_NEW  | Sempre cria nova transação, suspendendo a existente.                    |
| SUPPORTS      | Usa transação existente, se houver; senão executa sem transação.        |
| MANDATORY     | Exige transação existente, senão lança exceção.                         |
| NOT_SUPPORTED | Executa fora de transação, suspendendo a existente.                     |
| NEVER         | Proíbe transação; lança exceção se houver.                              |
| NESTED        | Cria transação aninhada (savepoints).                                   |

### Níveis de Isolamento
| Nível               | Permite Dirty Reads? | Observação                                                   |
|---------------------|----------------------|--------------------------------------------------------------|
| DEFAULT            | Depende do DB (geralmente READ COMMITTED). |                          |
| READ_UNCOMMITTED   | ✅ Sim               | Pode ler dados não commitados.                               |
| READ_COMMITTED     | ❌ Não               | Evita dirty reads, permite non-repeatable.                   |
| REPEATABLE_READ    | ❌ Não               | Evita dirty/non-repeatable, phantom ainda possível.          |
| SERIALIZABLE       | ❌ Não               | Máxima integridade, menor performance.                       |

**Estratégia CRUD**  

| Operação | Propagation | Isolation        | readOnly | Observação corporativa                  |
| -------- | ----------- | ---------------- | -------- | --------------------------------------- |
| Create   | REQUIRED    | READ\_COMMITTED  | false    | Atomicidade simples, evita dirty reads. |
| Read     | SUPPORTS    | READ\_COMMITTED  | true     | Consulta segura, performance otimizada. |
| Update   | REQUIRED    | REPEATABLE\_READ | false    | Protege contra non-repeatable reads.    |
| Delete   | REQUIRED    | REPEATABLE\_READ | false    | Evita inconsistências durante remoção.  |

**Insights Corporativos**
- Leitura pura: `readOnly + SUPPORTS` → alta performance.
- Alteração crítica: `REQUIRED + REPEATABLE_READ` → consistência.
- Alta concorrência + integridade máxima: considerar `SERIALIZABLE` (custo em performance).

---

> *“Robustez transacional é o core da escalabilidade corporativa.”*
