import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

 
public class MyDeadlock {
 
    String str1 = "Java";
    String str2 = "UNIX";
     
    Thread trd1 = new Thread("My Thread 1"){
        public void run(){
            while(true){
                synchronized(str1){
                    synchronized(str2){
                        System.out.println(str1 + str2);
                    }
                }
            }
        }
    };
     
    Thread trd2 = new Thread("My Thread 2"){
        public void run(){
            while(true){
                synchronized(str2){
                    synchronized(str1){
                        System.out.println(str2 + str1);
                    }
                }
            }
        }
    };
     
    public static void main(String a[]){
        MyDeadlock mdl = new MyDeadlock();
        mdl.trd1.start();
        mdl.trd2.start();
        
        // Find and Display deadlocks
        ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
        long[] ids = tmx.findDeadlockedThreads();
        if (ids != null) {
           ThreadInfo[] infos = tmx.getThreadInfo(ids, true, true);
           System.out.println("The following threads are deadlocked:");
           for (ThreadInfo ti : infos) {
              System.out.println(ti);
           }
        }
    }
}

/*
Sample output
JavaUNIX
JavaUNIX
JavaUNIX
JavaUNIX
The following threads are deadlocked:
"My Thread 2" Id=10 BLOCKED on java.lang.String@3d4b7453 owned by "My Thread 1" Id=9
	at MyDeadlock$2.run(MyDeadlock.java:28)
	-  blocked on java.lang.String@3d4b7453
	-  locked java.lang.String@67f1fba0


"My Thread 1" Id=9 BLOCKED on java.lang.String@67f1fba0 owned by "My Thread 2" Id=10
	at MyDeadlock$1.run(MyDeadlock.java:16)
	-  blocked on java.lang.String@67f1fba0
	-  locked java.lang.String@3d4b7453

*/
