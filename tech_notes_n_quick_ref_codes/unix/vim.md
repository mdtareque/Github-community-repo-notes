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



### navigation

Goto last edited line
other
<ctrl>+<o> <ctrl> + ﻿<i>
g; and g,
gi or '. ( single-quote and a dot)

## Synchronized scrolling in splitted windows

    :set scrollbind


### copy-paste alignment

Enter :set paste before pasting to avoid long right skewing rows in case of pasting large data from other application.

### Folding

zF{n}- create folding of next{n} lines  
zF{motion} - creates folding, visual block can be used here  

zc - close the folding  
zo - open the folding

http://www.linux.com/learn/tutorials/442438-vim-tips-folding-fun


### Insert current filename into the contents

    <C-r>+% in insert mode

