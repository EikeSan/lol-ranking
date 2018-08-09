# lol-ranking
Rank de vitorias e partidas


- GET /users/list -> lista todos os usuários

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
    
- POST /participant/create -> cadastrar novo player/time

  ```javascript
    body: {
      name: 'NTC', *//Unique Player Nickname ou Nome do Time 
      match: 10,
      win: 4
    }

- PUT /participant/update/{id} -> Atualizar dados do player/time

  ```javascript
    body: {
      name: 'NTC', //Unique Player Nickname ou Nome do Time 
      match: 10,
      win: 4
    }
    
- PUT /participant/updateStatus/{id} -> Incrementar número de partidas e vitórias do player/time

  ```javascript
    body: { 
      match: 10, *
      win: 4
    }
- PUT /participant/updateWins/{id}-> Incrementar número de vitórias do player/time

  ```javascript
    body: { 
      win: 4 *
    }

- DELETE /participant/delete/{id} -> Excluir player/time

- GET /participant/ranking -> Listar players/times pelo número de viórias, menor número de partidas utilizado com desempate

- GET /participant/{id}-> Buscar player/time






