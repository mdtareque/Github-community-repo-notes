1. 		
    Scanner scanner = new Scanner("Sue,4,tr,6").useDelimiter(",");
    int age = scanner.nextInt();

 java.util.InputMismatchException

2. 
    System.out.format("%c\n", "x");
    System.out.format("%f\n", 123); 
    System.out.format("%d\n", 123.45);

 java.util.IllegalFormatConversionException: f != java.lang.Integer
 
3.
    new Long("123.3")
 
 java.lang.NumberFormatException: For input string: "123.3"

4.
    System.out.format(">%-0+,15d<", 32);

Exception in thread "main" java.util.IllegalFormatFlagsException: Flags = '-+0,'

5.
    Thread t = new Thread();
	t.start();
	t.wait(10000);

Exception in thread "main" java.lang.IllegalMonitorStateException

6. 
    Thread t = new Thread(MyThread);
    t.start();
    t.start(); // < --
   
Exception in thread "main" java.lang.IllegalThreadStateException
