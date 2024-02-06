CREATE TABLE Brand (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL
);
CREATE TABLE Price (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       brand_id INT,
                       start_date TIMESTAMP WITH TIME ZONE,
                       end_date TIMESTAMP WITH TIME ZONE,
                       price_list INT,
                       product_id INT,
                       priority INT,
                       price DECIMAL(10, 2),
                       curr VARCHAR(3),
                       CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES Brand(id)
);
