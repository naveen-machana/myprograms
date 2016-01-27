package org.naveen.interestingprograms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TestProgram {
	
	interface Fruit<E extends Fruit<E>> extends Comparable<E>{}
	
	/*
	 * interface Fruit<E extends Fruit> extends Comparable<E>{} -- means Fruits can only be compared with Fruits
	 * static class Apple implements Fruit<Apple>{

		@Override
		public int compareTo(Apple o) {
			// TODO Auto-generated method stub
			return 0;
		}		
	}
	
	static class ChildApple extends Apple{
		
	}
	
	static class Orange implements Fruit<Orange> {
		@Override
		public int compareTo(Orange o) {
			return 0;
		}
		
	}
	 * */	
	
	
	/* interface Fruit<E> extends Comparable<E>{} -- means type E's are being compared, but not fruits
	1. class ListFruits implements Fruit<List>{

		@Override
		public int compareTo(List o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	2. E can also be a Fruit, but it need not to be
	class ListFruits implements Fruit<ListFruits> {

		@Override
		public int compareTo(ListFruits o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}*/
	static class Apple implements Fruit<Apple>{

		@Override
		public int compareTo(Apple o) {
			// TODO Auto-generated method stub
			return 0;
		}		
	}
	
	static class ChildApple extends Apple{
		
	}
	
	static class Orange implements Fruit<Orange> {
		@Override
		public int compareTo(Orange o) {
			return 0;
		}
		
	}
	
	public static void main(String[] args) {
		
		Fruit<Apple> apple = new Apple();
		Fruit<Orange> orange = new Orange();
		ChildApple cApple = new ChildApple();
		
		apple.compareTo(cApple);
		
/*		List<Apple> apples = new ArrayList<>();
		List<Orange> oranges = new ArrayList<>();
		List<Fruit> fruits = new ArrayList<Fruit>();
		fruits.addAll(apples);
		fruits.addAll(oranges);
		
		TestProgram.max(apples);
*/		// Apple extends Comparable<? super Apple> 
		// Apple extends Comparable<Fruit> 
		// Comparable<Fruit> subtype of Comparable<? super Apple>
		// T extends Comparable<Fruit>
		
	}
	
	public static <T extends Comparable<? super T>> T max(Collection<? extends T> c) {
		T max = null;
		Iterator<? extends T> iterator = c.iterator();
		max = iterator.next();
		while (iterator.hasNext()) {
			T that = iterator.next();
			max = max.compareTo(that) < 0 ? that : max;
		}
		return max;
		
	}
	
	public static <T> T max(Collection<? extends T> c, Comparator<? super T> comparator) {
		T max = null;
		Iterator<? extends T> iterator = c.iterator();
		max = iterator.next();
		while (iterator.hasNext()) {
			T that = iterator.next();
			max = comparator.compare(max, that) < 0 ? that : max;
		}
		return max;
		
	}
	
	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		return new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
			
		};
	}
	
	public static <T> Comparator<T> reverseComparator(final Comparator<T> cmp) {
		
		return new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return cmp.compare(o2, o1);
			}
			
		};
	}
	
	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		return new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				return o2.compareTo(o1);
			}			
		};
	}
	
	public static <T> T min(Collection<? extends T> c, Comparator<? super T> cmp) {
		return max(c, reverseComparator(cmp));
	}
	
	public static <T extends Comparable<? super T>> T min(Collection<? extends T> c) {
		Comparator<T> cmp = TestProgram.<T>reverseOrder();
		return max(c);
	}
	
	
	
	
}
