# product-store
A simple CRUD application using Bootstrap, html, spring boot, spring MVC. An electronics store.

Befrore running project ensure that you have correctly configured the data to connect to the database

-- create database beststore;

-- create table
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    category VARCHAR(255),
    price DOUBLE PRECISION NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    image_file_name VARCHAR(255)
);

-- insert 30 products
INSERT INTO products (name, brand, category, price, description, image_file_name) VALUES
('Wireless Mouse', 'Logitech', 'Electronics', 24.99, 'Compact wireless mouse with smooth tracking.', '1.jpg'),
('Mechanical Keyboard', 'Redragon', 'Electronics', 59.99, 'RGB backlit mechanical keyboard with blue switches.', '2.jpg'),
('Bluetooth Headphones', 'Sony', 'Audio', 89.50, 'Noise-cancelling over-ear headphones with Bluetooth 5.0.', '3.jpg'),
('Smartphone Case', 'Spigen', 'Accessories', 19.99, 'Shockproof case with slim design.', '4.jpg'),
('Gaming Monitor', 'AOC', 'Electronics', 299.00, '27-inch Full HD monitor with 144Hz refresh rate.', '5.jpg'),
('USB-C Cable', 'Anker', 'Accessories', 9.99, 'Durable nylon braided cable, 1m.', '6.jpg'),
('Portable Charger', 'Xiaomi', 'Electronics', 39.99, '10000mAh fast-charging power bank.', '7.jpg'),
('Laptop Stand', 'Nexstand', 'Accessories', 29.99, 'Foldable and adjustable laptop stand.', '8.jpg'),
('Smartwatch', 'Samsung', 'Electronics', 199.99, 'Water-resistant smartwatch with heart rate monitor.', '9.jpg'),
('External Hard Drive', 'Seagate', 'Storage', 79.99, '1TB USB 3.0 portable hard drive.', '10.jpg'),
('Wireless Earbuds', 'Apple', 'Audio', 149.00, 'True wireless earbuds with long battery life.', '11.jpg'),
('Bluetooth Speaker', 'JBL', 'Audio', 99.00, 'Portable waterproof speaker with deep bass.', '12.jpg'),
('Webcam', 'Logitech', 'Electronics', 69.99, '1080p webcam with built-in microphone.', '13.jpg'),
('Desk Lamp', 'Philips', 'Home', 34.99, 'LED lamp with adjustable brightness.', '14.jpg'),
('HDMI Cable', 'Belkin', 'Accessories', 12.99, 'High-speed HDMI 2.1 cable, 2m.', '15.jpg'),
('Wireless Keyboard', 'Microsoft', 'Electronics', 49.99, 'Compact wireless keyboard with quiet keys.', '16.jpg'),
('Smartphone', 'Xiaomi', 'Electronics', 349.00, 'Mid-range smartphone with AMOLED display.', '17.jpg'),
('Router', 'TP-Link', 'Networking', 59.00, 'Dual-band Wi-Fi 6 router.', '18.jpg'),
('SSD Drive', 'Samsung', 'Storage', 129.00, '500GB NVMe solid state drive.', '19.jpg'),
('Tablet', 'Apple', 'Electronics', 499.00, '10.2-inch tablet with Retina display.', '20.jpg'),
('Gaming Mouse', 'Razer', 'Electronics', 79.00, 'High precision RGB gaming mouse.', '21.jpg'),
('Microphone', 'Blue', 'Audio', 119.00, 'USB condenser microphone for streaming.', '22.jpg'),
('Smart TV', 'LG', 'Electronics', 699.00, '55-inch 4K UHD smart TV with HDR.', '23.jpg'),
('Flash Drive', 'SanDisk', 'Storage', 15.00, '64GB USB 3.1 flash drive.', '24.jpg'),
('Printer', 'HP', 'Office', 129.00, 'All-in-one wireless color printer.', '25.jpg'),
('Laptop', 'Dell', 'Computers', 899.00, '15-inch laptop with Intel i7 processor.', '26.jpg'),
('Graphics Card', 'NVIDIA', 'Computers', 599.00, 'GeForce RTX 4060 Ti 8GB.', '27.jpg'),
('Desk Chair', 'Secretlab', 'Furniture', 449.00, 'Ergonomic gaming chair with adjustable lumbar support.', '28.jpg'),
('Camera', 'Canon', 'Photography', 799.00, 'Mirrorless camera with 4K video.', '29.jpg'),
('Tripod', 'Manfrotto', 'Photography', 99.00, 'Aluminum tripod with fluid head.', '30.jpg');
