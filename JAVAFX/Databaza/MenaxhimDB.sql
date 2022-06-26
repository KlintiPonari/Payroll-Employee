DROP DATABASE IF EXISTS MenaxhimiDB;
CREATE DATABASE MenaxhimiDB;
USE MenaxhimiDB;



CREATE TABLE managers (
                          username     VARCHAR(20) NOT NULL COLLATE latin1_general_cs,
                          uemail       text NOT NULL COLLATE latin1_general_cs,
                          upassword 	text NOT NULL COLLATE latin1_general_cs

)ENGINE=INNODB;



CREATE TABLE employees (
                           Employee_id INTEGER,
                           Employee_name VARCHAR(20),
                           Employee_surname VARCHAR(20),
                           Employee_birthday DATE,
                           Employee_gender VARCHAR(10),
                           status VARCHAR(30),
                           Employee_number VARCHAR(15),
                           Employee_email VARCHAR(30),
                           Employee_address VARCHAR(30),
                           Employee_hours INT,
                           PRIMARY KEY (Employee_id)
)ENGINE=INNODB;



CREATE TABLE contracts (
                           Contract_date_begin DATE,
                           Contract_date_due DATE,
                           job_title VARCHAR(30),
                           department VARCHAR(30),
                           EmpID INTEGER,
                           empSalary VARCHAR(30),
                           FOREIGN KEY (EmpID)
                               REFERENCES employees (Employee_id)
							   ON DELETE CASCADE

) ENGINE=INNODB;





CREATE TABLE payment (
                         Employee_netto_salary INT,
                         Payment_bonus INT,
                         tax_ammount INT,
                         empId INTEGER,
                         FOREIGN KEY (empId)
                             REFERENCES employees (Employee_id)
                             ON DELETE CASCADE
) ENGINE=INNODB;


INSERT INTO employees(Employee_id, Employee_name, Employee_surname,	Employee_birthday,Employee_gender, status,Employee_number, Employee_email, Employee_address, Employee_hours)  
VALUES  (1, "Klinton", "Ponari", "2000/03/27" , "Male" , false, "043915470", "klintiponari6@gmail.com", "street", "8");      



INSERT INTO payment(Employee_netto_salary, payment_bonus, tax_ammount,empId)
VALUES (400, 200, 100, 1);

INSERT INTO contracts(Contract_date_begin, Contract_date_due, job_title, department,EmpId,empSalary)
VALUES ("2021/06/21", "2022/06/22", ".Net Programmer", "Computer Enginner", 1, 500);

UPDATE employees SET Employee_name = 'Klinton', Employee_surname = 'Ponari' WHERE Employee_id=1; 


