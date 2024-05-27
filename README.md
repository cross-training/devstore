# Dev Store

## Ejercicio

 Crea una web o app que funcione como un sitio donde encontrar aquellos productos
 que crees que todo developer debería conocer.

 Requisitos:

- Deberás crear una tienda online pero sin tienda... ¿Cómo? Tu web o app puede
   servir para enlazar a los productos que venden tiendas online reales (Amazon, por ejemplo)
   y así utilizar enlaces de referidos que te aporten ingresos.
- La tienda deberá poseer una sección de productos destacados y diferentes filtros
   para agrupar por categoría. Los productos son de tu total elección, y cada uno
   debe tener foto (o más de una) y descripción (e información extra si así lo quieres).

- ¡Punto extra! Es el último reto del año... ¿Quieres formar un equipo de hasta
   3 personas (tienen que existir commits de 3 personas) para llevar a cabo el reto?
   Este es el momento.

- [retos de programación](https://retosdeprogramacion.com/proyectos/)
- [example](https://github.com/sguerra/devarmor)

## Infrastructure

- Tools:
  - Ansible:
    - [Install](./tools/ansible/README.md#install)
    - [Create Inventory](./tools/ansible/inventory.ini)
  - Flyway:
    - [Install](./tools/flyway/README.md#install)
  - Terraform:
  - Helm:  
- Services:
  - Database
  - Backend service
  - Frontend service
  - nginx
  - Prometheus
  - Grafana
  - Zipkin
  - Keycloak
- Environment:
  - Local
    - [Docker compose](./services/local/docker-compose/README.md)
      - [docker-compose](./services/local/docker-compose/docker-compose.yaml)
      - [Up](./services/local/docker-compose/README.md#up)
      - [Migrate Database](./services/local/docker-compose/README.md#migrate-database)
    - kind Kubernetes
  - Cloud
    - AWS
    - GCP
    - Azure
- CI/CD:
  - [GitHub Actions](./.github/workflows/README.md)
