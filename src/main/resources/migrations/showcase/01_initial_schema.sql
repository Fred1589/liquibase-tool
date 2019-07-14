--liquibase formatted sql
--changeset dummy_project:release_1.initial_schema.sql

CREATE SCHEMA IF NOT EXISTS ${schemaName};
GRANT USAGE ON SCHEMA ${schemaName} TO ${schemaUser};

CREATE TABLE ${schemaName}.person (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    job VARCHAR
);

GRANT ALL ON ALL TABLES IN SCHEMA ${schemaName} TO ${schemaUser};

--rollback DROP SCHEMA ${schemaName} CASCADE;