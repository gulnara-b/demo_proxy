# Proxy ....

This app accepts, verifies and proxies calls



## Developer manual
### Requirements

1. Java 8
2. Docker

### Run

1. Run `sh ./db.sh` or `docker-compose up`
2. Run `./gradlew bootRun`

### Develop and test

1. Import build.gradle into your IDE of your choice
2. Run test and bootRun tasks after modifying the code.

### Test

In the console run this command multiple times with varying params. Change IP to `127.0.0.1` or type wrong values for IDs and time.

```shell
curl -XPOST http://localhost:8080/customer/request -H "Content-Type: application/json" \
-d '{ "customerID":1, "tagID": 1, "userID":"aaaaaaaa-bbbb-cccc-1111-222222222222", "remoteIP": "127.0.0.11",  "timestamp": 1600000000 }'
```

then check billing:

```shell
curl http://localhost:8080/billing/customer/1/date/2020-09-13
```

which should respond:

```json
[{"id":1,"customer":{"id":5,"name":"Client2","active":true},"timestamp":"2021-03-03T21:00:00Z","validCount":2,"invalidCount":1}]````
```
