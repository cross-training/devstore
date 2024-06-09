SET search_path TO catalog;

CREATE TABLE tbl_categories (
    id VARCHAR(30) NOT NULL PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    description TEXT,
    UNIQUE (name)
);

CREATE TABLE tbl_products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    category_id VARCHAR(30) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    review DECIMAL(2, 1),
    url VARCHAR(255),
    image_url VARCHAR(255),
    create_at TIMESTAMP;
    UNIQUE (name)
);