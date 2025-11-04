QUERIES PARA BASE DE DATOS POSTGRESQL

La documentación del Swagger esta disponible solo para el usuario con rol de rector

El usuario de tipo rector es el primer usuario que se registra al sistema, así que por favor PRECAUCIÓN CON LAS CREDENCIALES.
En el sistema solo se pueden crear usuarios usando el usuario de tipo rector y solo con los roles de estudiante y docente.

Recuerda que para toda acción que tenga que ver con las asignaturas debes tener un usuario con el rol de rector.

El sistema tiene su base de datos creada en PostgreSQL

Crear base de datos

CREATE DATABASE parcial_2
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

Crear tabla de Rol

CREATE TABLE IF NOT EXISTS public.rol
(
    id integer NOT NULL DEFAULT nextval('rol_id_seq'::regclass),
    rol_nombre character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT rol_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.rol
    OWNER to postgres;

Insertar roles en la tabla de rol

Es importante no cambiar el orden, ya que para las funcionalidades se necesitan los id en orden.

INSERT INTO public.rol(rol_nombre) VALUES ('ROLE_RECTOR'),('ROLE_DOCENTE'),('ROLE_ESTUDIANTE');

