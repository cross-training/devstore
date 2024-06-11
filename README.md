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

## Tasks

**prerequisites:**

```shell
gem install rake git
```

| Task      | Description                                 | Command               |
|-----------|---------------------------------------------|-----------------------|
| Release   | Create a new release                        | `ruby script.rb`      |

## Infrastructure

- Tools:
  - Ansible:
    - [Install](./tools/ansible/README.md#install)
    - [Create Inventory](./tools/ansible/inventory.ini)
  - Flyway:
    - [Install](./tools/flyway/README.md#install)
  - Terraform:
  - Helm:
- Spring boot 3.2.6
- Services:
  - Infrastructure:
    - [Discovery Server](./services/discovery-service/README.md): Para el registrar y descubrir los servicios
    - [Config Server](./services/config-service/README.md): Para la configuración de los servicios
    - [Load Balancer](./services/load-balancer/README.md): Para el balanceo de carga
    - [Circuit Breaker](./services/circuit-breaker/README.md): Para la tolerancia a fallos
    - [Api Gateway](./services/gateway-service/README.md): Para la seguridad y el enrutamiento
    - [Log Center](./services/log-center/README.md): Para el Monitoreo de los servicios

  - Database
    - [PostgreSQL](./services/database/postgresql/README.md)
  - Frontend
  - nginx
  - Monitoring:
    - Prometheus
    - Grafana
  - Tracing:  
    - Zipkin:
  - Security:
    - Keycloak:
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

## References

- [Retos de programación](https://retosdeprogramacion.com/ejercicios/)
- [Hacker rank](https://www.hackerrank.com/dashboard?h_r=hrw&h_l=confirmation_email&utm_source=hrw&utm_campaign=confirmation_domains&utm_medium=email)
- [Canal de Programación Java](https://www.youtube.com/@luigicode1480/videos)
- [Curso de Microservicios con SpringBoot](https://www.youtube.com/watch?v=80zkdQJ2y4c&list=PLxy6jHplP3Hi_W8iuYSbAeeMfaTZt49PW)
  - [Spring Cloud](https://www.youtube.com/watch?v=80zkdQJ2y4c&list=PLxy6jHplP3Hi_W8iuYSbAeeMfaTZt49PW)
- Monitory:
  - [Prometheus](https://prometheus.io/)
  - [Grafana](https://grafana.com/)
  - [Zipkin](https://zipkin.io/)
  - [Rastreo y Monitoreo - Microservicios con Spring Boot](https://youtube.com/watch?v=doGYvlvmN6s)
  - [Microservicios con Spring Boot: Capítulo 11: Zipkin y Sleuth](https://www.youtube.com/watch?v=wF98ikyyKEI)
- Security:
  - [Keycloak](https://www.keycloak.org/)
  - [Spring Security](https://spring.io/projects/spring-security)
  - [Spring Security OAuth](https://spring.io/projects/spring-security-oauth)
  - [spring security samples](https://github.com/spring-projects/spring-security-samples)
  - Tutorials:
    - [Microservicios con Spring Boot: Capítulo 12: Keycloak (parte 1)](https://www.youtube.com/watch?v=uzou3vdmu9Q)
    - [Microservicios con Spring Boot: Capítulo 13: Keycloak (parte 2)](https://www.youtube.com/watch?v=EYIBO1TOSaE)

## Info

- [Spring Cloud Kubernetes](https://docs.spring.io/spring-cloud-kubernetes/reference/getting-started.html)
  - Description:
  - Dependence: spring-cloud-starter-Kubernetes-config
  - [tutorial](https://refactorizando.com/microservicios-spring-cloud-kubernetes/)
  - [Externalizar la configuración de Spring Boot en Kubernetes con ConfigMap](https://refactorizando.com/externalizar-configuracion-spring-boot-kubernetes-configmap/)
