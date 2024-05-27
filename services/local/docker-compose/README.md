# Deploy local with Docker

## Start

### Up

```bash
docker-compose -p devstore up -d
```

Only postgres:

```bash
docker-compose -p devstore up -d postgres
```

### Migrate Database

```bash
flyway migrate
```

### Endpoints

- backend service balanced by nginx:
  - [health](http://localhost:8081/health)
  - [metrics](http://localhost:8081/metrics)
  - [categories](http://localhost:8081/categories)
  - [products](http://localhost:8081/products)
- [Prometheus](http://localhost:9090)
- [Grafana](http://localhost:3000)
  - Dashboards
    - [NodeJS Application Dashboard](http://localhost:3000/d/PTSqcpJWk/nodejs-application-dashboard?orgId=1&refresh=5s)

## End

### Down

```bash
docker-compose -p devstore down
```

## References

Kill process in 5432 port

```shell
sudo kill -9 $(sudo lsof -t -i:5432)
```

Set folder permissions

```shell
sudo chown -R $USER:$USER grafana
sudo chown -R $USER:$USER prometheus
sudo chown -R $USER:$USER nginx   
```

- [grafana-dashboard](https://github.com/codecentric/spring-boot-monitoring-sample/blob/master/grafana/grafana-dashboard.json)
