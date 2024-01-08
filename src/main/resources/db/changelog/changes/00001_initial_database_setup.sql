--liquibase formatted sql

--changeset liquibase:1 labels:ddl
--comment: Create user table
CREATE TABLE IF NOT EXISTS user
(
    username    text,
    password    text,
    roles       frozen<set<text>>,
    image       text,
    created_at  timestamp,
    updated_at  timestamp,
    PRIMARY KEY (username)
);
-- rollback DROP TABLE user


--changeset liquibase:2 labels:ddl
--comment: Create category table
CREATE TABLE IF NOT EXISTS category
(
    id uuid,
    name text,
    created_at  timestamp,
    updated_at  timestamp,
    PRIMARY KEY (id)
);
-- rollback DROP TABLE category

--changeset liquibase:3 labels:ddl
--comment: Create product table
CREATE TABLE IF NOT EXISTS product
(
    id uuid,
    category_id uuid,
    name text,
    image text,
    created_at  timestamp,
    updated_at  timestamp,
    PRIMARY KEY (id)
);
-- rollback DROP TABLE product

--changeset liquibase:4 labels:ddl
--comment: Create company table
CREATE TABLE IF NOT EXISTS company
(
    id uuid,
    name text,
    created_at  timestamp,
    updated_at  timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE company

--changeset liquibase:5 labels:ddl
--comment: Create account table
CREATE TABLE IF NOT EXISTS account
(
    id uuid,
    balance DECIMAL,
    created_at  timestamp,
    updated_at  timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE account

--changeset liquibase:6 labels:ddl
--comment: Create inventory table
CREATE TABLE IF NOT EXISTS inventory
(
    id uuid,
    name text,
    created_at  timestamp,
    updated_at  timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE inventory

-- --changeset liquibase:7 labels:ddl
--comment: Create incoming_product table
CREATE TABLE IF NOT EXISTS incoming_product
(
    id uuid,
    product_id uuid,
    company_id uuid,
    count int,
    price DECIMAL,
    inventory_id uuid,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
);
-- rollback DROP TABLE incoming_product

--changeset liquibase:8 labels:ddl
--comment: Create outgoing_product table
CREATE TABLE IF NOT EXISTS outgoing_product
(
    id uuid,
    product_id uuid,
    company_id uuid,
    count int,
    price DECIMAL,
    inventory_id uuid,
    status text,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE incoming_product

--changeset liquibase:9 labels:ddl
--comment: Create sales table
CREATE TABLE IF NOT EXISTS sales
(
    id uuid,
    product_id uuid,
    company_id uuid,
    count int,
    revenue DECIMAL,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE sales

--changeset liquibase:10 labels:ddl
--comment: Create incoming_and_outgoing_to_account table
CREATE TABLE IF NOT EXISTS incoming_and_outgoing_to_account
(
    id uuid,
    company_id uuid,
    price DECIMAL,
    status text,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE incoming_and_outgoing_to_account

--changeset liquibase:11 labels:ddl
--comment: Create debt table
CREATE TABLE IF NOT EXISTS debt
(
    id uuid,
    company_id uuid,
    amount DECIMAL,
    status text,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE debt

--changeset liquibase:12 labels:ddl
--comment: Create incoming_product_to_inventory table
CREATE TABLE IF NOT EXISTS incoming_product_to_inventory
(
    id uuid,
    product_id uuid,
    count int,
    inventory_id uuid,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE incoming_product_to_inventory

--changeset liquibase:13 labels:ddl
--comment: Create outgoing_product_from_inventory table
CREATE TABLE IF NOT EXISTS outgoing_product_from_inventory
(
    id uuid,
    product_id uuid,
    count int,
    inventory_id uuid,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
    );
-- rollback DROP TABLE outgoing_product_from_inventory


--changeset liquibase:14 labels:ddl
--comment: Create product_in_inventory table
CREATE TABLE IF NOT EXISTS product_in_inventory
(
    id uuid,
    product_id uuid,
    inventory_id uuid,
    count int,
    created_at  timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
);
-- rollback DROP TABLE product_in_inventory