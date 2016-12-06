# Busroutes
An interesting coding challenge.

### Try it out
This is a micro service implemented as a REST-API, supporting a single URL and only
GET requests. It serves
`http://localhost:8088/api/direct?dep_sid={}&arr_sid={}`. The parameters
`dep_sid` (departure) and `arr_sid` (arrival) are two station ids (sid)
represented by 32 bit integers.

The response is a single JSON Object:

```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "dep_sid": {
      "type": "integer"
    },
    "arr_sid": {
      "type": "integer"
    },
    "direct_bus_route": {
      "type": "boolean"
    }
  },
  "required": [
    "dep_sid",
    "arr_sid",
    "direct_bus_route"
  ]
}
```

The `direct_bus_route` field is set to `true` if there exists a bus route
in the input data that connects the stations represented by `dep_sid` and
`arr_sid`. Otherwise `direct_bus_route` is set to `false`.




### Example Data

Bus Routes Data File:
```
3
0 0 1 2 3 4
1 3 1 6 5
2 0 6 4
```

Query:
````
http://localhost:8088/api/direct?dep_sid=3&arr_sid=6
```

Response:
```
{
    "dep_sid": 3,
    "arr_sid": 6,
    "direct_bus_route": true
}
```

### Quick smoke test

Assuming a `bash` environment, you can do a quick local test:
```
bash build.sh
cd tests/
bash run_test_local.sh ../service.sh
```
This should output:
```
TEST PASSED!
```

Given a running `docker` installation and a UNIX-like environment you can run:
```
cd tests/
bash build_docker_image.sh YOUR_GIT_REPO_URL|ZIP_FILE
bash run_test_docker.sh
```
This should output:
```
TEST PASSED!
```



*Note: The docker based test assumes your running native docker. If not (e.g.
your on OSX) please adopt the `run_test_docker.sh` file and replace `localhost`
with the IP of your docker VM*
