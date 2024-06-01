# Database

## Postgres

**Start:**

```bash
cd ./../../../../../deployment/local/docker-compose
docker-compose -p devstore up -d postgres
```

**Create User and Schema:**

```sql
CREATE USER catalog WITH PASSWORD 'catalog';
CREATE SCHEMA catalog AUTHORIZATION catalog;
```


**Run Scripts:**

```bash
flyway -configFiles=postgres_flyway.conf migrate
```
