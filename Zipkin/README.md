
# zipkin server

This microservice track the communication between all microservices you can check if the microservice is working well or has a problem.
Each information can save in mysql so you need to create de database "zipkin" and run the schema for create the tables that needs zipkin for work.

RabbitMQ can communicate with zipkin using the enviroments variables so you can check this configuration in "zipkin.cmd"

Zipkin it is a .jar so the application is builded.

```
http://localhost:9411
```



## Requirement to work

- MySQl: you need to create the database with the schema.
- RabbitMQ: It is optional, in RabbitMQ you can check track the message and the health for each microservice.
- For run this zipkin you need to run the .cmd it is bash with the configuration and enviroments for zipkin works and the connection with RabbitMQ.

