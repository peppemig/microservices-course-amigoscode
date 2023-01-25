# SpringBoot microservices project

# Current services:
- Customer service **(port 8080)**
- Fraud service **(port 8081)**
- Notification service **(port 8082)**

# Other features:
- Eureka Server (to list all services) **(port 8761)**
- API Gateway (currently only for 'Customer service') **(port 8083)**
- RabbitMQ (dockerized) **(port 5672 - where services will connect) (port 15672 - management board)**
- Zipkin (dockerized) and Micrometer for tracing **(port 9411)**
- Feign Client
- All services are interacting with a MySQL database (dockerized) **(port 3307)**

# RabbitMQ flow:
- Customer service -> Fraud service -> Customer service -> QUEUE -> Notification service 
