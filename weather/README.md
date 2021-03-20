# Weather-Service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

Console
```
mvnw compile quarkus:dev -Dquarkus.http.port=9080
```

## Docker

Create a docker image with:

```
docker build -f src/main/docker/Dockerfile.jvm -t weather/weather-service .
```

Run
```
docker run -i --rm -p 9080:9080 weather/weather-service
```

## Browser
```
http://localhost:9080/weather/version
http://localhost:9080/weather/getWeather/{city}
http://localhost:9080/q/swagger-ui/#/
```

