--DROP TABLE IF EXISTS order;

CREATE TABLE ORDERS (
                               id INT PRIMARY KEY,
                               status VARCHAR(50) NOT NULL
);

CREATE SEQUENCE incrOneSeq START WITH 1 INCREMENT BY 1;