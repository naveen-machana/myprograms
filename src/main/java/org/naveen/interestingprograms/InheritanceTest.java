package org.naveen.interestingprograms;

public class InheritanceTest {
	
	private static class A {
		public void method() {
			System.out.println("A");
		}
	}
	
	private static class B extends A {
		public void method() {
			System.out.println("B");
		}
	}
	
	public static void main(String[] args) {
		//B b = new A();
	}
	
	interface a {
		float PI = 3.14f;
		default void method() {
			System.out.println("a");
		}
	}
	
	interface b {		
		default void method() {
			System.out.println("b");
		}
		static void method1() {
			
		}
	}
	
	private static class implclass extends superclass implements a, b {
		public void method() {
			b.method1();
		}
	}
	
	private static class superclass{
		public void method() {
			System.out.println("superclass");
		}
	}
	
	interface c extends b {
		default void method() {
			System.out.println("c");
		}
	}
}
