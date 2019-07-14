--liquibase formatted sql
--changeset dummy_project:release_1.table_animal.sql

CREATE TABLE ${schemaName}.animal (
    id BIGSERIAL PRIMARY KEY,
    person_id BIGINT REFERENCES ${schemaName}.person (id),
    type VARCHAR
);

GRANT ALL ON ALL TABLES IN SCHEMA ${schemaName} TO ${schemaUser};

--rollback DROP TABLE ${schemaName}.animal;