GRANT ALL ON DATABASE topjava TO postgres;
GRANT TEMPORARY, CONNECT ON DATABASE topjava TO PUBLIC;

create user topjava_user with encrypted password 'topjava_user';
grant all privileges on database topjava to topjava_user;

create schema if not exists topjava_schema;
create schema if not exists topjava_schema_liquibase;
grant all privileges on schema topjava_schema to topjava_user;
grant all privileges on schema topjava_schema_liquibase to topjava_user;
set search_path to "$user", topjava_user;
