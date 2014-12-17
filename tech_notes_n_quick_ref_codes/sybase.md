## Sybase Learning
[Known sybase jConnect bugs](http://www.sybase.com/detail?id=1033283)  
[All sql operators](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.help.ase_15.0.commands/html/commands/commands98.htm)  
Sybase Migration - Changes required for 12 to 15 - [pdf](http://www.sybase.in/files/White_Papers/SY-Required-SQL-Changes-for-ASE15-v.1-073009-WP.pdf)  
[sybase error codes - one liner](http://infocenter.sybase.com/archive/index.jsp?topic=/com.sybase.infocenter.dc36584.1260/html/iqerror/Errsybcode.htm)  
[Sybase error code - in detail](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.infocenter.dc00462.1510/concepts/con_sqlcode.html)  
[jconnect info](http://infocenter.sybase.com/help/topic/com.sybase.infocenter.dc39001.0707/pdf/prjdbc0707.pdf)  
[datetime conversion](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.infocenter.dc38151.1530/doc/html/san1278453000116.html)  
[locking and isolation](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.dc20021_1251/html/locking/locking9.htm)  
[locking](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.dc20021_1251/html/locking/locking88.htm)  
[sybase client/server](http://manuals.sybase.com/onlinebooks/group-cn/cng1250e/osref/@Generic__BookTextView/328)  
[cursor timelog perfromance](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.dc20020_1251/html/databases/databases537.htm)  
[Java connection param](http://infocenter.sybase.com/help/topic/com.sybase.infocenter.dc39001.0707/pdf/prjdbc0707.pdf) (http://sap-note.com/1892774.htm) [EARLY_BATCH_READ_THRESHOLD] connection hangs for large data in jconnect 7

### General DDL and DML queries
Quick DDL

    create table t (name char(10),id int)
    alter table t modify name char(40)

Column addition by `alter table` by default adds a non-NULL column, hence required to specify a default value is nullable is not mentioned

    alter table t add c varchar(15)
> Error -> _ALTER TABLE 't' failed. Default clause is required in order to add non-NULL column 'c'._

Rename a column

    exec sp_rename
        @objname = 'tableName.columnName',
        @newname = 'NewColumnName',
        @objtype = 'COLUMN'

Typo in datatype while casting

> ErrorMsg: Operand type clash: MAINTENANCE TOKEN is incompatible with FLOAT


Add a column with a default value

    alter table MY_TABLE
    add status varchar (30) 
    default 'IN Progress' not null 
    -- dropping it
    alter table MY_TABLE drop status
 
### Better string concatenation ISO/ANSI SQL compliant

    Declare @d char(2)
    select 'test' || 'adf' || @d || ".."

#### Reverse the string

    select reverse("abcd")
Output - `dcba`

    select reverse(0x12345000)
Output - `0x00503412`

### stuff

    select stuff('12345',2,1,'-') -- "1-345"
    select stuff('12345',2,1,'---') -- "1---345"

### sp_recompile

to recompile a stored proc, or call it with recompile to generate a new plan each time as

    exec sp_name with recompile


### Any User can execute these

    sp_helpjoins table1, table2
    sp_helpkey table_name

## Permission

### Grant exec permission to proc

    GRANT EXECUTE on proc_name
    to group_name

### grant syntax

    grant insert,delete on tbl_name to user1, user2
    grant select on tbl_name to public
    
### revoke permission

    revoke all on tbl_name from public

### Only owner of tables can execute these

    sp_primarykey tabname, col1 [, col2, col3, ..., col8]
    sp_foreignkey tabname, pktabname, col1 [, col2] ... [, col8]
    sp_commonkey tabaname, tabbname, col1a, col1b [, col2a, col2b, ..., col8a, col8b]

### Case change

    select LOWER( 'cHeck CasE' )
    select UPPER( 'cHeck CasE' )

### Substring

    select LEFT( 'chocolate', 5 )
    select RIGHT( 'chocolate', 5 )

### Replicate

    select REPLICATE( 'repeat', 3 )
__Preset in 15\+__

    select UCASE, LCASE, HTML_ENCODE, REPLACE, REPEAT

### Quick value defaulting

    select @max_id = COALESCE(@max_id,(select some_field from SOME_TABLE where ...),1000)

### Get the port on which server is connected

    select * from master..syslisteners

## Server Details

### Version of sybase

    select @@version

### Name of the server

    select @@servername

### Date-time

#### Functions

    select YEAR( '1998-07-13 06:07:12' )

#### GMT Date

    select getutcdate()

#### Datetime conversion

    select CONVERT( CHAR( 20 ), getdate(), 12 )

#### Datediff and dateadd

    Declare @local_date  datetime
            ,@gmt_date   datetime
            ,@gmt_offset int
    select @local_date = getdate()
    select @gmt_date = getutcdate()

    if @local_date > @gmt_date
        select @gmt_offset = datediff(hh, dateadd(mi, 1, @local_date), @gmt_date)
    else
        select @gmt_offset = datediff(hh, @local_date, dateadd(mi, 1, @gmt_date))

    print '%1\! %2\!', @local_date, @gmt_date

##### Dateadd fine-tune

    select dateadd(ms, -1  , getDate() )  -- milli-seconds
    select dateadd(ss, -10 , getDate() )  -- seconds
    select dateadd(mi, -10 , getDate() )  -- minutes
    select dateadd(hh, -1  , getDate() )  -- hours
    select dateadd(dd, -1  , getDate() )  -- days
    select dateadd(wk, -1  , getDate() )  -- weeks
    select dateadd(mm, -1  , getDate() )  -- months
    select dateadd(yy,  1  , getDate() )  -- years

#### Date parts

    select datepart(dd,getdate())  -- day
    select datepart(dw,getdate())  -- day of week 1 - Sunday
    select datepart(wk,getdate())  -- week of year
    select datepart(dy,getdate())  -- day of year
    select datepart(qq,getdate())  -- quarter

### Casting

    select CAST( 1 + 2 AS CHAR )
    select CAST( '2000-10-31' AS DATE )

### User permissions and access on the table

Get the users group and access level on a table.

    sp_helprotect <table_name>
    sp_helpuser <user_name>

### Get dependencies

    sp_depends <table_name>

### Sybase options - query performance

    set fmtonly on
    set noexec on
    set statistics io on
    set showplan on

Options set can be seen by _@@options_ that returns a [hex output](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.help.ase_15.0.sqlug/html/sqlug/sqlug611.htm).

Other [set options](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.infocenter.dc36272.1572/html/commands/X64136.htm)  
e.g `SET FLUSHMESSAGE ON`

### space usage

    sp_spaceused tableName, [1]

### group by

	create table t (name char(10), ctry char(10), age int)
	insert into t values('a', 'IN', 22)
	insert into t values('b', 'IN', 24)
	insert into t values('c', 'IN', 30)
	insert into t values('aa', 'JP', 32)
	insert into t values('ab', 'JP', 34)
	insert into t values('ac', 'CN', 20)
	select * from t
	select ctry, count(*) as #residents, avg(age) as 'avg age'
	from t
	group by ctry
	having(count(name) > 1)

	ctry #residents avg age
	"IN " 3 25
	"JP " 2 33

### globals and test

    db_id(), db_name(), @@procid, @@spid
    -- check for existance of a required temp table
    if (object_id("SOMETABLE") is null)

### curosr

    DECLARE print_debug_vals CURSOR FOR
    SELECT debug_vals
    FROM #tmp
    
    OPEN print_debug_vals
    FETCH print_debug_vals INTO @msgStr
      
    WHILE @@sqlstatus = 0
    BEGIN
    FETCH print_debug_vals INTO @id_md
    END
    CLOSE print_debug_vals


### Testing

Test for null

    if 0.0 != null
        select 'Yes'

Raiserror

    create proc test_re
    as
    begin
        declare @t char(10)
        select @t = '123'
        raiserror 19000 'failed %1!', @t
    end


### Deadlock creation

Gather some data for playing deadlock-deadlock

	select top 10 * into t1 from SOME_TABLE
	select top 10 * into t2 from SOME_TABLE

Transaction 1 (TranA) 	
1. begin tran TranA
2. select * from t1 holdlock
3. waitfor delay "00:00:05"
4. select * from t2 holdlock
5. commit tran TranA

Transaction 2 (TranB)
1. begin tran TranB
2. update t2 set dt_trd = '20 May 2013'
3. update t1 set dt_trd = '30 May 2013'
4. commit tran TranB

1. Deadlock TranA
Run TranA first in one sql session and TranB in another sql session. TranB will encounter a deadlock

2. Deadlock TranB
1. Run ?TranA line by line till line 2.
2. In another session run TranB till line 2.
3. Then come back to TranA and run line 4.
4. Come back to TranB and run line 3.
TranA would deadlock now.


### Trick with  bits

Check whether db is offline, the 4th position bit from LSB of a field in master table says whether db is offline or not.

    select status2 & 16 from master..sysdatabases where name = 'db-name'

http://infocenter.sybase.com/archive/index.jsp?topic=/com.sybase.help.ase_15.0.tables/html/tables/tables28.htm

### display execution time of a sql stmts

    select @dt_start=getdate()
      <main-sql-stmts>
    select @dt_end = getdate()
    select @execTime = right ('00' + convert (varchar, datediff(hour, @dt_start, @dt_end)),2) + ':' +
                right ('00' + convert (varchar, datediff(minute, @dt_start, @dt_end) - datediff(hour, @dt_start, @dt_end)*60),2) + ':' +
                right ('00' + convert (varchar, datediff(second, @dt_start, @dt_end) - datediff(minute, @dt_start, @dt_end)*60), 2) + 's'
    print "Execution time for main statement %3!", @execTime 

### check locking scheme for a table

    select name, 'table uses...' = case (sysstat2 & 57344)
    when 8192 then 'allpages locking scheme'
    when 16384 then 'datapages locking scheme'
    when 32768 then 'datarows locking scheme'
    end
    from sysobjects where type = "U"

## query optiomzation

http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.dc20023_1251/html/optimizer/X17067.htm

Good query and plan example, if B's indexes could completely contained within A's, then you should see



    set showplan on
    set statistics io, time on
    go
    SELECT * FROM A a    WHERE
        (dt_create >=      '4 Apr 2013' AND dt_create < '5 Apr 2013')
    AND id_ NOT IN ( SELECT id_evt
                           FROM   B  de
                           WHERE  a.id_ = b.id_ )

> STEP 1\
        The type of query is SELECT.\
        Evaluate Ungrouped ANY AGGREGATE.\
\
        FROM TABLE\
            B\
            b\
        EXISTS TABLE : nested iteration.\
        Index : B_surrogate_KEY\
        Forward scan.\
        Positioning by key.\
        Index contains all needed columns. Base table will not be read.\
        Keys are:\
            id_  ASC\
        Using I/O Size 2 Kbytes for index leaf pages.\
        With LRU Buffer Replacement Strategy for index leaf pages.\

## Sybase general and Stored procedures facts
- TEXT datatype variable can't be passed as procedure parameters. A temp table should be included for passing the data between procedures.
- `Alter table` queries are not re-runnable.
- `DROP PROCEDURE` does not allow specifying the database name as prefix.
- `sp_describe` allows the database to be prefixed with 2-dot notation, to look for objects in the referenced database.
- `TEXT` and `IMAGE` datatypes are invalid for parameters or local variables
- Column identifier name maximum length is 30.
- Error for `if null <> null select ''` - 'Invalid operator for datatype op: != type: VOID TYPE', shows `<>` is replaced by `!=` internally
- When a `char` or `unichar` value is declared to allow nulls, Adaptive Server stores it internally as `varchar` or `univarchar`. And datalength of any `NULL` data returns `NULL`.
- When you want to futher join on other tables from a field that we get from a table from a outer join, you need to use outer join on further from the inner table again. Or else the error is 'The table 'xyz' is an inner member of an outer-join clause. This is not allowed if the table aslo participates in a regular join clause.'
- If table schema is updated, stored procs referencing it needs to be recompiled in 12.5 or else there will be a error. Or incase the columns were added, a `select *` will still give old list of columns
- a float(N) for N between 1 and 15 gives a 4-byte float. On ASE15 sp_describe shows output as `float(15) /*4-bytes*/`. Generally a plain "float" should be defined, which results in 8 bytes by default.
