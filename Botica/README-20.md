# Botica — Pack integrado para 20/20

Este proyecto incluye mejoras para cumplir la rúbrica:
- **Diseño de solución**: MVC + DAO + SOLID + Seguridad (+ TDD con 3 pruebas)
- **Librerías**: Guava, Apache Commons Lang3, Apache POI, Logback (Spring Boot)
- **Control de versiones**: Workflow de GitHub Actions
- **UI**: login + estado de sesión (ya presente), export a Excel `/admin/productos/export`

## Cómo ejecutar
1. `mvn clean install`
2. `mvn spring-boot:run`
3. Visita `/inicio`. Para exportar Excel: `/admin/productos/export` (requiere rol en seguridad si se protege `/admin/**`).

## Tests
- `mvn test` ejecuta Repository, Service y Web tests.

## Notas
- Ajusta la seguridad en `SecurityConfig` si deseas restringir `/admin/**`.
- Si usas PostgreSQL en producción, usa perfil de test con H2/Testcontainers.
