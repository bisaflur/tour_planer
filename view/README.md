# view

## Project setup with node
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```
## Project setup with docker
### Build docker file
```
docker build -t frontend-vuejs/frontend-vuejs-app .
```

### Run docker file
```
docker run -it -p 80:80 --rm --name vuejs-app-1 frontend-vuejs/frontend-vuejs-app
```
