## Sybase Learning
[Known sybase jConnect bugs](http://www.sybase.com/detail?id=1033283)  
[All sql operators](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.help.ase_15.0.commands/html/commands/commands98.htm)  
Sybase Migration - Changes required for 12 to 15 - [pdf](http://www.sybase.in/files/White_Papers/SY-Required-SQL-Changes-for-ASE15-v.1-073009-WP.pdf)  
[sybase error codes - one liner](http://infocenter.sybase.com/archive/index.jsp?topic=/com.sybase.infocenter.dc36584.1260/html/iqerror/Errsybcode.htm)  
[Sybase error code - in detail](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.infocenter.dc00462.1510/concepts/con_sqlcode.html)
[datetime conversion](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.infocenter.dc38151.1530/doc/html/san1278453000116.html)  
[locking and isolation](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.dc20021_1251/html/locking/locking9.htm)  
[locking](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.dc20021_1251/html/locking/locking88.htm)  
[sybase client/server](http://manuals.sybase.com/onlinebooks/group-cn/cng1250e/osref/@Generic__BookTextView/328)  

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



## Sybase general and Stored procedures facts
- TEXT datatype variable can't be passed as procedure parameters. A temp table should be included for passing the data between procedures.
- `Alter table` queries are not re-runnable.
- `DROP PROCEDURE` does not allow specifying the database name as prefix.
- `sp_describe` allows the database to be prefixed with 2-dot notation, to look for objects in the referenced database.
- `TEXT` and `IMAGE` datatypes are invalid for parameters or local variables
- Column identifier name maximum length is 30.
- Error for `if null <> null select ''` - 'Invalid operator for datatype op: != type: VOID TYPE', shows `<>` is replaced by `!=` internally
- When a `char` or `unichar` value is declared to allow nulls, Adaptive Server stores it internally as `varchar` or `univarchar`. And datalength of any `NULL` data returns `NULL`.
