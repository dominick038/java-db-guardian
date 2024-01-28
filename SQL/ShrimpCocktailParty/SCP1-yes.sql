-- Create a table
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(100)
);

-- Insert data into the table
INSERT INTO `customers` (name, email)
VALUES ('John Doe', 'john@example.com'),
             ('Jane Smith', 'jane@example.com'),
             ('Mike Johnson', 'mike@example.com'),
             ('Mike Johnson', 'mike@example.com');

