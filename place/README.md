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

```
clean,install place
```

### Terminal
```
docker build -f src/main/docker/Dockerfile.jvm -t place/place-service .
```
```
docker run -i --rm -p 8080:8080 (containerName)
```
