# couchbase-spring-gitpod-playground
Couchbase and Spring playground for gitpod

## Gitpod Workspace

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/ldoguin/couchbase-spring-gitpod-playground)


Insert a user

```
curl -v http://localhost:8093/query/service \
     -d 'statement=INSERT INTO default (KEY, VALUE) VALUES ("123",{"userId": "123",  "firstName": "firstName",  "lastName": "lastName",  "email": "email",  "password": "password",  "country": "France",  "enabled": true,  "tokenExpired": true}) RETURNING *;' \
     -u Administrator:Administrator
```

