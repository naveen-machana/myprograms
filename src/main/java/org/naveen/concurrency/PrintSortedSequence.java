package org.naveen.concurrency;

import java.util.concurrent.ConcurrentSkipListSet;

public class PrintSortedSequence {

	public static void main(String[] args) throws InterruptedException {
		ConcurrentSkipListSet<Item> set = new ConcurrentSkipListSet<Item>();
		int noThreads = 6;
		int maxNumberToBePrinted = 100;
		
		Thread[] threads = new Thread[noThreads];
		
		for (int i = 0; i < noThreads; i++) {
			threads[i] = new Thread(new PrintRunnable(i+1, noThreads, set, maxNumberToBePrinted));
			threads[i].start();
		}
		
		for (int i = 0; i < noThreads; i++) {
			threads[i].join();
		}
		
		for (Item i : set) {
			System.out.println(i);
		}
	}

}

class PrintRunnable implements Runnable {
	private final int threadId;
	private final int noOfThreads;
	private final ConcurrentSkipListSet<Item> set;
	private int currentCount;
	private final int maxNumberToBePrinted;
	
	PrintRunnable(int threadId, int noOfThreads, ConcurrentSkipListSet<Item> set, int maxNumberToBePrinted) {
		this.set = set;
		this.noOfThreads = noOfThreads;
		this.threadId = threadId;		
		this.maxNumberToBePrinted = maxNumberToBePrinted;
	}
	
	public void run() {
		int startFrom = 1 + (threadId * (threadId - 1))/2;
		int nextStep = (noOfThreads * (noOfThreads + 1))/2 - threadId+1;
		
		for (currentCount = startFrom; currentCount <= maxNumberToBePrinted; currentCount--, currentCount += nextStep) {
			
			for (int currentIterationMax = currentCount + threadId;
					currentCount < currentIterationMax 
					&& currentCount <= maxNumberToBePrinted; currentCount++) {
				set.add(new Item(threadId, currentCount));
			}
		}
	}
}


class Item implements Comparable<Item> {
	private final int threadId;
	private final int number;
	
	Item(int threadId, int number) {
		this.threadId = threadId;
		this.number = number;
	}
	
	@Override
	public int hashCode() {
		int r = 31;
		r = r * 17 + threadId;
		r = r * 17 + number;
		return r;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Item)) return false;
		Item other = (Item)o;
		
		return this.threadId == other.threadId 
				&& this.number == other.number;
	}
	
	@Override
	public int compareTo(Item o) {
		return number - o.number;
	}
	
	@Override
	public String toString() {
		return "[threadId: " + threadId + ", number: " + number + "]"; 
	}
	
}