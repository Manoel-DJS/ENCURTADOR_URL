# 🔗 Encurtador de URL - JAVA API REST

Desafio Backend - API  para encurtar URLs, desenvolvida em **Java 17 com Spring Boot**, hospedada na plataforma **Render**, e integrada ao banco de dados online - **MongoDB Atlas**.

---

## 🌐 URL da API

- API: [https://encurtador-url-8lqz.onrender.com](https://encurtador-url-8lqz.onrender.com)
- Swagger UI: [Acessar Swagger UI](https://encurtador-url-8lqz.onrender.com/swagger-ui/index.html#/)

---

## 📌 Visão Geral

Esta API permite:

- ✅ Encurtar URLs longas em links curtos.
- 🔁 Redirecionar para a URL original.
- 📄 Recuperar a URL encurtada como texto(`String`).

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Web (REST API)
- Spring Data MongoDB (MongoDB Atlas)
- SpringDoc OpenAPI (Swagger)
- MongoDB Atlas (Banco de Dados em Nuvem)
- Render (Hospedagem da API)
- Docker 

---

## 🚀 Endpoints Disponíveis

### 1️⃣ Encurtar uma URL

`POST /shorten-url`

**Request:**
```json
{
  "urlOriginal": "https://www.exemplo.com/artigos/guia-completo-spring-boot-mongodb"
}
```
**Response (200 OK):**
```json
{
  "urlEncurtada": "https://encurtador-url-8lqz.onrender.com/abc123"
}
```
### 2️⃣ e 3️⃣ Redirecionar para a URL original
```json
  "https://encurtador-url-8lqz.onrender.com/{id}}"
```
Exemplo:
```json
  "https://encurtador-url-8lqz.onrender.com/abc123"
```
**Response (200 OK):**
```json
  "https://www.exemplo.com/artigos/guia-completo-spring-boot-mongodb"
```

## 🧪 Como Testar a API?

Você pode testar os endpoints de forma prática utilizando as ferramentas abaixo:

### 🛠️ Ferramentas Recomendadas

- [Postman](https://www.postman.com/) 
- [Insomnia](https://insomnia.rest/)
- [Clique aqui para usar a API pelo Front-End Angular](https://url-short-angular.vercel.app/)

### 🌐 Teste via Swagger UI (Recomendado)

Acesse a documentação interativa e teste diretamente no navegador:

👉 [https://encurtador-url-8lqz.onrender.com/swagger-ui/index.html#/](https://encurtador-url-8lqz.onrender.com/swagger-ui/index.html#/)

Aguarde alguns segundos (talvez, uns 30) para ela iniciar no render e permitir as requisições.