# Database

## Postgres

**Start:**

```bash
cd ./../../../../../deployment/local/docker-compose
docker-compose -p devstore up -d postgres
```

**Run Scripts:**

```bash
flyway -configFiles=postgres_flyway.conf migrate
```
