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
