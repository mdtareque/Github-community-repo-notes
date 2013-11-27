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


## Vim

### Aligning data in a tabular/columnar format \[[http://vim.wikia.com/wiki/VimTip894]\]

Adding this to a file _align.vim_ and including in _.vimrc_ as

    so align.vim
    
Then after select the lines type

    :Align <character-to-align-on>


    command! -nargs=? -range Align <line1>,<line2>call AlignSection('<args>')
    vnoremap <silent> <Leader>a :Align<CR>
    function! AlignSection(regex) range
    let extra = 1
    let sep = empty(a:regex) ? '=' : a:regex
    let maxpos = 0
    let section = getline(a:firstline, a:lastline)
    for line in section
    let pos = match(line, ' *'.sep)
    if maxpos < pos
    let maxpos = pos
    endif
    endfor
    call map(section, 'AlignLine(v:val, sep, maxpos, extra)')
    call setline(a:firstline, section)
    endfunction
     
    function! AlignLine(line, sep, maxpos, extra)
    let m = matchlist(a:line, '\(.\-\) \-\('.a:sep.'.*\)')
    if empty(m)
    return a:line
    endif
    let spaces = repeat(' ', a:maxpos - strlen(m[1]) + a:extra)
    return m[1] . spaces . m[2]
    endfunction


### Aligning selected lines or the current line to left i.e. removing all the spaces and tabs at the start of line(s)

    :le

### SQL color

SQL syntax color highlighting \[[https://github.com/hoffstein/vim-tsql]\]

### .vimrc

    set ai
    if &term =~ "vt100"
    if has("terminfo")
    set t_Co=8
    set t_Sf=^[[3%p1%dm
    set t_Sb=^[[4%p1%dm
    else
    set t_Co=8
    set t_Sf=^[[3%dm
    set t_Sb=^[[4%dm
    endif
    endif

    syntax on

    set backspace=indent,eol,start
    " size of hard tabstop
    set tabstop=4
    " size of an indent
    set shiftwidth=4
    " a combination of spaces and tabs are used to simulate tab stops at a width
    " other than the (hard) tabstop
    set smarttab
    " always uses spaces instead of tab characters
    set expandtab

    let g:sql_type_default = "sqlserver"
    " set the filetype for other extensions
    au BufNewFile,BufRead *.inc set filetype=sqlserver
    au BufNewFile,BufRead *.sp setl ft=sql

    set <PageDown>=^[[6~
    set <PageUp>=^[[5~
    set <Home>=^[[1~
    set <End>=^[[4~
    map <Up> <c-y>
    map <Down> <c-e>

    " add double quotes around word
    nnoremap qd :silent! normal mpea"<Esc>bi"<Esc>`pl

    " add single quotes around word
    nnoremap qw :silent! normal mpea'<Esc>bi'<Esc>`pl

    " To Align lines with some character(s) in one column
    " Usage
    " Select lines in visual mode
    " Enter
    " :Al =
    " to align with =
    so ~/c/Align.vim

    " remove spaces and tabs as well at the end of line automatically while saving
    autocmd BufWritePre * :%s/\s\+$//e

### navigation

Goto last edited line
other
<ctrl>+<o> <ctrl> + ﻿<i>
g; and g,
gi or '. ( single-quote and a dot)

### copy-paste alignment

Enter :set paste before pasting to avoid long right skewing rows in case of pasting large data from other application.

### Folding

zF{n}- create folding of next{n} lines  
zF{motion} - creates folding, visual block can be used here  

zc - close the folding  
zo - open the folding

### Insert current filename into the contents

    <C-r>+% in insert mode

### Build jenkins job via shell-script


    #!/bin/bash
    token=mtk
    job_name=JobName
    jenkins_url=http://server:port
    jsonDate='json={"parameter":[{"name":"Test","value":"true"}]}&Submit=Build'

    curl --silent -u  ptpfeeds:ptpfeeds --show-error --data $jsonData $jenkins_url/job/$job_name/build?token=$token\&cause=BuiltFromShellScript

https://wiki.jenkins-ci.org/display/JENKINS/NodeLabel+Parameter+Plugin#NodeLabelParameterPlugin-Triggerviascript

## Commandline navagation keyboard shortcuts


## BCP

    bcp DBNAME..tableName out /tmp/testBcp.out -Uuser -Ppass -Sserver -c -t,
    bcp DBNAME..tableName in /tmp/testBcp.out -Uuser -Ppass -Sserver -c -t,
    
`-c` is for 
> Performs the operation using a character data type. This option does not prompt for each field; it uses char as the storage type, without prefixes and with \t (tab character) as the field separator and \r\n (newline character) as the row terminator. -c is not compatible with -w.
