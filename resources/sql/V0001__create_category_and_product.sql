CREATE TABLE Categories (
    id VARCHAR(30) NOT NULL PRIMARY KEY,
    name VARCHAR(127) NOT NULL,
    description TEXT
);

CREATE TABLE Products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    review DECIMAL(2, 1),
    url VARCHAR(255),
    imageUrl VARCHAR(255)    
);

CREATE TABLE Product_Category (
    product_id LONG NOT NULL,
    category_id VARCHAR(30) NOT NULL,
    PRIMARY KEY (product_id, category_id),
    FOREIGN KEY (product_id) REFERENCES Products(id),
    FOREIGN KEY (category_id) REFERENCES Categories(id),
    UNIQUE (product_id, category_id)
);

