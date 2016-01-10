package org.naveen.interviews;

public class ReturnValueTesting {
	
	public static int returnValue() {
		try {
			int i = 2/0;			
			return 1;
		}
		catch (Exception e) {
			return 'a';
		}
		finally {
			System.out.println("In finally");
			return 3;
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(returnValue());
		System.out.println(returnValueWithNoCatch());
	}
	
	public static int returnValueWithNoCatch() {
		try {
			int i = 20;
			i = 20/ 0;
			return i;
		}
		
		finally {
			return 10;
		}
		
	}

}
