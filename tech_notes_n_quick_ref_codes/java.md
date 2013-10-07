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
