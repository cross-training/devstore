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

### Endpoints

- [Discovery](http://localhost:8761)
- [Zipkin](http://localhost:9411)
- [Config](http://localhost:8888)
  - [catalog-service](http://localhost:8888/catalog-service)
- catalog service:
  - [health](http://localhost:8081/health)
  - [metrics](http://localhost:8081/metrics)
  - [categories](http://localhost:8081/categories)
  - [products](http://localhost:8081/products)
- [Prometheus](http://localhost:9090)
- [Grafana](http://localhost:3000)
  - Dashboards
    - [NodeJS Application Dashboard](http://localhost:3000/d/PTSqcpJWk/nodejs-application-dashboard?orgId=1&refresh=5s)

## End

**Down:**

```bash
python3 infra.py down
```

## References

- [grafana-dashboard](https://github.com/codecentric/spring-boot-monitoring-sample/blob/master/grafana/grafana-dashboard.json)
