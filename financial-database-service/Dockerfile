FROM postgres
ENV POSTGRES_PASSWORD mysecretpassword
ENV POSTGRES_DB kadmos
COPY dml.sql /docker-entrypoint-initdb.d/
EXPOSE 5432