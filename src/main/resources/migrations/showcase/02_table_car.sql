--liquibase formatted sql
--changeset dummy_project:release_1.table_car.sql

CREATE TABLE ${schemaName}.car (
    id BIGSERIAL PRIMARY KEY,
    person_id BIGINT REFERENCES ${schemaName}.person (id),
    brand VARCHAR
);

GRANT ALL ON ALL TABLES IN SCHEMA ${schemaName} TO ${schemaUser};

--rollback DROP TABLE ${schemaName}.car;