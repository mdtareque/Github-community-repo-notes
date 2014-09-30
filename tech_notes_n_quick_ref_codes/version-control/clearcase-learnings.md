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

### Check in

    cleartool ci -c 'comment' fileName.sql

### Edit a checken in comment

    cleartool chevent –replace –c "NEW_COMMENT" ELEMENT_NAME

### See the checkouts done in current dir

    cleartool lsco

Diff two files or just with the predecessor

    ct diff fileVersion2 fileVersion1
    ct diff -pred fileVersion2

### Keyword substitution or Keyword expansion
> $Author:$  
$Date:$  
$Header:$  
$Id:$  
$Name:$  
$Revision:$  
$Source:$

Reference - http://www.ibm.com/developerworks/rational/library/05/1213_diebolt/

### Label

Apply label

    ct mklabel label_name fileName_with_exact_version

Remove a label

    ct rmlabel label_name fileName_with_exact_version

Find procs with labels

    cleartool find . -version 'lbtype(<label>)' -print


### View

Create a dynamic view

    ct mkview -tag view_name -stgloc -auto

Remove a view

    ct rmview view_name

Set a view

    ct setview view_name

### Create a new file

    ct co -unr -nc . (parent directory)
    ct mkelem -nc new_file_name
    ct ci -c 'comment' new_file_name
    ct ci -c 'comment' . (parent directory)


clearcase find command examles  
http://www-01.ibm.com/support/docview.wss?uid=swg21124425  
clearcase general info on config spec, comments  
http://publib.boulder.ibm.com/infocenter/cchelp/v7r0m0/index.jsp?topic=/com.ibm.rational.clearcase.cc_ref.doc/topics/config_spec.htm  
isql  
http://infocenter.sybase.com/help/index.jsp?topic=/com.sybase.infocenter.dc35456.1550/html/ocspsunx/X33477.htm  


### New file created on Branch leaving directory unsynced. Directory merge needed.

Set config-spec to main/latest
Find what requires to be merged, if any

    ct findmerge . -typ d -fver '{lbtype(label-name)}' -whynot -print
    Needs Merge "." [to ver from ver base ver]

If anything found with 'Needs Merge', then run the below to actually merge

    ct findmerge . -typ d -fver '{lbtype(label-name)}'  -merge  -abort -exec 'cleartool ci -nc $CLEARCASE_PN'

Add comments, and check the logfile created given in output. Re-run step 1 to ensure nothing is remaining.
