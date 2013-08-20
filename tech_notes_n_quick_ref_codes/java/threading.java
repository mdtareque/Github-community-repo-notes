import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Threading {

	public static void main(String[] args) {
		new Threading().doRun();
	}
	
	public void doRun(){
		 ProcCall p = new ProcCall();
		 Thread t = new Thread(p);
		 t.start();
//		 We will store the threads so that we can check if they are done

		int max_thread = 10;
		Vector<Thread> threadsStarted = new Vector<Thread>();
		List<Thread> threads = new ArrayList<Thread>();
		// We will create 500 threads
		for (int i = 0; i < 20; i++) {
			if (threadsStarted.size() < max_thread) {
				Runnable task = new MyRunnable(1000000000L + i);
				Thread worker = new Thread(task);
				// We can set the name of the thread
				worker.setName(String.valueOf(i));
				// Start the thread, never call method run() direct
				threadsStarted.add(worker);
				worker.start();
				threads.add(worker);
			} else {
				System.out
						.println("All available threads used (waiting on their completion)");
				System.out.println("No of active threads.... "
						+ threadsStarted.size());
				for (int j = 0; j < threadsStarted.size(); j++) {
					System.out.println("Joining thread at position " + j);
						try {
							threadsStarted.elementAt(j).join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Interrupted.. may be the thread completed in mean while.");
						}
				}
				for (int j = 0; j < threadsStarted.size(); j++) {
					System.out.println("Name "
							+ threadsStarted.elementAt(j).getName());
				}
				threadsStarted.clear();

				Runnable task = new MyRunnable(10L + i);
				Thread worker = new Thread(task);
				// We can set the name of the thread
				worker.setName(String.valueOf(i));
				// Start the thread, never call method run() direct
				threadsStarted.add(worker);
				worker.start();
				threads.add(worker);
			}
			// Remember the thread for later usage
		}
		int running = 0;
		do {
			running = 0;
			for (Thread thread : threadsStarted) {
				if (thread.isAlive()) {
					running++;
				}
			}
			// System.out.println("We have " + running + " running threads. ");
		} while (running > 0);

	}

	class ProcCall implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Hi");
		}

	}

	class MyRunnable implements Runnable {
		private long	countUntil;

		MyRunnable(long countUntil) {
			this.countUntil = countUntil;
		}

		@Override
		public void run() {
			long sum = 0;
			if (Integer.parseInt(Thread.currentThread().getName()) < 13) {
				countUntil = 100000L;
			}
			for (long i = 1; i < countUntil; i++) {
				sum += i;
			}
			System.out.println("Thread.." + Thread.currentThread().getName()
					+ ".. " + sum);
		}
	}
}
