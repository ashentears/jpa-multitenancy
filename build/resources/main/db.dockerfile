FROM postgres:12
COPY tenant-schema.sql /docker-entrypoint-initdb.d/