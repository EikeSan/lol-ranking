# lol-ranking
Rank de vitorias e partidas


- GET /users-> lista todos os usuários

- POST /users/signUp -> cadastrar novo usuário

  ```javascript
    body: {
      username: 'maria',
      password: '123456'
    }


- POST /login -> verifica as credenciais do usuário e retorna o token
  ```javascript
    body: {
      username: 'maria',
      password: '123456'
    }
    
- POST /participants -> cadastrar novo player/time

  ```javascript
    body: {
      name: 'NTC', *//Unique Player Nickname ou Nome do Time 
      match: 10,
      win: 4
    }

- PUT /participants/{id} -> Atualizar dados do player/time

  ```javascript
    body: {
      name: 'NTC', //Unique Player Nickname ou Nome do Time 
      match: 10,
      win: 4
    }
    
- PUT /participants/updateStatus/{id} -> Incrementar número de partidas e vitórias do player/time

  ```javascript
    body: { 
      match: 10, *
      win: 4
    }
- PUT /participants/updateWins/{id} -> Incrementar número de vitórias do player/time

  ```javascript
    body: { 
      win: 4 *
    }

- DELETE /participants/{id} -> Excluir player/time

- GET /participants/ranking -> Listar players/times pelo número de vitórias, menor número de partidas utilizado com desempate

- GET /participant/{id} -> Buscar player/time






