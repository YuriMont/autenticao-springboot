<h1 align="center">
    Serviço de autenticação utilizando Spring Security (Java)
</h1>

## 📖  Sobre o projeto:

Este serviço de autenticação foi criado utilizando o Spring Boot e o armazenamento de usuários será feito em um banco de dados PostgreSQL. A autenticação será baseada em tokens JWT (JSON Web Tokens).

### 📜 Sistema: 

O serviço utilizará o banco de dados PostgreSQL para armazenar os dados dos usuários. A tabela users será criada no banco de dados com os seguintes campos:

* `Long id`: identificador único do usuário.
* `String name`: nome do usuário.
* `String email`: endereço de e-mail do usuário.
* `String password`: senha do usuário.

As senhas dos usuários serão armazenadas de forma criptografada, utilizando a classe `PasswordEncoder` nativa do Spring Security.

O serviço será protegido por autenticação baseada em `token JWT`. Quando um usuário fizer login, um `token JWT` será gerado e retornado na resposta. Esse token deverá ser incluído no cabeçalho das requisições posteriores para acessar os endpoints protegidos.

Para a geração e validação de `tokens JWT`, será utilizada a biblioteca `jjwt` do Java.

Por fim, a autenticação do serviço será configurada utilizando o `Spring Security`. Será criada uma classe `JwtAuthenticationFilter` que será responsável por validar os `tokens JWT` e permitir que os usuários acessem os endpoints protegidos. A autenticação será configurada para exigir um token JWT válido em todos os endpoints, exceto os endpoints de cadastro e login de usuários.

### 📌 O serviço terá os seguintes endpoints:

1. `POST /api/user/registrar` - endpoint para o cadastro de novos usuários.
* Parâmetros: nome, e-mail, senha.
* Fluxo: os dados do usuário são recebidos e validados. Caso sejam válidos, o usuário é salvo no banco de dados e um token JWT é gerado e retornado na resposta.
* Resposta: um objeto contendo o token JWT gerado.

2. `POST /api/user/login` - endpoint para a autenticação de usuários existentes.
* Parâmetros: e-mail, senha.
* Fluxo: os dados de e-mail e senha são recebidos e validados. Caso o e-mail e senha correspondam a um usuário existente no banco de dados, um token JWT é gerado e retornado na resposta.
* Resposta: um token JWT é gerado.

3. `GET /api/user/listarTodos` - endpoint para obter informações de todos os usuários cadastrados.
* Fluxo: o token JWT é recebido na requisição e validado. Caso o token seja válido, as informações do usuário correspondente são recuperadas do banco de dados e retornadas na resposta.
* Resposta: uma lista de objetos contendo as informações de todos os usuário.

### 🛠️ Tecnologias 

<div align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)&nbsp;
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)&nbsp;
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)&nbsp;
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)&nbsp;
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)&nbsp;


</div>


