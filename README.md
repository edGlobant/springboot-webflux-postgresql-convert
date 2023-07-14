# Vehicle USD to Crypto price converter

This is a Springboot Webflux reactive API to convert vehicles USD prices into BTC or ETH price.

## Getting Started

These are the steps on how to get started the app:

1. Clone the repository.
2. Install the dependencies. (Maven, Java 17, Docker)
3. Run ```mvn clean package```
4. Run ```docker-compose up```

You can find the app running at ```PORT :8090``` now.

You can test the endpoints with the collection present on the directory ```/postman``` its v2.1 btw.

For some reason swagger is not working on the docker container so if you run the app locally on ```port 8080``` you can check the docs on this url:

```http://localhost:8080/swagger-ui.html```