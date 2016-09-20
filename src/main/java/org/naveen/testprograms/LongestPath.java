package org.naveen.sampleprograms;

import java.util.Arrays;
import java.util.Random;

public class LongestPath {
	
	enum Direction {
		LEFT(0, -1), RIGHT(0, 1), TOP(-1, 0), BOTTOM(1, 0);
		
		int x, y;
		private Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int[][] generate(int r, int c) {
		int[][] a = new int[r][c];
		Random random = new Random();
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				a[i][j] = random.nextInt(r*c) + 1;
			}
		}
		
		print(a);
		return a;
	}
	
	private static void print(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.print(String.format("%5d", a[i][j]));
			}
			System.out.println();
		}
	}
	
	private static boolean inLimits(int[][] a, int x, int y) {
		return x >= 0 && x < a.length && y >= 0 && y < a[0].length;
	}
	
	private static void copy(int[] a, int[] b, int size) {
		for (int i = 0; i < b.length; i++) {
			b[i] = 0;
		}
		for (int i = 0; i < size; i++) {
			b[i] = a[i];
		}
	}
	
	public static void longestPath(int[][] a, int curlen, int[] maxlen, int i, int j, int[] curpath, int[] maxpath) {
		if (! inLimits(a, i, j)) return;
		
		for (Direction d : Direction.values()) {
			int x = i + d.x;
			int y = j + d.y;
			
			if (inLimits(a, x, y) && a[i][j] < a[x][y]) {
				curpath[curlen] = a[x][y];
				if (curlen + 1 > maxlen[0]) {
					maxlen[0] = curlen+1;					
					copy(curpath, maxpath, curlen+1);
				}
				longestPath(a, curlen + 1, maxlen, x, y, curpath, maxpath);
			}
		}
	}
	
	public static void main(String[] args) {
		int r = 5, c = 5;
		int[][] a = generate(r, c);
		/*int[][] a = {{   14,    4,    5,    6},
					 {   10,   15,    2,   13},
					 {    8,    5,    3,    4},
					 {    7,    6,    1,    1}};*/
		int[] maxlen = {1};
		
		int[] maxpath = null;	
		
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				int[] curmaxlen = {1};
				int[] curmaxpath = new int[r*c];
				int[] curpath = new int[r * c];
				curpath[0] = a[i][j];
				curmaxpath[0] = a[i][j];
				
				longestPath(a, 1, curmaxlen, i, j, curpath, curmaxpath);
				System.out.println(Arrays.toString(curmaxpath));
				if (curmaxlen[0] > maxlen[0]) {
					maxlen = curmaxlen;
					maxpath = curmaxpath;
				}
			}
		}
		
		System.out.println("longest increasing path: " + maxlen[0]);
	}

}

