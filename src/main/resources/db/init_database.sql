
create database baitap;
use baitap;

CREATE TABLE department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);
insert into department(id, name)

values (1, 'sale'),
       (2, 'it'),
       (3, 'marketing');


CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    dob DATE,
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    salary DECIMAL(15, 2),
    phone VARCHAR(20),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES department(id)
);
INSERT INTO employee (name, dob, gender, salary, phone, department_id)
VALUES ('Nguyen Van A', '1990-05-15', 'MALE', 5000.00, '0987654321', 1),
       ('Le Van C', '1992-07-25', 'MALE', 5500.00, '0978123456', 1),
       ('Hoang Van E', '1995-12-10', 'MALE', 4500.00, '0945678912', 2);


