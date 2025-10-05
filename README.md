# 📖 API de Gerenciamento de Biblioteca

## 📝 Descrição

Esta é uma **API RESTful** para o gerenciamento de uma biblioteca, desenvolvida como um projeto de estudo com foco em boas práticas de desenvolvimento, segurança e design de APIs.
A aplicação permite gerenciar **livros, autores, usuários e o fluxo de empréstimos e devoluções**.

A segurança é um pilar central, utilizando **Spring Security** com autenticação **stateless** baseada em **JSON Web Tokens (JWT)** e autorização por papéis (Roles).

---

## ✨ Funcionalidades Principais

* 🔐 Autenticação e Autorização com Spring Security e JWT
* 👤 Gestão de Usuários com perfis (**ROLE_USER, ROLE_ADMIN**)
* 📚 CRUD completo para Livros e Autores com relacionamentos **Muitos-para-Muitos**
* 🔄 Sistema de Empréstimo de livros com controle de status e datas
* 🛡️ Proteção de endpoints baseada em papéis (**Role-Based Access Control**)

---

## 🚀 Tecnologias Utilizadas

* **Backend**: Java 21
* **Framework**: Spring Boot 3
* **Segurança**: Spring Security
* **Autenticação**: JSON Web Token (JWT)
* **Banco de Dados**: MySQL
* **Persistência**: Spring Data JPA / Hibernate
* **Build Tool**: Maven

---

## ⚙️ Configuração e Instalação

### Pré-requisitos

* Java (JDK) 21 ou superior
* Maven 3.8 ou superior
* MySQL 8 ou superior

### Passos para Execução Local

1. **Clone o repositório:**

```bash
git clone <url-do-seu-repositorio>
cd gerenciamento-biblioteca-api
```

2. **Crie o Banco de Dados:**
   No cliente MySQL, execute:

```sql
CREATE DATABASE bibliotecaapi;
```

3. **Configure as Variáveis de Ambiente:**
   A aplicação espera as seguintes variáveis:

* `DB_USER`: Usuário do banco (ex: root)
* `DB_PASS`: Senha do banco
* `JWT_SECRET`: Chave secreta longa e aleatória para assinar os tokens JWT

🔹 Alternativamente, para desenvolvimento, crie um arquivo `src/main/resources/application-local.properties` (e adicione ao `.gitignore`) com:

```properties
spring.datasource.username=seu_usuario_db
spring.datasource.password=sua_senha_db
jwt.secret=suaChaveSecretaSuperLongaParaDesenvolvimento
```

E no `application.properties`:

```properties
spring.profiles.active=local
```

4. **Execute a Aplicação:**

```bash
mvn spring-boot:run
```

A API estará disponível em: **[http://localhost:8080](http://localhost:8080)**

---

## 📑 Documentação da API (Endpoints)

### 🔐 Autenticação

* **POST /auth/register** → Cria novo usuário (público)

  * Corpo: `UserDTO { name, email, password, role }`

* **POST /auth/login** → Autentica usuário e retorna JWT (público)

  * Corpo: `{ "email": "seu-email", "password": "sua-senha" }`
  * Resposta: `{ "token": "seu-jwt-aqui" }`

---

### 👤 Usuários

* **PUT /users/me** → Atualiza dados do usuário logado (**ROLE_USER / ROLE_ADMIN**)
* **GET /users** → Lista todos os usuários (**ROLE_ADMIN**)
* **DELETE /users/{id}** → Remove usuário por ID (**ROLE_ADMIN**)

---

### 📚 Livros e Autores

* **GET /authors** / **GET /books** → Lista todos (**ROLE_USER / ROLE_ADMIN**)
* **POST /authors** / **POST /books** → Cria autor/livro (**ROLE_ADMIN**)

  * Para livros: corpo deve conter `authorIds` existentes
* **PUT /authors/{id}** / **PUT /books/{id}** → Atualiza autor/livro (**ROLE_ADMIN**)
* **DELETE /authors/{id}** / **DELETE /books/{id}** → Remove autor/livro (**ROLE_ADMIN**)

---

### 📖 Empréstimos

* **POST /loans** → Registra novo empréstimo (**ROLE_USER / ROLE_ADMIN**)

  * Corpo: `{ "bookId": 123 }`

* **POST /loans/{id}/return** → Registra devolução (**ROLE_USER / ROLE_ADMIN**)

---
