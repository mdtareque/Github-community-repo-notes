## Key points

    public class ThreadCreationTimeIncreasesWithNumberOfThreads {
        public static void main(String[] args) {
            long threadCreationTime = 0;
            while (true) {
                long time = System.currentTimeMillis();
                new Thread() {
                    public void run() {
                    }
                }.start();
                time = System.currentTimeMillis() - time;
                if (time > threadCreationTime) {
                    System.out.println("Thread created in " + time + "ms.");
                    threadCreationTime = time;
                }
            }
        }
    }


Heap core dump and analysis tools

http://docs.oracle.com/javase/7/docs/webnotes/tsg/TSG-VM/html/memleaks.html  
http://docs.oracle.com/javase/7/docs/technotes/guides/visualvm/jmx_connections.html  
http://docs.oracle.com/javase/7/docs/webnotes/tsg/TSG-VM/html/tooldescr.html#gblfh  
https://blog.codecentric.de/en/2008/07/memory-analysis-part-1-obtaining-a-java-heapdump/
http://docs.oracle.com/javase/7/docs/technotes/guides/management/agent.html  
https://weblogs.java.net/blog/kellyohair/archive/2005/09/heap_dump_snaps.html  
http://help.eclipse.org/juno/index.jsp?topic=%2Forg.eclipse.mat.ui.help%2Ftasks%2Facquiringheapdump.html  

Info

http://avricot.com/blog/index.php?post/2010/05/03/Get-started-with-java-JVM-memory-%28heap%2C-stack%2C-xss-xms-xmx-xmn...%29  
http://docs.oracle.com/cd/E13150_01/jrockit_jvm/jrockit/jrdocs/refman/optionX.html  
http://javahowto.blogspot.in/2006/06/6-common-errors-in-setting-java-heap.html  
