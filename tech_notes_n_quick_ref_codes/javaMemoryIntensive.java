public class MemoryIntensive {
	
	public static void main(String[] args) throws Exception {
		MemoryIntensive memoryTest = new MemoryIntensive();
		memoryTest.generateOOM();
	}

	public void generateOOM() throws Exception {
		int iteratorValue = 20;
		System.out.println("OOM test started..\n");
		for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
			System.out.println("Iteration " + outerIterator + " Free Mem: "
					+ Runtime.getRuntime().freeMemory());
			int loop1 = 2;
			int[] memoryFillIntVar = new int[iteratorValue];
			// feel memoryFillIntVar array in loop..
			do {
				memoryFillIntVar[loop1] = 0;
				loop1--;
			} while (loop1 > 0);
			iteratorValue = iteratorValue * 5;
			System.out.println("\nRequired Memory for next loop: "
					+ iteratorValue);
			Thread.sleep(1000);
		}
	}

}


/*

OOM test started..

Iteration 1 Free Mem: 1310461776

Required Memory for next loop: 100
Iteration 2 Free Mem: 1310461776

Required Memory for next loop: 500
Iteration 3 Free Mem: 1310461776

Required Memory for next loop: 2500
Iteration 4 Free Mem: 1310461776

Required Memory for next loop: 12500
Iteration 5 Free Mem: 1310461776

Required Memory for next loop: 62500
Iteration 6 Free Mem: 1310461776

Required Memory for next loop: 312500
Iteration 7 Free Mem: 1310461776

Required Memory for next loop: 1562500
Iteration 8 Free Mem: 1310461776

Required Memory for next loop: 7812500
Iteration 9 Free Mem: 1304211760

Required Memory for next loop: 39062500
Iteration 10 Free Mem: 1272961744

Required Memory for next loop: 195312500
Iteration 11 Free Mem: 1116711728

Required Memory for next loop: 976562500
Iteration 12 Free Mem: 335461712
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at MemoryIntensive.generateOOM(MemoryIntensive.java:15)
        at MemoryIntensive.main(MemoryIntensive.java:5)

*/
