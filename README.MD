# SPRING BOOT

## Curls

```bash
curl --location 'http://localhost:8080/demo/api/login' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "admin@gmail.com",
  "password": "123456"
}'
```

```bash
curl --location 'http://localhost:8080/demo/api/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
  "email": "test@gmail.com",
  "password": "test"
}'
```

```bash
curl --location 'http://localhost:8080/demo/api/users'
```

## Docker


```docker
docker build -t codeparcemain.azurecr.io/api-test-devops:main .

docker push codeparcemain.azurecr.io/api-test-devops:main 

docker run -d -p 8080:8080--name api-test-devops  --memory "150M" --cpus "0.1"  --domainname "code.parce" api-test-devops:main

```