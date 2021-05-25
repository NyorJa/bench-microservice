# bench-microservice

## Spring microservices using spring cloud (config, eureka, gateway, circuit breaker, zipkin-sleuth)

### Prerequisites
- Java 11
- Maven 3++
- Docker
- Vault

### How to run
- Run config-server, user-service, department-service, service-registry, cloud-gateway
- Run `docker run -d -p 9411:9411 openzipkin/zipkin`
