-- Table 1
CREATE TABLE table1 (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);

INSERT INTO table1 (id, name, age)
SELECT 
    FLOOR(RAND() * 1000) AS id,
    CONCAT('Name', FLOOR(RAND() * 1000)) AS name,
    FLOOR(RAND() * 100) AS age
FROM
    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS t;

-- Table 2
CREATE TABLE table2 (
    id INT PRIMARY KEY,
    address VARCHAR(100),
    city VARCHAR(50)
);

INSERT INTO table2 (id, address, city)
SELECT 
    FLOOR(RAND() * 1000) AS id,
    CONCAT('Address', FLOOR(RAND() * 1000)) AS address,
    CONCAT('City', FLOOR(RAND() * 1000)) AS city
FROM
    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS t;
