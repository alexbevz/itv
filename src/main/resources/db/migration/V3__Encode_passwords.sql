create extension if not exists pgcrypto;

update "user"
set password = crypt(password, gen_salt('bf'));