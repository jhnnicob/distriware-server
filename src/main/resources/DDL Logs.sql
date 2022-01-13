--July 21 2021--

ALTER TABLE `serv`.`user` 
ADD COLUMN `person_id` INT NULL AFTER `email`;

ALTER TABLE `serv`.`person` 
CHANGE COLUMN `mobileNumber` `mobile_number` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `phoneNumber` `phone_number` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `bussinessName` `business_name` VARCHAR(100) NULL DEFAULT NULL ,
CHANGE COLUMN `bussinessAddress` `business_address` VARCHAR(255) NULL DEFAULT NULL ;

ALTER TABLE `serv`.`person` 
CHANGE COLUMN `firstName` `first_name` VARCHAR(100) NULL DEFAULT NULL ,
CHANGE COLUMN `lastName` `last_name` VARCHAR(100) NULL DEFAULT NULL ;


CREATE TABLE `serv`.`person_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NULL,
  `role` VARCHAR(50) NULL,
  `person_rolecol` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));