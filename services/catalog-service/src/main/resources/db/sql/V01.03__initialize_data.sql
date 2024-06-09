SET search_path TO catalog;

INSERT INTO tbl_categories (id, name, description) VALUES
('Electronics', 'Electronics', 'Devices and gadgets such as phones, laptops, etc.'),
('Books', 'Books', 'Various genres of books and literature.'),
('Clothing', 'Clothing', 'Apparel for men, women, and children.'),
('HomeKitchen', 'Home & Kitchen', 'Household items and kitchenware.'),
('Sports', 'Sports', 'Sports equipment and athletic wear.');

INSERT INTO tbl_products (name, category_id, description, price, stock, review, url, image_url, create_at) VALUES
('Smartphone','Electronics', 'Latest model with all advanced features.', 699.99, 100, 4.5, 'http://example.com/smartphone', 'http://example.com/smartphone.jpg', '2024-06-09 15:30:45' ),
('Laptop','Electronics', 'High performance laptop for gaming and work.', 1299.99, 100, 4.7, 'http://example.com/laptop', 'http://example.com/laptop.jpg','2024-06-09 15:30:45'),
('Cookbook','Books', 'A variety of recipes for every meal.', 19.99, 50, 4.3, 'http://example.com/cookbook', 'http://example.com/cookbook.jpg','2024-06-09 15:30:45'),
('T-shirt', 'Clothing', 'Comfortable cotton t-shirt.', 14.99, 50, 4.0, 'http://example.com/tshirt', 'http://example.com/tshirt.jpg','2024-06-09 15:30:45'),
('Basketball','Sports', 'Professional-grade basketball.', 29.99, 30, 4.6, 'http://example.com/basketball', 'http://example.com/basketball.jpg','2024-06-09 15:30:45');
