/*
SQL Statements to CREATE PROCEDURES for Queries 1 - 15
*/


-- Enter a new customer
CREATE PROCEDURE proc_1
    @cname VARCHAR(20),
    @caddress VARCHAR(100),
    @category INT
AS
BEGIN

INSERT INTO Customer
( -- Columns to insert data into
 [cname], [caddress], [category]
)
VALUES (@cname, @caddress, @category)
END


-- Enter a new Department
CREATE PROCEDURE proc_2
    @dept_no VARCHAR(7),
    @dept_data VARCHAR(100)
AS
BEGIN
    INSERT INTO Department
    ( -- Columns to insert data into
    [dept_no], [dept_data]
    )
    VALUES (@dept_no, @dept_data)
END


--Enter a new assembly with its customer-name, assembly-details, assembly-id,
-- and date-ordered 
CREATE PROCEDURE proc_3
    @assembly_id VARCHAR(10),
    @data_ordered VARCHAR(10),
    @assembly_details VARCHAR(100),
    @cname1 VARCHAR(20)
AS
BEGIN
    INSERT INTO Assembly
    ( -- Columns to insert data into
    [assembly_id], [date_ordered], [assembly_details], [cname]
    )
    VALUES (@assembly_id, CAST (@data_ordered as DATE), @assembly_details, @cname1)
END


--Enter a new process-id and its department together 
--with its type and information relevant to the type 
CREATE PROCEDURE proc_4
    @process_id VARCHAR(7),
    @process_data VARCHAR(100),
    @dept_no1 VARCHAR(7)
AS
BEGIN
    INSERT INTO Process
    ( -- Columns to insert data into
    [process_id], [process_data], [dept_no]
    )
    VALUES (@process_id, @process_data, @dept_no1)
END


CREATE PROCEDURE proc_4_1
    @process_id VARCHAR(7),
    @fit_type VARCHAR(5)
AS
BEGIN
    INSERT INTO Process_fit
    ( -- Columns to insert data into
    [process_id], [fit_type]
    )
    VALUES (@process_id, @fit_type)
END


CREATE PROCEDURE proc_4_2
    @process_id VARCHAR(7),
    @paint_type VARCHAR(6),
    @paint_method VARCHAR(8)
AS
BEGIN
    INSERT INTO Process_paint
    ( -- Columns to insert data into
    [process_id], [paint_type], [paint_method]
    )
    VALUES (@process_id, @paint_type, @paint_method)
END


CREATE PROCEDURE proc_4_3
    @process_id VARCHAR(7),
    @cutting_type VARCHAR(7),
    @machine_type VARCHAR(9)
AS
BEGIN
    INSERT INTO Process_cut
    ( -- Columns to insert data into
    [process_id], [cutting_type], [machine_type]
    )
    VALUES (@process_id, @cutting_type, @machine_type)
END


-- Create a new account and associate it with the 
-- process, assembly, or department to which it is applicable 
CREATE PROCEDURE proc_5_1
    @acc_no INT,
    @date_established VARCHAR(10),
    @details_2 FLOAT,
    @dept_no VARCHAR(7)
AS
BEGIN
    INSERT INTO Dept_acc
    ( -- Columns to insert data into
    [acc_no], [date_established], [details_2], [dept_no]
    )
    VALUES (@acc_no, CAST(@date_established as DATE), @details_2, @dept_no)
END


CREATE PROCEDURE proc_5_2
    @acc_no INT,
    @date_established VARCHAR(10),
    @details_1 FLOAT,
    @assembly_id VARCHAR(10)
AS
BEGIN
    INSERT INTO Assembly_acc
    ( -- Columns to insert data into
    [acc_no], [date_established], [details_1], [assembly_id]
    )
    VALUES (@acc_no, CAST(@date_established as DATE), @details_1, @assembly_id)
END


CREATE PROCEDURE proc_5_3
    @acc_no INT,
    @date_established VARCHAR(10),
    @details_3 FLOAT,
    @process_id VARCHAR(7)
AS
BEGIN
    INSERT INTO Process_acc
    ( -- Columns to insert data into
    [acc_no], [date_established], [details_3], [process_id]
    )
    VALUES (@acc_no, CAST(@date_established as DATE), @details_3, @process_id)
END


-- Enter a new job, given its job-no, assembly-id, process-id, and date the job commenced 
CREATE PROCEDURE proc_6
    @job_no INT,
    @date_commenced VARCHAR(10),
    @assembly_id VARCHAR(10),
    @process_id VARCHAR(7)
AS
BEGIN
    INSERT INTO Job
    ( -- Columns to insert data into
    [job_no], [date_commenced], [assembly_id], [process_id]
    )
    VALUES (@job_no, CAST(@date_commenced as DATE), @assembly_id, @process_id)
END


-- At the completion of a job, enter the date it completed 
--and the information relevant to the type of job 
CREATE PROCEDURE proc_7
    @job_no INT,
    @date_completed VARCHAR(10)

AS
BEGIN
    UPDATE Job SET date_completed = @date_completed WHERE job_no = @job_no
END


CREATE PROCEDURE proc_7_1
    @job_no INT,
    @fit_labor_time VARCHAR(8)
AS
BEGIN
    INSERT INTO Job_fit
    ( -- Columns to insert data into
    [job_no], [fit_labor_time]
    )
    VALUES (@job_no, CAST(@fit_labor_time as TIME))
END


CREATE PROCEDURE proc_7_2
    @job_no INT,
    @color VARCHAR(10),
    @volume INT,
    @paint_labor_time VARCHAR(8)
AS
BEGIN
    INSERT INTO Job_paint
    ( -- Columns to insert data into
    [job_no], [color], [volume], [paint_labor_time]
    )
    VALUES (@job_no, @color, @volume, CAST(@paint_labor_time as TIME))
END


CREATE PROCEDURE proc_7_3
    @job_no INT,
    @job_machine_type VARCHAR(10),
    @machine_time VARCHAR(8),
    @material_used VARCHAR(3),
    @cut_labor_time VARCHAR(8)
AS
BEGIN
    INSERT INTO Job_cut
    ( -- Columns to insert data into
    [job_no], [job_machine_type], [machine_time], [material_used], [cut_labor_time]
    )
    VALUES (@job_no, @job_machine_type, CAST(@machine_time as TIME), @material_used, CAST(@cut_labor_time as TIME))
END


-- Enter a transaction-no and its sup-cost and update all the costs (details) of the affected 
--accounts by adding sup-cost to their current values of details 
CREATE PROCEDURE proc_8
    @t_no VARCHAR(10),
    @sup_cost FLOAT,
    @job_no INT
AS
BEGIN
    DECLARE @dacc_no INT,
            @aacc_no INT,
            @pacc_no INT;
    
    SET @aacc_no = (SELECT acc_no
                    FROM Assembly_acc, Job
                    WHERE Assembly_acc.assembly_id = Job.assembly_id AND Job.job_no = @job_no);

    SET @pacc_no = (SELECT acc_no
                    FROM Process_acc, Job
                    WHERE Process_acc.process_id = Job.process_id AND Job.job_no = @job_no);

    SET @dacc_no = (SELECT acc_no
                    FROM Dept_acc, Process, Job
                    WHERE Process.process_id = Job.process_id AND Job.job_no = @job_no AND Dept_acc.dept_no = Process.dept_no);


    INSERT INTO Transaction1
    ( -- Columns to insert data into
    [t_no], [sup_cost], [job_no], [dacc_no], [aacc_no], [pacc_no]
    )
    VALUES (@t_no, @sup_cost, @job_no, @dacc_no, @aacc_no, @pacc_no);

    UPDATE Dept_acc
    SET details_2 = details_2 + @sup_cost
    WHERE acc_no = @dacc_no;

    UPDATE Assembly_acc
    SET details_1 = details_1 + @sup_cost
    WHERE acc_no = @aacc_no;

    UPDATE Process_acc
    SET details_3 = details_3 + @sup_cost
    WHERE acc_no = @pacc_no;
END


--Retrieve the cost incurred on an assembly-id 
CREATE PROCEDURE proc_9
    @assembly_id VARCHAR(10)

AS
BEGIN
    SELECT details_1 FROM Assembly_acc WHERE Assembly_acc.assembly_id = @assembly_id;
END


-- Retrieve the total labor time within a department for jobs completed
-- in the department during a given date
CREATE PROCEDURE proc_10
    @dept_no VARCHAR(7),
    @date_completed VARCHAR(10)

AS
BEGIN
    DECLARE @fl_time FLOAT,
            @pl_time FLOAT,
            @cl_time FLOAT,
            @tl_time FLOAT;
    SET @fl_time = (SELECT  SUM(( DATEPART(hh, fit_labor_time) * 3600 ) + ( DATEPART(mi, fit_labor_time) * 60 ) + DATEPART(ss, fit_labor_time))/60 as minute
FROM Job_fit WHERE Job_fit.job_no in (
SELECT distinct(job_no) FROM Job
WHERE Job.process_id in (SELECT distinct(process_id) FROM Process WHERE Process.dept_no = @dept_no) AND Job.date_completed = CAST(@date_completed as DATE)));

IF @fl_time IS NULL SET @fl_time = 0; 

    SET @pl_time = (SELECT  SUM(( DATEPART(hh, paint_labor_time) * 3600 ) + ( DATEPART(mi,  paint_labor_time) * 60 ) + DATEPART(ss,  paint_labor_time))/60 as minute
FROM Job_paint WHERE Job_paint.job_no in (
SELECT distinct(job_no) FROM Job
WHERE Job.process_id in (SELECT distinct(process_id) FROM Process WHERE Process.dept_no = @dept_no) AND Job.date_completed =CAST(@date_completed as DATE)));

IF @pl_time IS NULL SET @pl_time = 0;

    SET @cl_time = (SELECT  SUM(( DATEPART(hh, cut_labor_time) * 3600 ) + ( DATEPART(mi, cut_labor_time) * 60 ) + DATEPART(ss, cut_labor_time))/60 as minute
FROM Job_cut WHERE Job_cut.job_no in (
SELECT distinct(job_no) FROM Job
WHERE Job.process_id in (SELECT distinct(process_id) FROM Process WHERE Process.dept_no = @dept_no) AND Job.date_completed =CAST(@date_completed as DATE)));

IF @cl_time IS NULL SET @cl_time = 0;

    SET @tl_time = @fl_time + @pl_time + @cl_time
    SELECT  @tl_time
END


-- Retrieve the processes through which a given assembly-id has passed so far (in date-commenced order) and the department responsible for each process 
CREATE PROCEDURE proc_11
    @assembly_id VARCHAR(10)
AS
BEGIN
    SELECT Job.date_commenced, Job.process_id, Process.dept_no
    FROM Job, Process
    WHERE Job.assembly_id = @assembly_id AND Process.process_id = Job.process_id 
    ORDER BY 1 ;

END


-- Retrieve the jobs (together with their type information and assembly-id) completed during a given date in a given department 
CREATE PROCEDURE proc_12_1
    @date_completed VARCHAR(10),
    @dept_no VARCHAR(7)
AS
BEGIN
    SELECT DISTINCT(Job.job_no), Job.assembly_id, Job_fit.fit_labor_time
    FROM Job, Job_fit
    WHERE date_completed = @date_completed and Job.process_id in (SELECT process.process_id FROM Process WHERE dept_no = @dept_no) AND Job_fit.job_no = Job.job_no;

END


CREATE PROCEDURE proc_12_2
    @date_completed VARCHAR(10),
    @dept_no VARCHAR(7)
AS
BEGIN
    SELECT DISTINCT(Job.job_no), Job.assembly_id, Job_paint.color, Job_paint.volume, Job_paint.paint_labor_time
    FROM Job, Job_paint
    WHERE date_completed = @date_completed and Job.process_id in (SELECT process.process_id FROM Process WHERE dept_no = @dept_no) AND Job_paint.job_no = Job.job_no;

END

CREATE PROCEDURE proc_12_3
    @date_completed VARCHAR(10),
    @dept_no VARCHAR(7)
AS
BEGIN
    SELECT DISTINCT(Job.job_no), Job.assembly_id, Job_cut.job_machine_type, Job_cut.machine_time, Job_cut.material_used, Job_cut.cut_labor_time
    FROM Job, Job_cut
    WHERE date_completed = @date_completed and Job.process_id in (SELECT process.process_id FROM Process WHERE dept_no = @dept_no) AND Job_cut.job_no = Job.job_no;

END


-- Retrieve the customers (in name order) whose category is in a given range 
CREATE PROCEDURE proc_13
    @lower_b INT,
    @upper_b INT
AS
BEGIN
    SELECT cname, category AS name FROM Customer
    WHERE category >= @lower_b AND category <= @upper_b
    ORDER BY 1 ;
END


-- Delete all cut-jobs whose job-no is in a given range 
CREATE PROCEDURE proc_14
    @lower_b INT,
    @upper_b INT
AS
BEGIN
    DELETE FROM Job_cut WHERE job_no >= @lower_b AND job_no <= @upper_b
END


-- Change the color of a given paint 
CREATE PROCEDURE proc_15
    @job_no INT,
    @color VARCHAR(10)
AS
BEGIN
    UPDATE Job_paint SET color = @color WHERE job_no = @job_no;
END



CREATE PROCEDURE proc_222
    --@lower_b INT,
   -- @upper_b INT
AS
BEGIN
    DECLARE @j INT
    
    SET @j = (SELECT DISTINCT( Job_cut.job_no ) FROM Job_cut WHERE job_no >= 1 AND job_no <= 10 )
    --DELETE FROM Job_cut WHERE job_no >= @lower_b AND job_no <= @upper_b
    SELECT @j;
END