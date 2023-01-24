# SpringBoot microservices project

# Current services:
- Customer service
- Fraud service
- Notification service

# Other features:
- Eureka Server (to list all services)
- API Gateway (currently only for 'Customer service' on port 8083)
- Zipkin (dockerized) and Micrometer for tracing
- Feign Client
- All services are interacting with a MySQL database (dockerized)
