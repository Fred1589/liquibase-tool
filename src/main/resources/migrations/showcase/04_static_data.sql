--liquibase formatted sql
--changeset dummy_project:release_1.static_data.sql

INSERT INTO ${schemaName}.person (id, name, job) VALUES (-1, 'John Doe', 'Engineer');
INSERT INTO ${schemaName}.car (id, person_id, brand) VALUES (-1, -1, 'Mercedes');


--rollback DELETE FROM ${schemaName}.car WHERE id = -1; DELETE FROM ${schemaName}.person WHERE id = -1;