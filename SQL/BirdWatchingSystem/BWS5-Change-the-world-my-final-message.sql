DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id` INT PRIMARY KEY,
    `name` VARCHAR(50),
    `age` INT,
    `email` VARCHAR(100)
);

INSERT INTO `users` 
    (`id`, `name`, `age`, `email`)
VALUES 
    (1, 'John Doe', 25, 'john.doe@example.com'),
    (2, 'Jane Smith', 30, 'jane.smith@example.com'),
    (3, 'Mike Johnson', 35, 'mike.johnson@example.com'),
    (4, 'Sarah Williams', 28, 'sarah.williams@example.com'),
    (5, 'David Brown', 32, 'david.brown@example.com');
