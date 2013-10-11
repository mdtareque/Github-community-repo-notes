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

> _${parameter{_}{*}_:\-_{*}{_}word}_ to use a default value  
_${parameter{_}{*}_:=_{*}{_}word}_ to assign a default value  
_${parameter{_}{*}_:?_{*}{_}word}_ to display an error if unset or null  
_${parameter{_}{*}_:\+_{*}{_}word}_ to use an alternate value

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

### Date manipulation
Add and subtract number of days from a given date

    date --date="-1 days ago"  -- future date
    date --date="2 days ago"  -- previous date

-OR-

Convert a timestamp to date and do the manipulations

    date -d 20130818" -17280 seconds" +%Y%m%d_%s

### Build jenkins job via shell-script


token=mtk
job_name=JobName
jenkins_url=http://server:port
jsonDate='json={"parameter":[{"name":"Test","value":"true"}]}&Submit=Build'

curl --silent -u  ptpfeeds:ptpfeeds --show-error --data $jsonData $jenkins_url/job/$job_name/build?token=$token\&cause=BuiltFromShellScript

## Commandline navagation keyboard shortcuts
