##General DDL and DML queries
### Quick DDL

    create table t (name char(10),id int
    alter table t modify name char(40)

## Sybase Learning
[Known sybase jConnect bugs](http://www.sybase.com/detail?id=1033283)  
[All sql operators](http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.help.ase_15.0.commands/html/commands/commands98.htm)

#### Better string concatenation ISO/ANSI SQL compliant

    Declare @d char(2)
    select 'test' || 'adf' || @d || ".."

#### Reverse the string

    select reverse("abcd")
Output - `dcba`

    select reverse(0x12345000)
Output - `0x00503412`

## Sybase general and Stored procedures facts
- TEXT datatype variable can't be passed as procedure parameters. A temp table should be included for passing the data between procedures.
- `Alter table` queries are not re-runnable.
