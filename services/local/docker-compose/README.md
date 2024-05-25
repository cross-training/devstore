# Deploy local with Docker

## Start

```bash
docker-compose -p devstore up -d
```

## Stop

```bash
docker-compose -p devstore down
```

## References

Kill process in 5432 port

```shell
sudo kill -9 $(sudo lsof -t -i:5432)
```

