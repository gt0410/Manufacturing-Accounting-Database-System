/*
SQL Statements to CREATE TABLES
*/

-- Create Customer table
CREATE TABLE Customer (
    cname VARCHAR(20),
    caddress VARCHAR(100),
    category INT,
    CONSTRAINT category_ck CHECK (category BETWEEN 1 AND 10),
    PRIMARY KEY (cname)
);

-- Create Assembly table
CREATE TABLE Assembly (
    assembly_id VARCHAR(10), --aid01, aid02, ... ICREASE size based on   query frequency
    date_ordered DATE,
    assembly_details VARCHAR(100),
    cname VARCHAR(20),
    PRIMARY KEY (assembly_id),
    FOREIGN KEY (cname) REFERENCES Customer(cname)
);

--Create Department Table
CREATE TABLE Department (
    dept_no VARCHAR(7), -- dept1, dept2, ...
    dept_data VARCHAR(100),
    PRIMARY KEY (dept_no)
);

--Create Process Table
CREATE TABLE Process (
    process_id VARCHAR(7) PRIMARY KEY, -- proc1, proc2, ...
    process_data VARCHAR(100),
    dept_no VARCHAR(7) FOREIGN KEY REFERENCES Department(dept_no)
);

--Create Process_fit Table
CREATE TABLE Process_fit (
    process_id VARCHAR(7) FOREIGN KEY REFERENCES Process(process_id),
    fit_type VARCHAR(5), --fit01, fit02, ...
    PRIMARY KEY (process_id)
);

--Create Process_paint Table
CREATE TABLE Process_paint (
    process_id VARCHAR(7) FOREIGN KEY REFERENCES Process(process_id),
    paint_type VARCHAR(6), -- type01, type02,...
    paint_method VARCHAR(8), -- method01, method02, ...
    PRIMARY KEY (process_id)
);

--Create Process_cut Table
CREATE TABLE Process_cut (
    process_id VARCHAR(7) FOREIGN KEY REFERENCES Process(process_id),
    cutting_type VARCHAR(7), --ctype01, ctype02, ...
    machine_type VARCHAR(9), --machine01, machine02, ...
    PRIMARY KEY (process_id)
);

--Create Job Table
CREATE TABLE Job (
    job_no INT PRIMARY KEY, -- job01, job02, ...
    date_commenced DATE,
    date_completed DATE,
    assembly_id VARCHAR(10) FOREIGN KEY REFERENCES Assembly(assembly_id),
    process_id VARCHAR(7) FOREIGN key REFERENCES Process(process_id)
);

--Create Job_fit Table
CREATE TABLE Job_fit (
    job_no INT FOREIGN KEY REFERENCES Job(job_no),
    fit_labor_time TIME,
    PRIMARY KEY (job_no) 
);

--Create Job_paint Table
CREATE TABLE Job_paint (
    job_no INT FOREIGN KEY REFERENCES Job(job_no),
    color VARCHAR(10),
    volume INT, -- Assuming Volume of paint used will always be calculated to nearest integer
    paint_labor_time TIME,
    PRIMARY KEY (job_no)
);

--Create Job_cut Table
CREATE TABLE Job_cut (
    job_no INT FOREIGN KEY REFERENCES Job(job_no),
    job_machine_type VARCHAR(10), --jmachine01, jmachine02, ...
    machine_time TIME,
    material_used VARCHAR(3), --m01, m02, ...
    cut_labor_time TIME,
    PRIMARY KEY (job_no) 
);

--Create Department Account Table
CREATE TABLE Dept_acc (
    acc_no INT PRIMARY KEY,
    date_established DATE,
    details_2 FLOAT,
    dept_no VARCHAR(7) FOREIGN KEY REFERENCES Department(dept_no) UNIQUE
);

--Create Assembly Account
CREATE TABLE Assembly_acc (
    acc_no INT PRIMARY KEY,
    date_established DATE,
    details_1 FLOAT,
    assembly_id VARCHAR(10) FOREIGN KEY REFERENCES Assembly(assembly_id) UNIQUE
);

--Create Process Account
CREATE TABLE Process_acc (
    acc_no INT PRIMARY KEY,
    date_established DATE,
    details_3 FLOAT,
    process_id VARCHAR(7) FOREIGN KEY REFERENCES Process(process_id) UNIQUE
);

--Create Transaction Table
CREATE TABLE Transaction1 ( -- Added '1' because transaction is a keyword
    t_no VARCHAR(10) PRIMARY KEY,
    sup_cost FLOAT,
    job_no INT FOREIGN KEY REFERENCES Job(job_no),
    dacc_no INT FOREIGN KEY REFERENCES Dept_acc(acc_no),
    aacc_no INT FOREIGN KEY REFERENCES Assembly_acc(acc_no),
    pacc_no INT FOREIGN KEY REFERENCES Process_acc(acc_no)
);



/*
SQL Statements to create index
*/

-- Create index on category - customer table
CREATE INDEX customer_category ON customer(category);

-- Create index on dept_no - Process table
CREATE INDEX process_dept ON process(dept_no);

-- Create index on assembly_id - Job table
CREATE INDEX assembly_job ON job(assembly_id);

-- Create index on dept_no - Dept_acc table
CREATE INDEX dept_no_acc ON Dept_acc(dept_no);

-- Create index on assembly_id - Assembly_acc table
CREATE INDEX assembly_id_acc ON Assembly_acc(assembly_id);

-- Create index on process_id - process_acc table
CREATE INDEX process_id_acc ON process_acc(process_id);





SELECT * FROM Customer;

SELECT * FROM Process;

SELECT * FROM Dept_acc;

SELECT * FROM Assembly_acc;

SELECT * FROM Process_acc;

SELECT * FROM Job;

SELECT * FROM Job_fit;

SELECT * FROM Job_paint;

SELECT * FROM Job_cut;

SELECT * FROM Transaction1;

SELECT * FROM Dept_acc;

SELECT * FROM Assembly_acc;

SELECT * FROM Process_acc;

