package org.naveen.interviews;

public class DifferentStringsWithSameHash {

	public static void main(String[] args) {
		String one = "FB";
		String two = "Ea";
		
		StringBuilder builder = new StringBuilder();
		builder.append("F int code: ").append(((int)'F'))
			   .append("\nB int code: ").append(((int)'B'))
			   .append("\nE int code: ").append(((int)'E'))
			   .append("\na int code: ").append(((int)'a'));
		
		/*		 
		    F = 70, B = 66, E = 69, a = 97
		    70 * 31 + 66 = 2236
		    69 * 31 + 97 = 2236		  
		*/
		
		System.out.println(builder.toString());
		
		System.out.println(one + " : " + one.hashCode());
		System.out.println(two + " : " + two.hashCode());
	}

}
