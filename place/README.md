# test PlacesService
###build and run
```
start(build) place
```
### browser
```
http://localhost:8080/place/getPlaces/{City}&{Range}
```
# Docker

### Terminal
```
maven clean,install place
```
```
docker build -f src/main/docker/Dockerfile.jvm -t place/place-service .
```
```
docker run -i --rm -p 8080:8080 place/place-service
```

# Swagger/OpenAPI
```
run/build place
```
```
search in Browser: http://localhost:8080/q/swagger-ui/#/default
```
