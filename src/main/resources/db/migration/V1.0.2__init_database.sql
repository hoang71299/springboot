CREATE TABLE  department (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `employee` (
  `dob` date DEFAULT NULL,
  `gender` enum('MALE','FEMALE','OTHER') DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `salary` decimal(38,2) DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14tijxqry9ml17nk86sqfp561` (`department_id`),
  CONSTRAINT `FK14tijxqry9ml17nk86sqfp561` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `employee_chk_1` CHECK ((`gender` between 0 and 2))
)