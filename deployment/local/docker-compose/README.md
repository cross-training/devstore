# Deploy local with Docker

## Start

**Requirements:**

```shell
pip install python-dotenv
sudo chown -R $USER:$USER grafana
sudo chown -R $USER:$USER prometheus
sudo chown -R $USER:$USER nginx   
```

**Up:**

```bash
sudo kill -9 $(sudo lsof -t -i:5432)
python3 infra.py up
```

**Up partials:**

```bash
python3 infra.py up config
python3 infra.py up discovery
python3 infra.py up postgres,config,discovery,gateway
python3 infra.py up catalog
```

### Endpoints

- [Zipkin](http://localhost:9411)
- [config service](http://localhost:8888)
  - [discovery config](http://localhost:8888/discovery-service/docker)
  - [api gateway config](http://localhost:8888/api-gateway/docker)
  - [catalog config](http://localhost:8888/catalog-service/docker)
  - [rating config](http://localhost:8888/rating-service/docker)
- [discovery service](http://localhost:8761)
- [api gateway](http://localhost:8080)
  - [health](http://localhost:8080/health)
  - [metrics](http://localhost:8080/metrics)
  - [catalog](http://localhost:8080/catalog/info)
  - [rating](http://localhost:8080/rating/info)
- catalog service:
  - [health](http://localhost:8081/health)
  - [metrics](http://localhost:8081/metrics)
  - [categories](http://localhost:8081/categories)
  - [products](http://localhost:8081/products)
- [prometheus](http://localhost:9090)
- [grafana](http://localhost:3000)
  - Dashboards
    - [NodeJS Application Dashboard](http://localhost:3000/d/PTSqcpJWk/nodejs-application-dashboard?orgId=1&refresh=5s)

## End

**Down:**

```bash
python3 infra.py down
```

## References

- [grafana-dashboard](https://github.com/codecentric/spring-boot-monitoring-sample/blob/master/grafana/grafana-dashboard.json)
