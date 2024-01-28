
-- Create the random table
DROP TABLE IF EXISTS random_table;
CREATE TABLE random_table (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    email VARCHAR(100)
);

-- Insert random data into the table
INSERT INTO random_table (id, name, age, email)
VALUES (1, 'John', 25, 'john@gmail.com'),
       (2, 'Jane', 30, 'jane@gmail.com'),
       (3, 'Bob', 35, 'bob@gmail.com'),
       (4, 'Mike', 40, 'mike@gmail.com'),
       (5, 'Kate', 45, 'kate@gmail.com');