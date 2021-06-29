# bench-microservice

## Spring microservices using spring cloud (config, eureka, gateway, circuit breaker, zipkin-sleuth, logstash)

### Prerequisites
- Java 11
- Maven 3++
- Docker
- Vault
- Keycloak
- Zipkin
- OAUTH2 Token
- Logstash
- ElasticSearch

### How to run
- Run configuration-server, discovery-server, order-service, product-service, gateway-service, notification service
- Go to localhost:8200/ui and unlock the vault using the json config stored in order-service
- Make sure you specify the vault config in docker compose and extract the sample config in vault-config/vault.json
