package org.naveen.interestingprograms;


public class SolveSudoku {
	
	public final int[][] a = {{0,0,0,7,9,2,0,3,5},
							  {0,9,3,6,0,1,0,7,4},
							  {5,0,0,0,0,0,1,0,0},
							  {0,0,1,0,0,0,0,2,0},
							  {0,0,6,9,0,8,4,0,0},
							  {0,2,0,0,0,0,3,0,0},
							  {0,0,8,0,0,0,0,0,7},
							  {6,7,0,3,0,5,9,1,0},
							  {4,5,0,8,1,7,0,0,0}};
	
	private static final int UNASSIGNED = 0;
	private static final int MAX_ROWS = 9;
	private static final int MAX_COLS = 9;
	
	public class Cell {
		private int r, c;
		
		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public int r() {
			return r;
		}
		
		public int c() {
			return c;
		}
		
		public boolean isValid() {
			return r >= 0 && r < MAX_ROWS &&
				   c >= 0 && c < MAX_COLS;	
		}
		
		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}		
	}
	
	public boolean solve(int[][] a) {		
		
		Cell unassignedPosition = findUnassignedCell(a);
		
		if (null == unassignedPosition) {
			return true;
		}
		
		for (int i = 1; i <= 9; i++) {
			int r = unassignedPosition.r();
			int c = unassignedPosition.c();
			
			if (isValid(a, r, c, i)) {
				a[r][c] = i;
				
				if (solve(a)) {
					return true;
				}
				
				a[r][c] = UNASSIGNED;
			}			
		}
		
		return false;
	}
	
	private Cell findUnassignedCell(int[][] a) {
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (a[i][j] == UNASSIGNED) {
					return new Cell(i, j);
				}
			}
		}
		
		return null;
	}
	
	private boolean isValid(int[][] a, int r, int c, int x) {
	
		return isValidForRow(a, r, x) && isValidForColumn(a, c, x) &&
				isValidForBox(a, r, c, x);
	}
	
	private boolean isValidForRow(int[][] a, int r, int x) {
		for (int i = 0; i < 9; i++) {
			if (a[r][i] == x) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isValidForColumn(int[][] a, int c, int x) {
		for (int i = 0; i < 9; i++) {
			if (a[i][c] == x) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isValidForBox(int[][] a, int r, int c, int x) {
		int i = (r / 3) * 3;
		int j = (c / 3) * 3;
		for (int l = 0; l < 3; l++, i++) {
			int k = j;
			for (int m = 0; m < 3; m++, k++){
				if (a[i][k] == x) {
					return false;
				}
			}				
		}
		return true;
	}
	
	public static void main(String[] args) {
		SolveSudoku s = new SolveSudoku();
		s.solve(s.a);
		
		for (int i = 0; i < s.a.length; i++) {
			System.out.println();
			for (int j = 0; j < s.a[i].length; j++) {
				System.out.print(String.format("%4d", s.a[i][j]));
			}
		}
		
	}
}