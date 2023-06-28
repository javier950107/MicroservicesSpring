
# Gateway server

This gateway server is a proxy, is the endpoint where you will check the security using "jwt".
In this server you will define a unique endpoint for all the microservices.

The microservice "videogames" you define the path /api/videogames/
The microservice "user-videogames" you define the path /api/users/ 

And you will define the security for each microservice and you will check if the user can use the endpoints and also gateway server works using load balancer.

```
http://localhost:8090
```