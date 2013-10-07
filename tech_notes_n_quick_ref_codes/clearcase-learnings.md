

### Clearcase

Info of a label

    ct desc -type label
    ct describe lbtype:label

### Normal main spec

    element * CHECKEDOUT
    element * /main/LATEST


### Creating a branch
http://www.ipnom.com/ClearCase-Commands/mkbrtype.htm 

    ct mkbrtype branch_name

### Branch cleartool spec

    element * CHECKEDOUT
    element * /main/<branch_name>/LATEST
    element * /main/LATEST -mkbranch <branch_name>


### Checkout
    
    cleartool co -unr -nc fileName.sql

### Undo checkout
    
    cleartool -unco fileName.sql
