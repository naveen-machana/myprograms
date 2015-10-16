package org.naveen.concurrency.testprograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Factorizer {
	
	public interface Computable<A, V> {
		V get(A a);
	}
	
	public static class Memoizer<A, V> implements Computable<A, V> {
		private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();
		private final Computable<A, V> task;
		private volatile AtomicInteger count = new AtomicInteger();
		private volatile AtomicInteger hitCount = new AtomicInteger();
		private volatile AtomicInteger cancelledCount = new AtomicInteger();
		
		public Memoizer(Computable<A, V> task) {
			this.task = task;
		}
		
		public V get(A a) {
			while (true) {				
				Future<V> f = cache.get(a);
				if (f == null) {	
					
					FutureTask<V> task = newTaskFor(a);
					f = cache.putIfAbsent(a, task);
					if (f == null) {count.incrementAndGet(); f = task; task.run();}					
				}
				else {
					hitCount.incrementAndGet();
				}
				try {
					return f.get();
				} catch (CancellationException e){
					cache.remove(a, f);
				} catch (ExecutionException e) {
					throw new RuntimeException(e);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		
		public int noOfTasksFoundInCache() {
			return hitCount.get();
		}
		
		public int noOfExecutedTasks() {
			return count.get();
		}
		
		private FutureTask<V> newTaskFor(A a) {
			
			Callable<V> c = new Callable<V>() {

				@Override
				public V call() throws Exception {
					return task.get(a);
				}
				
			};
			FutureTask<V> task = new FutureTask<>(c);
			return task;
		}
	}	
	
	private static class Int2String implements Computable<Integer, String> {

		@Override
		public String get(Integer a) {
			return String.valueOf(a);
		}
		
	}
	
	public static void testMemoizer() {
		Int2String converter = new Int2String();
		Memoizer<Integer, String> cache = new Memoizer<>(converter);
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletionService<String> exeCompletionService = new ExecutorCompletionService<>(executor);
		
		List<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			Callable<String> task = newTaskForRandom1to1000(cache);
			results.add(exeCompletionService.submit(task));
		}		
		
		int exceptionCount = 0;
		for (int i = 0; i < 100000; i++) {
			try {
				exeCompletionService.take();
			} catch (InterruptedException e) {
				exceptionCount ++;
			}		
		}
		
		System.out.println("Number of executed tasks: " + cache.noOfExecutedTasks());
		System.out.println("Number of tasks found in cache: " + cache.noOfTasksFoundInCache());
		System.out.println("Number of tasks found in cache: " + exceptionCount);

	}
	
	private static Random numberGenerator = new Random();
	private static TestInt2String newTaskForRandom1to1000(Memoizer<Integer, String> cache) {
		int x = numberGenerator.nextInt(1001);
		return new TestInt2String(x, cache);
	}
	
	private static class TestInt2String implements Callable<String> {
		private final int i;
		private final Memoizer<Integer, String> cache;
		
		public TestInt2String(int i, Memoizer<Integer, String> cache) {
			this.i = i;
			this.cache = cache;
		}
		
		@Override
		public String call() throws Exception {
			return cache.get(i);
		}
		
	}
	
	public static void main(String[] args) {
		testMemoizer();		
	}
}

