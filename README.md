
# Spring Microservice

Microservices with spring boot java 3.1.0.




## Routes API

Register User

```bash
    curl --location 'localhost:8090/api/user/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "userName": "Isco3",
        "password": "12345",
        "email": "test3@gmail"
    }'
```

Get all users

```bash
    curl --location 'localhost:8090/api/user/all' \
    --header 'Content-Type: application/json' \
    --data ''
```

Login user

```bash
curl --location 'localhost:8090/api/user/login' \
--header 'Content-Type: application/json' \
--data '{
    "userName": "Isco39",
    "password": "12345"
}'
```

Insert user videogame

```
curl --location 'localhost:8090/api/user/videogames/insert' \
--header 'Content-Type: application/json' \
--data '{
    "user":{
        "id": 1
    },
    "description": "Esto es una description",
    "endDate": "2023-06-17",
    "gameId": 1,
    "grade": 10,
    "platform": "XBOX"
}'
```

Get all user videogames

```
curl --location 'localhost:8090/api/user/videogames/all/1'
```

For delete a user videogame

```
curl --location --request DELETE 'localhost:8090/api/user/videogames/delete' \
--header 'Content-Type: application/json' \
--data '{
    "id": 2
}'
```

For update user videogame

```
curl --location 'localhost:8090/api/user/videogames/update' \
--header 'Content-Type: application/json' \
--data '{
    "id": 3,
    "description": "Zelda goty",
    "gameId": 1,
    "platform": "Ouya",
    "endDate": "2026-06-06",
    "grade": 10,
    "user": {
        "id": 1
    }
        
}'
```

Insert a videogame

```
curl --location 'localhost:8090/api/videogames/insert' \
--header 'Content-Type: application/json' \
--data '{
    "videogameName": "Zelda3"
}'
```

Get all videogames

```
curl --location 'localhost:8090/api/videogames/all' \
--data ''
```

Get videogame by id

```
curl --location --request POST 'localhost:8090/api/videogames/get/1' \
--header 'Content-Type: application/json'
```


