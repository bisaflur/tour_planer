#How to test PlacesService

->start(build) place, dann such nach z.B http://localhost:8080/place/getPlaces/Berlin&250 im Browser

##About Docker

->clean,install place

in Terminal:

-->cd place

-->docker build -f src/main/docker/Dockerfile.jvm -t (Placeholder containerName) .

-->docker run -i --rm -p 8080:8080 (containerName)