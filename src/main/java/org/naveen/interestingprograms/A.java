package org.naveen.interestingprograms;

public class A {
	private B b;
	
	void setB(B b) {
		this.b = b;
	}
	
	class B {		
	}
}

class C {
	public static void main(String[] args) {
			A a = new A();
			a.setB(a.new B());
	}
}

