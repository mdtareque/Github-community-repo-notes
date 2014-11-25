## Diff between Union and Union all?
UNION will remove the duplicate rows from the result set while UNION ALL doesn’t.

##	Difference between WHERE and GROUP BY?
WHERE – used for table joins and filtering rows before the group by function. 
GROUP BY allows aggregation on the final result set – examples include COUNT, SUM, MIN, MAX, AVG. Group By uses tempdb. 

##	What keyword allows aggregation filtering on the GROUP BY result set?
The HAVING clause filters rows after the GROUP BY and can use aggregate functions.
GROUP BY id_book HAVING COUNT (distinct id_trd) > 5       -- books having more than 5 trades

##  What happens if a column in the SELECT section is missing from the GROUP BY section?
MS SQL Server : the parser will generate an error and not execute the SQL
Sybase : query executes without error but will not group the missing column & will instead return the same number of rows as the WHERE clause would return.

## Difference between Correlated Sub Query and a Nested Sub Query?
Correlated Subquery : the inner query is rerun for every row in the outer query & contains a column reference back to the outer query row currently being evaluated. 
Nested subquery runs only once for the entire outer query. The inner query can be run independently of  the outer query and there is no column reference back to the outer query. 

## Difference between an INNER join, and LEFT and RIGHT OUTER Joins? 
Inner join : only shows rows common to both tables
Left Outer : all rows from outer (left) table are returned, and for inner (right) table the rows which do not meet the join condition are returned as NULL. 
Right Outer : all rows from inner (right) table are returned and for outer (left) table the rows which not meet the join conditions are returned as NULL

##	What is a CURSOR and do you think they are good or bad?
A Pointer used for row by row operations. There is not absolute right or wrong answer. HOWEVER hopefully they will say that sequential programming is never good on a DBMS as you lose all the power of Set Theory. The only justification for cursors would be for things like calculating interest on individual customer accounts.
It is attempting to force set-based technology into non-set based functionality.  
http://stackoverflow.com/questions/58141/why-is-it-considered-bad-practice-to-use-cursors-in-sql-server

## You have a table called EMPLOYEES. Describe 2 ways you could return a unique list of names
Looking for them to mention distinct keyword and then the group by clause or a union
select distinct(name) from EMPLOYEES
select name from EMPLOYEES group by Name
select name from EMPLOYEES union select name from EMPLOYESS

##	What is the difference between Chained and Unchained mode with working with transactions?
Chained mode : “begin transaction” is implicitly executed and the transaction remains open until commit or rollback received. 
Unchained mode : treats each statement as a transaction but not multiple ones unless you explicitly wrap them in a begin and commit/rollback

##	What happens if you execute ROLLBACK TRANSACTION, in Unchained mode, without having specified a BEGIN TRANSACTION?
Nothing is rolled back

##	What are Clustered and Non-Clustered indexes, how many of each could you have?
Only 1 clustered index per table. When you create a clustered index on a table, all the rows are physically stored in the order of the clustered index key. This means inserts can be slower if the table is being physically reordered, however it also reduces hot spots / contention at the end of the table if multiple processes are doing inserts. 
Non-clustered indexes have their own storage separate from the table data storage. Non-clustered indexes are stored as B-tree structures (so do clustered indexes), with the leaf level nodes having the index key and it's row locater

##	If you run CREATE INDEX without specifying an index type, what type would you actually get?
Non-clustered

##	Does a Clustered Index have to be unique?
No

##	What is Index Covering, or a Covering Index?
Index covering is a mechanism for using the leaf level of nonclustered index the way the data page of a clustered index would work. Index covering occurs when all columns referenced in a query are contained in the index itself.

##	Have you come across Update Statistics and Rebuild Indexes, and how often should they be run? 
Performance. To allow the optimiser to make the best possible plan decisions when deciding how to execute a query. It needs the most up to date stats to be able to make the correct descisions. Rebuilding indexes allows space to be reclaimed, by reducing fragmentation of the index.
How often depends on how often the table is changing, just looking for some sign of logical thought

##	Describe 3 different types of table locks?
Intent lock: indicates that page-level or row-level locks are currently held on a table. Adaptive Server applies an intent table lock with each shared or exclusive page or row lock, so an intent lock can be either an exclusive lock or a shared lock. Setting an intent lock prevents other transactions from subsequently acquiring conflicting table-level locks on the table that contains that locked page. An intent lock is held as long as page or row locks are in effect for the transaction.
Shared Lock: This lock is similar to a shared page or lock, except that it affects the entire table. For example, Adaptive Server applies a shared table lock for a select command with a holdlock clause if the command does not use an index. A create nonclustered index command also acquires a shared table lock.
Exclusive Lock: This lock is similar to an exclusive page or row lock, except it affects the entire table. For example, Adaptive Server applies an exclusive table lock during a create clustered index command. update and delete statements require exclusive table locks if their search arguments do not reference indexed columns of the object.

##	List the 4 types of Isolation levels, and when would you use them? 
ANSI Categories of locking behavior within a transaction :-
0 – read uncommitted
1 – read committed (default)
2 – repeatable read
3 – serializable

## How can select our Yesterday’s date? 
select dateadd (dd, -1, getdate() )

## What is a Log Suspend? What causes it?
When the log of a particular database fills up due to excessive insert/delete/update commands all being logged by running processes. Doing an update of 10 million rows in one transaction may cause a log suspend as all 10 million updates need to be logged to allow for a rollback to take place if required.

## Followup If Right : Why can one long running query writing to tempdb cause the tempdb log to fill up, even if the longest running process is just Sleeping doing IO?
There may be many other processes running at the same time all accessing tempdb. All their actions are logged and can’t be cleared out of the log until the longest running open transaction in tempdb has completed.

## Difference between logical and physical reads, which are faster?
Logical comes from Cache physical from disk. Logical faster.

## A lengthy stored proc is performing badly. It contains many INSERTS, DELETES, and UPDATES. All of the FROM clauses contain 10+ tables. How would you go about determining which SQL statement is causing the worst performance? How would you improve performance?
Indexes, showplan, re-write code, reduce number of table joins, introduce temp tables, take code blocks out to test on their own, schema redesign. 

Additonal questions (optional)

## What is the Procedure Cache and Data Cache?
Procedure cache is the memory area where compiled query trees run e.g procedure batch query. 
Data Cache is the memory area where data that is required for the current querys running is bought from Disk on to memory for building result sets.

## Aside from re-compiling a stored procedure, how else could you get a new query plan into the procedure cache.
When the second execution of a procedure is started while the same procedure is already running the optimizer recreates a new query plan in the QP cache for the second execution. This is not guaranteed to be the same as the first one.

IQ Questions

## SYBASE IQ : What are the main types of indexes you have come across in IQ?
HG, LF, FP1 FP2 FP3 etc
