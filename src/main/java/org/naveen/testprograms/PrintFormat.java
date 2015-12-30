package org.naveen.testprograms;

public class PrintFormat {

	public static void main(String[] args) {
		String[] arg = new String[]{"java 100", "cpp 65", "python 50"};
		System.out.println("============================");
		for (String s : arg) {	
			//System.out.println();
			int spaceIndex = s.indexOf(' ');
			String string = s.substring(0, spaceIndex);
			String number = s.substring(spaceIndex + 1);
			System.out.print(String.format("%-15s", string));
			System.out.print(String.format("%03d\n", Integer.parseInt(number)));
		}
		System.out.println("============================");
	}
}
