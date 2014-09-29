$ amisql -Imyinterfaces -Sxxxx -Uxxxxx -Pxxxxxx
2:1> select @@version
2:2> go
                                                                                                                                                                                                                
 ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Adaptive Server Enterprise/15.7/EBF 23288 SMP SP104  ONE-OFF/P/x86_64/Enterprise Linux/ase157sp101/3449/64-bit/FBO/Mon Jul 28 18:27:57 2014                                                                    

(1 row affected)

3:1> sp_cacheconfig
3:2> go
 Cache Name         Status   Type     Config Value   Run Value
 ------------------ -------- -------- -------------- ------------
 default data cache Active   Default   27680.00 Mb    27680.00 Mb
 log_cache          Active   Log Only   1000.00 Mb     1000.00 Mb
 tempdb_cache       Active   Mixed      8550.00 Mb     8550.00 Mb
                                    ------------ ------------
                            Total    37230.0 Mb   37230.0 Mb
==========================================================================
Cache: default data cache,   Status: Active,   Type: Default
      Config Size: 27680.00 Mb,   Run Size: 27680.00 Mb
      Config Replacement: strict LRU,   Run Replacement: strict LRU
      Config Partition:            4,   Run Partition:            4
 IO Size  Wash Size     Config Size  Run Size     APF Percent
 -------- ------------- ------------ ------------ -----------
     2 Kb     245760 Kb  19875.00 Mb  19875.00 Mb     10
    16 Kb     245760 Kb   7805.00 Mb   7805.00 Mb     10
==========================================================================
Cache: log_cache,   Status: Active,   Type: Log Only
      Config Size: 1000.00 Mb,   Run Size: 1000.00 Mb
      Config Replacement: relaxed LRU,   Run Replacement: relaxed LRU
      Config Partition:            4,   Run Partition:            4
 IO Size  Wash Size     Config Size  Run Size     APF Percent
 -------- ------------- ------------ ------------ -----------
     2 Kb        408 Kb      2.00 Mb      2.00 Mb     10
     4 Kb     204384 Kb    998.00 Mb    998.00 Mb     10
==========================================================================
Cache: tempdb_cache,   Status: Active,   Type: Mixed
      Config Size: 8550.00 Mb,   Run Size: 8550.00 Mb
      Config Replacement: relaxed LRU,   Run Replacement: relaxed LRU
      Config Partition:            4,   Run Partition:            4
 IO Size  Wash Size     Config Size  Run Size     APF Percent
 -------- ------------- ------------ ------------ -----------
     2 Kb     245760 Kb   8550.00 Mb   8550.00 Mb     10

(return status = 0)

4:1> sp_helpconfig "max memory"
4:2> go

max memory sets the maximum size of memory, in 2K units, that ASE can allocate.

 Minimum Value   Maximum Value   Default Value   Current Value   Memory Used   Unit             Type
 --------------- --------------- --------------- --------------- ------------- ---------------- -------
           98304      2147483647           98304        27566080    55132160   memory pages(2k) dynamic


(return status = 0)

5:1> sp_helpconfig "engines at startup"
5:2> go
Configuration option is not unique.
 option_name                    config_value run_value
 ------------------------------ ------------ -----------
 number of engines at startup             4            4
 number of Q engines at startup           0            0

(return status = 1)
6:1> sp_helpconfig "procedure cache"
6:2> go

procedure cache size specifies the amount of memory allocated to the procedure cache.

 Minimum Value   Maximum Value   Default Value   Current Value   Memory Used   Unit             Type
 --------------- --------------- --------------- --------------- ------------- ---------------- -------
            7000      2147483647            7000         2097152     4718788   memory pages(2k) dynamic


(return status = 0)
7:1>

8:1> sp_helpconfig [help]
8:2> go
sp_helpconfig [ 'help' ] | [ '<config option name>' [, '<memory size>' | 'estimate ...' ] ]
Examples:

Generate help for a particular config option:
  sp_helpconfig 'number of user connections'

Convert memory to-be-allocated for a config option to appropriate units:
  sp_helpconfig 'number of open objects', '2m'

Estimate the memory required for a config option given a specific current configuration / work load:
  sp_helpconfig 'compression info pool size', 'estimate'

  'estimate' clause specifiction for compression info pool size:
    estimate USING <arg> = <value> [, <arg> = <value> ...]

    Arguments supported are:
        maxconcusers:   Max # of concurrent users accessing compressed tables.
                        Default: number of user connections + number of worker processes
        numcolumns:     Ave # of columns in a compressed table
                        Default: 50, or average number of columns in compressed tables in the database
        numcompobjs:    Number of compressed objects accessed server-wide
                        Default: number of open objects, or number of compressed tables in the database
        numtables:      Ave # of compressed tables referenced per statement
                        Default: 2

    For arguments 'maxconcusers', 'numcompobjs', use 0 < value < 1 to choose a percentage of the default value.
    Examples:

    - Estimate the memory required to access all the compressed objects in database 'compdb'

        compdb..sp_helpconfig 'compression info pool size', 'estimate'

    - Estimate the memory required to access compressed objects server-wide, overriding defaults

        sp_helpconfig 'compression info pool size', 'estimate USING maxconcusers=0.8, numcompobjs = 0.2'

        sp_helpconfig 'compression info pool size', 'estimate USING maxconcusers=1000, numcolumns=25, numcompobjs = 20000'

Generate help / usage information:
  sp_helpconfig [help]

(return status = 0)
