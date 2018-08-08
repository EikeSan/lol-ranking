# lol-ranking
Rank de vitorias e partidas


- GET /users/list -> lista todos os usuários

- POST /users/signUp -> cadastrar novo usuário

  ```javascript
    body: {
      username: 'maria',
      password: '123456'
    }
```

- POST /login -> verifica as credenciais do usuário e retorna o token no Header

  ```javascript
  
    body: {
      username: 'maria',
      password: '123456'
    }
```