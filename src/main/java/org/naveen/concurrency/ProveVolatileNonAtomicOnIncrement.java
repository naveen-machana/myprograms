package org.naveen.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ProveVolatileNonAtomicOnIncrement {
	
	private static volatile int x = 0;
	
	public ProveVolatileNonAtomicOnIncrement() {

	}

	public static void main(String[] args) throws InterruptedException {
		initiateIncrementTasks();
	}
	
	private static void initiateIncrementTasks() throws InterruptedException {
		Thread[] threads = new Thread[5];
		CountDownLatch startGate = new CountDownLatch(1);
		CountDownLatch endGate = new CountDownLatch(threads.length);
		System.out.println("Creating threads");
		for (int i = 0; i < 5; i++) {
			TimeUnit.SECONDS.sleep(1);
			threads[i] = new Thread(new IncrementRunnable(startGate, endGate));
			threads[i].start();
			System.out.println("Thread " + threads[i] + " is created and started.");
		}
		System.out.println("Opening start gate.");
		startGate.countDown();
		System.out.println("Main thread waiting for all threads to complete increment operation.");
		endGate.await();
		
		System.out.println("Volatile variable's current value after "
				+ "5 threads, each increment x 100 times: " + x);
		System.out.println("Result of volatile should be 500, and current value is " + x);
		
	}
	
	private static class IncrementRunnable implements Runnable {
		private final CountDownLatch startGate;
		private final CountDownLatch endGate;
		
		IncrementRunnable(CountDownLatch startGate, CountDownLatch endGate) {
			this.startGate = startGate;
			this.endGate = endGate;
		}
		
		public void run() {
			try {
				//System.out.println(Thread.currentThread() + " is waiting for start gate to open");
				startGate.await();
				for (int i = 0; i < 100; i++) {
					x++;				
				}				
				//System.out.println(Thread.currentThread() + " completed increment operation.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				endGate.countDown();
			}
			
		}
	}

}
