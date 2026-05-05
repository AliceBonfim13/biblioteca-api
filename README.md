# 📚 Library API

## 📌 Descrição
API REST para gerenciamento de biblioteca, permitindo o controle de livros, autores e usuários.
O sistema conta com autenticação e autorização utilizando JWT, garantindo segurança no acesso aos endpoints.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- JPA / Hibernate
- Banco de Dados (MySQL)
- Docker e Docker Compose

---

## ⚙️ Funcionalidades

### 🔐 Autenticação
- Registro de usuários
- Login com geração de token JWT
- Proteção de rotas com Spring Security

### 👤 Usuários
- Cadastro de usuários
- Listagem de usuários
- Atualização de dados
- Remoção de usuários

### ✍️ Autores
- Cadastro de autores
- Listagem de autores
- Atualização de autores
- Exclusão de autores

### 📖 Livros
- Cadastro de livros
- Listagem de livros
- Atualização de livros
- Exclusão de livros
- Relacionamento com autores
