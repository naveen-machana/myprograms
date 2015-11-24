package org.naveen.interviews;

import java.util.concurrent.CountDownLatch;

public class SureDeadlock {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		try {
			System.out.println("Before await");
			latch.await();
			System.out.println("After await");
		} catch (InterruptedException e) {
			System.out.println("In catch block");
			e.printStackTrace();
		}
	}

}
