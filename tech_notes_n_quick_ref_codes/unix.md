## Linux

Find top 10 directories sorted by size

    du -a . | sort -n -r | head -n 10

## Bash

### String split

    varName="SomeValueAB"
    last2chars=${varName: -2}
    removedLast2cahrs=`echo ${varName%??}`

### Increment a number
    testNumber=$(expr $testNumber + 1)

### Usage

	usage() {
	cat 1>&2 <<EndOfUsage
	Usage: $scriptName options
	Optional parameter
	Description:
	EndOfUsage
	exit
	}

### Some quick-short parameter processing

    [ "$#" \-gt "2" ] && usage # If no. of arguments greater that 2
    [ "X$1" == "X-h" ] && usage # If first argument '-h'

### Parameter Expansion and Default Values 
[Reference](http://www.debuntu.org/how-to-bash-parameter-expansion-and-default-values).

> ${parameter-word}  to use a default value if variable unset  
> ${parameter:-word} to use a default value if variable unset and empty  
> ${parameter:=word} to assign a default value  
> ${parameter:?word} to display an error if unset or null  
> ${parameter:+word} to use an alternate value

### String substring, pattern matching, substring removal 
[tldp-string manipulation](http://tldp.org/LDP/abs/html/string-manipulation.html).

	${string#substring} Deletes shortest match of $substring from front of $string.
	${string##substring} Deletes longest match of $substring from front of $string.
	${string%substring} Deletes shortest match of $substring from back of $string.
	${string%%substring} Deletes longest match of $substring from back of $string.
	${string/substring/replacement} Replace first match of $substring with $replacement.
	${string//substring/replacement} Replace all matches of $substring with $replacement.
	${string/#substring/replacement} [starts with] If $substring matches front end of $string, substitute $replacement for $substring.
	${string/%substring/replacement} [ends with] If $substring matches back end of $string, substitute $replacement for $substring.

### Change modified timestamp of a file to any time

	touch -t 201303072222 filename

### Find files older than 7 days and zip or delete

    find $dir -mtime +7 | xargs tar -czvPf archived.tar.gz 2>/dev/null
    find $dir -mtime +7 -type f -exec rm {} \; 2>/dev/null
    find $dir -mtime +7 -type d -exec rmdir {} \; 2>/dev/null

### List the jar files downloaded within 1 day. (be in the .m2 directory)

    find . -type f -name *.jar -mtime -1 | xargs ls -lrt {}
    find . -type f -name *.pom -mtime -1 | xargs ls -lrt {}


### Date manipulation
Add and subtract number of days from a given date

    date --date="-1 days ago"  -- future date
    date --date="2 days ago"  -- previous date

-OR-

Convert a timestamp to date and do the manipulations

    date -d 20130818" -17280 seconds" +%Y%m%d_%s

### Get lines/data only present in 2nd file that are not in file1, based on 1st column of file

    awk 'FNR==NR{a[$1];next};!($1 in a)' file1 file2  > /tmp/awkDiff

### scp

    scp /tmp/file.txt user@host:remote-path-to-store




## Build jenkins job via shell-script

    #!/bin/bash
    token=mtk
    job_name=JobName
    jenkins_url=http://server:port
    jsonDate='json={"parameter":[{"name":"Test","value":"true"}]}&Submit=Build'
    curl --silent -u  user:pass --show-error --data $jsonData $jenkins_url/job/$job_name/build?token=$token\&cause=BuiltFromShellScript

https://wiki.jenkins-ci.org/display/JENKINS/NodeLabel+Parameter+Plugin#NodeLabelParameterPlugin-Triggerviascript


### Get lines/data only present in 2nd file that are not in file1, based on 1st column of file

    awk 'FNR==NR{a[$1];next};!($1 in a)' file1 file2  > /tmp/awkDiff
    comm -3 file1 file3

### Java Thread faq on deprecated methods 
http://docs.oracle.com/javase/7/docs/technotes/guides/concurrency/threadPrimitiveDeprecation.html
Unix find number of lines in compressed file

    $ zcat file.gz | wc
    $ gunzip -c file.gz | wc

### Coding standards

https://www.owasp.org/index.php/Top_10_2010-Main

tools 
Firefox edit cookie, TamperData add-ons
HTTPProxy, fiddler

## Commandline navagation keyboard shortcuts
http://www.math.utah.edu/docs/info/features_7.html

ESC + .(dot)  gives you the last argument of the last command
Ctrl + A      Go to the beginning of the line you are currently typing on 
Ctrl + E      Go to the end of the line you are currently typing on
Ctrl + U      Clears the line before the cursor position. If you are at the end of the line, clears the entire line.
Ctrl + H      Same as backspace
Ctrl + W      Delete the word before the cursor 
Ctrl + K      Clear the line after the cursor
Ctrl + T      Swap the last two characters before the cursor 
Alt/Esc + T   Swap the last two words before the cursor 
Alt + F       Move cursor forward one word on the current line 
Alt + B       Move cursor backward one word on the current line


## BCP

    bcp DBNAME..tableName out /tmp/testBcp.out -Uuser -Ppass -Sserver -c -t,
    bcp DBNAME..tableName in /tmp/testBcp.out -Uuser -Ppass -Sserver -c -t,
    
`-c` is for 
> Performs the operation using a character data type. This option does not prompt for each field; it uses char as the storage type, without prefixes and with \t (tab character) as the field separator and \r\n (newline character) as the row terminator. -c is not compatible with -w.

### Good info and read

http://www.pixelbeat.org/programming/shell_script_mistakes.html  
http://google-styleguide.googlecode.com/svn/trunk/shell.xml  
