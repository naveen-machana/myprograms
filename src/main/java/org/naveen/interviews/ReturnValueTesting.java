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
		System.out.println(returnValue());
	}

}
