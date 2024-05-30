SET search_path TO catalog;

INSERT INTO Categories (id, name, description) VALUES
('Electronics', 'Electronics', 'Devices and gadgets such as phones, laptops, etc.'),
('Books', 'Books', 'Various genres of books and literature.'),
('Clothing', 'Clothing', 'Apparel for men, women, and children.'),
('HomeKitchen', 'Home & Kitchen', 'Household items and kitchenware.'),
('Sports', 'Sports', 'Sports equipment and athletic wear.');

INSERT INTO Products (name, description, price, review, url, imageUrl) VALUES
('Smartphone', 'Latest model with all advanced features.', 699.99, 4.5, 'http://example.com/smartphone', 'http://example.com/smartphone.jpg'),
('Laptop', 'High performance laptop for gaming and work.', 1299.99, 4.7, 'http://example.com/laptop', 'http://example.com/laptop.jpg'),
('Cookbook', 'A variety of recipes for every meal.', 19.99, 4.3, 'http://example.com/cookbook', 'http://example.com/cookbook.jpg'),
('T-shirt', 'Comfortable cotton t-shirt.', 14.99, 4.0, 'http://example.com/tshirt', 'http://example.com/tshirt.jpg'),
('Basketball', 'Professional-grade basketball.', 29.99, 4.6, 'http://example.com/basketball', 'http://example.com/basketball.jpg');

INSERT INTO Product_Category (product_id, category_id) VALUES
(1, 'Electronics'), 
(2, 'Electronics'), 
(3, 'Books'), 
(4, 'Clothing'), 
(5, 'Sports'); 


