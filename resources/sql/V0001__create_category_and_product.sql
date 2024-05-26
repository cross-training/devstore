CREATE TABLE Categories (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(80) NOT NULL,
    description TEXT
);

CREATE TABLE Products (
    id SERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(80) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    review DECIMAL(2, 1),
    url VARCHAR(255),
    imageUrl VARCHAR(255)    
);

CREATE TABLE Product_Category (
    product_id INTEGER,
    category_id INTEGER,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES Products(id),
    FOREIGN KEY (category_id) REFERENCES Categories(id),
    UNIQUE (product_id, category_id)
);

CREATE INDEX idx_category_code ON Categories(code);
CREATE INDEX idx_product_code ON Products(code);
