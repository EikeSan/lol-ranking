# lol-ranking

Rank de vitorias e partidas 

## How Install to local server ##

* `git clone https://github.com/EikeSan/lol-ranking`
* `mvn clean package`
* `Create a database and specifies into application.properties`
* `mvn spring-boot:run -DskipTests -Dserver.port=8080`
* `The command above start a local server in: http://localhost:8080/`
* `First run the signUp route to create a user them the login route to retrive your token`
* `You can access http://localhost:8080/swagger-ui.html to test some routes`
* `Routes also are described below`



## Api Routes ##


- GET /users-> list all users

- POST /users/signUp -> create new user

  ```javascript
    body: {
      username: 'maria',
      password: '123456'
    }


- POST /login -> verify the user's credential and if success return the access token
  ```javascript
    body: {
      username: 'maria',
      password: '123456'
    }
    
- POST /participants -> create new player/team

  ```javascript
    body: {
      name: 'NTC', *//Unique Player Nickname or Team name 
      match: 10,
      win: 4
    }

- PUT /participants/{id} -> Update player/team's information 

  ```javascript
    body: {
      name: 'NTC', //Unique Player Nickname or Team name 
      match: 10,
      win: 4
    }
    
- PUT /participants/updateStatus/{id} -> Increase player/team's number of matches and wins  

  ```javascript
    body: { 
      match: 10, *
      win: 4
    }
- PUT /participants/updateWins/{id} -> Increase player/team's number of wins 

  ```javascript
    body: { 
      win: 4 *
    }

- DELETE /participants/{id} -> Delete player/team

- GET /participants/ranking -> List players/team's by number of win and lower number of matches in case of draw

- GET /participant/{id} -> Find player/team






