# Manufacturing-Accounting-Database-System
A job-shop accounting system is part of an organization that manufactures special-purpose assemblies for customers.

-------------
Author 
---

**Gowtham Teja Kanneganti**

Contact: gteja0410@gmail.com

-----
Database Specifications
---- 
A customer has a unique name, an address, and a category (an integer number from 1-10). A customer can order several assemblies. Each assembly is identified by a unique assembly-id, and has a date-ordered, and assembly-details. To manufacture assemblies, the organization contains a number of processes, each of which is identified by a unique process-id and is supervised by one department. Each department has its own department number and department-data. Each process also has process-data. Processes are classified into three types: paint, fit, and cut. The following information is kept about each type of process:
* Fit: fit-type
* Paint: paint-type, painting-method
* Cut: cutting-type, machine-type

During manufacture an assembly can pass through any sequence of processes in any order; it may pass through the same process more than once.

A job is assigned every time a process begins on an assembly. Information recorded about a job includes a unique job-no, a date the job commenced, and a date the job completed as well as additional information that depends on the type of job. Jobs are classified into three job types: cut-job, paint-job, and fit-job. Information stored about particular job types is:
* Cut-job: type of machine used, amount of time the machine used, material used, and labor time.
* Pain-job: color, volume, and labor time.
* Fit-job: labor time.

An account is maintained by the organization to keep track of expenditure for each process, each assembly, and each department. For each account, the database stores its unique account number and the date the account established. Three types of accounts are maintained:
* Assembly-account to record costs (details-1) for assemblies.
* Department-account to record costs (details-2) for departments.
* Process-account to record costs (details-3) for processes.

As a job proceeds, cost transactions can be recorded against it. Each such transaction is identified by a unique transaction number, and is for a given cost (sup-cost). Each transaction of necessity updates three accounts:
* A process account
* An assembly account
* A department account