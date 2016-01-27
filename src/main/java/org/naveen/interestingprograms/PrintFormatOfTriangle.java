package org.naveen.interestingprograms;

public class PrintFormatOfTriangle {

	public static void main(String[] args) {
		
		int[][] a = {{1}, {2, 3}, {4,5,6,7}, {8,9,10,11,12,13,14,15,16}};
		
		int i, j;
		int n = a.length;

		for (i = 0, j = n-1; i < j; i++, j--) {
			for (int k = 0; k < a[i].length; k++)
				System.out.print(a[i][k] + " ");
			for (int k = a[j].length-1; k >=0; k--)
				System.out.print(a[j][k] + " ");
		}

		if (a.length % 2 != 0) {
			for (int k = 0; k < a[i].length; k++) {
				System.out.println(a[i][k] + " ");
			}
		}
	}

}
