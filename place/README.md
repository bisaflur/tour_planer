#Places-Service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

###build and run

Console

```
mvnw compile quarkus:dev -Dquarkus.http.port=8080
```

# Docker

Create a docker image with:

```
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/place-jvm .
```

Run
```
docker run -i --rm -p 8080:8080 quarkus/place-jvm
```

# Swagger/OpenAPI
```
http://localhost:8080/q/swagger-ui/
```

### Browser
```
http://localhost:8080/place/version
http://localhost:8080/place/getPlaces/{City}&{Radius}
```