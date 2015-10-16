package org.naveen.concurrency.testprograms;

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
}
