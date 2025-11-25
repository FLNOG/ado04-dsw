# 📚 Grades Platform – API + Web

Projeto completo contendo:
- **API REST** protegida com **JWT**
- **Aplicação Web** com Spring MVC + Thymeleaf
- Gerenciamento de cursos e autenticação

---

# 🚀 Tecnologias Utilizadas
- Java 24
- Spring Boot
- Spring MVC
- Spring Security (JWT + AuthenticationManager)
- JPA / Hibernate
- Thymeleaf
- H2 / MySQL (dependendo da config)

---

# 🔐 Autenticação (JWT)
Todas as rotas `/api/**` são protegidas e exigem um **token JWT**, exceto `/api/auth/login`.

Para gerar o token, use:

### **POST - /api/auth/login**
```json
{
  "username": "admin",
  "password": "admin"
}
```
Retorno esperado:
```
eyJh...<TOKEN>...
```

Você deve utilizar este token no header das requisições:
```
Authorization: Bearer <SEU_TOKEN_AQUI>
```

---

# 📘 Documentação da API de Cursos

## 🔹 Listar todos os cursos
### GET - `http://localhost:8080/api/cursos`
**Headers:**
```
Authorization: Bearer <SEU_TOKEN_AQUI>
```

---

## 🔹 Criar curso *(ROLE_ADMIN)*
### POST - `http://localhost:8080/api/cursos`
**Headers:**
```
Authorization: Bearer <SEU_TOKEN_AQUI>
Content-Type: application/json
```
**Body:**
```json
{
  "nome": "Curso de Java",
  "descricao": "Aprendendo Spring Boot",
  "duracao": 40,
  "professor": "Felipe Silva",
  "categoria": "Programação",
  "preco": 199.90
}
```

---

## 🔹 Atualizar curso *(ROLE_ADMIN)*
### PUT - `http://localhost:8080/api/cursos/{id}`
**Headers:**
```
Authorization: Bearer <SEU_TOKEN_AQUI>
Content-Type: application/json
```
**Body:**
```json
{
  "nome": "Curso de Java Avançado",
  "descricao": "Atualizado",
  "duracao": 50,
  "professor": "Felipe Silva",
  "categoria": "Programação",
  "preco": 249.90
}
```

---

## 🔹 Excluir curso *(ROLE_ADMIN)*
### DELETE - `http://localhost:8080/api/cursos/{id}`
**Headers:**
```
Authorization: Bearer <SEU_TOKEN_AQUI>
```

---

# 🌐 Parte Web
A aplicação também possui uma área administrativa acessível via navegador.

### Login Web:
Acesse:
```
http://localhost:8080/login
```
Após logar, você será direcionado para:
```
http://localhost:8080/admin
```

---

# 🗃 Banco de Dados (Exemplo de Roles)
```
ID | NAME
---|------------
1  | ROLE_ADMIN
2  | ROLE_USER
```

---

# 🏁 Como rodar o projeto
1. Clone o repositório
2. Configure application.properties se necessário
3. Rode o projeto:
```
./mvnw spring-boot:run
```
4. Acesse a API e o dashboard web

---
