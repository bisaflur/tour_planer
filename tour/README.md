# Tour-Service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

Console
```
mvnw compile quarkus:dev -Dquarkus.http.port=8086
```

## Docker

Create a docker image with:

```
docker build -f src/main/docker/Dockerfile.jvm -t tour/tour-service .
```

Run
```
docker run -i --rm -p 8086:8086 tour/tour-service
```

## Browser
```
http://localhost:8086/sights/{city}&{radius}
http://localhost:8086/sights/weather/{city}
http://localhost:8086/q/swagger-ui/#/
```

