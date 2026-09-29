# UniGuide Database

## Database Overview

| Item | Value |
|---|---|
| Database | uniguide_db |
| DBMS | PostgreSQL 18.6 |
| Host | localhost |
| Port | 5432 |
| Schema | public |

## Database Files

- schema.sql - finalized database schema
- seed.sql - development seed data
- README.md - database documentation

## Tables

1. users
2. departments
3. courses
4. hostels
5. facilities
6. activities
7. clubs
8. events
9. notices
10. contacts
11. campus_locations

## Backend Compatibility

The database is designed for the UniGuide Spring Boot backend.

Backend database URL:

jdbc:postgresql://localhost:5432/uniguide_db

Hibernate schema generation is disabled:

spring.jpa.hibernate.ddl-auto=none

The backend and database were checked for compatibility.

## Security

Do not commit:

- PostgreSQL passwords
- JWT secrets
- User passwords
- Production credentials
- Local environment secret files

User data is intentionally excluded from seed.sql.

## Phase 15 Handover

- [x] PostgreSQL environment ready
- [x] UniGuide database created
- [x] Tables finalized
- [x] Relationships and foreign keys verified
- [x] Constraints verified
- [x] Indexes reviewed
- [x] Seed data loaded
- [x] Database testing completed
- [x] Backend compatibility reviewed
- [x] schema.sql generated
- [x] seed.sql generated
- [x] README.md created
- [ ] Clean restore verification
- [ ] Final Git commit
- [ ] Database handover

