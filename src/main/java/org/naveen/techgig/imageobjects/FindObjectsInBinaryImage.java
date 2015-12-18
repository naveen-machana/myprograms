package org.naveen.techgig.imageobjects;

public class FindObjectsInBinaryImage {
	
	private static boolean mark(int[][] a, int i, int j, int m, int n, int c) {
		if (i >= m || j >= n || i < 0 || j < 0)
			return false;

		if (a[i][j] != 1)
			return false;
		
		boolean flag = false;

		if (a[i][j] == 1) {
			if (j > 0 && a[i][j-1] >= 1) {
				a[i][j] = c; flag = true;
				if (a[i][j-1] == 1)
					mark(a, i, j - 1, m, n, c);
			}
			if (j < n-1 && a[i][j+1] >= 1) {
				a[i][j] = c; flag = true;
				if (a[i][j+1] == 1)
					mark(a, i, j + 1, m, n, c);
			}
			if (i > 0 && a[i-1][j] >= 1) {
				a[i][j] = c; flag = true;
				if (a[i-1][j] == 1)
					mark(a, i - 1, j, m, n, c);
			}
			if (i  < m-1 && a[i+1][j] >= 1) {
				a[i][j] = c; flag = true;
				if (a[i+1][j] == 1)
					mark(a, i + 1, j, m, n, c);
			}
		}
		return flag;
	}
	
	public static int findNumberOfObjects(int[][] a){
		int m = a.length;
		int n = a[0].length;
		int c = 2;
		
		for (int i = 0; i < m; i++){
			for (int j = 0; j < n; j++) {
				if (a[i][j] == 1) {
					boolean flag = mark(a, i, j, m, n, c);
					if (flag) c++;
				}
			}
		}
		return c-2;
	}
	
	public static int[][] createArrayFromInput(String size, String input) {
		String[] mnSize = size.split(",");
		int[][] a = new int[Integer.valueOf(mnSize[0])][Integer.valueOf(mnSize[1])];
		int m = a.length, n = a[0].length;
		String[] inputs = input.split(",");
		
		int nv = 0, v = 0, c = 0, i = 0, j = 0;
		
		do {
			nv = Integer.valueOf(inputs[c]); 
			v = Integer.valueOf(inputs[c + 1]);
			c += 2;
			while (nv > 0) {
				a[i][j] = v;
				j++;
				if (j == n) {
					i++;
					j = 0;
				}
				nv--;
			}
		}
		while (i < m && j < n && c < inputs.length); 		
		return a;		
	}
	
	private static void printArray(int[][] a) {

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {				
				System.out.printf("%3d ", a[i][j]);
			}
			System.out.println();
		}

	}
	
	public static void main(String[] args) {
		String size = "50,30";
		String input = "40,0,60,1,100,0,50,1,60,0,500,1,60,0,30,1,100,0,240,1,50,0,10,1,40,0,60,1,30,0,40,1,30,0";
		int[][] a = createArrayFromInput(size, input);
		//printArray(a);
		System.out.println(findNumberOfObjects(a));		
	}

}
