# Database

## Postgres

**Start:**

```bash
cd ./../../../../../deployment/local/docker-compose
docker-compose -p devstore up -d postgres
```

**Create User and Schema:**

```sql
CREATE USER rating WITH PASSWORD 'rating';
CREATE SCHEMA rating AUTHORIZATION rating;
```

**Run Scripts:**

```bash
flyway -configFiles=postgres_flyway.conf migrate
```
