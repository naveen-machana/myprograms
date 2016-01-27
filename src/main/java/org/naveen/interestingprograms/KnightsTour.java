package org.naveen.interestingprograms;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.naveen.interestingprograms.KnightsTour.Board.Cell;

public class KnightsTour {
	
	private static final int BOARD_MAX_ROWS = 5;
	private static final int BOARD_MAX_COLS = 5;
	
	private enum PossibleMove{
		ONE(-2, -1), TWO(-1, -2), THREE(1, -2), FOUR(2, -1),
		FIVE(-2, 1), SIX(-1, 2), SEVEN(1, 2), EIGHT(2, 1);
		private PossibleMove(int r, int c) {
			this.c = c;
			this.r = r;
		}
		private int r;
		private int c;
		public int r() {
			return r;
		}
		public int c() {
			return c;
		}
	}
	
	private static final Set<PossibleMove> possibleMoves = EnumSet.allOf(PossibleMove.class);
	
	public static class Board {		
		
		private int MAX_ROWS, MAX_COLS;
		private int[][] a;
		private int counter = 0;
		private static final int UNASSIGNED = 0;
		
		public Board(int maxRows, int maxCols) {
			this.MAX_ROWS = maxRows;
			this.MAX_COLS = maxCols;
			a = new int[MAX_ROWS][MAX_COLS];
		}
		
		public boolean isSafe(Cell cell) {
			return (cell.isValid() && isUnassigned(cell));
		}
		
		private boolean isUnassigned(Cell cell) {
			return a[cell.r][cell.c] == UNASSIGNED;
		}
		
		public boolean isCompleted() {
			return counter == MAX_ROWS * MAX_COLS;
		}
		
		public void setCell(Cell cell, int x) {
			a[cell.r()][cell.c()] = x;
			counter++;
			System.out.println(this);
		}
		
		public void resetCell(Cell cell) {
			a[cell.r()][cell.c()] = UNASSIGNED;
			counter --;
			System.out.println(this);
		}	
		
		@Override
		public String toString() {
			StringBuilder board = new StringBuilder();  
			for (int i = 0; i < a.length; i++) {
				board.append("\n");
				for (int j = 0; j < a[i].length; j++) {
					board.append(String.format("%4d", a[i][j]));
				}
			}
			return board.toString();
		}
		
		public class Cell {
			private int r, c;
			public Cell resolve(PossibleMove move) {
				return new Cell(r + move.r, c + move.c);
			}
			
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
	}
	
	private boolean solveHelper(Board board, Cell cell, int x) {
		
		if (board.isCompleted()) {
			return true;
		}
		
		for (PossibleMove move : possibleMoves) {
			Cell resolved = cell.resolve(move);

			if (board.isSafe(resolved)) {
				
				board.setCell(resolved, x);
				
				if (solveHelper(board, resolved, x + 1)) {
					return true;
				}
				
				board.resetCell(resolved);
			}
		}
		
		return false;
	}
	
	public void findKnightsTour() {
		
		List<Board> solutions = new LinkedList<Board>();
		
		Board board = new Board(BOARD_MAX_ROWS, BOARD_MAX_COLS);
		Cell cell = board.new Cell(0, 0);
		board.setCell(cell, 1);
		boolean solved = solveHelper(board, cell, 2);
		if (solved) {
			solutions.add(board);
		}
		
		// find solution for each position, if there exists one
		/*for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Board board = new Board();
				Cell cell = new Cell(i, j);
				board.setCell(cell, 1);
				boolean solved = solveHelper(board, cell, 2);
				if (solved) {
					solutions.add(board);
				}
			}
		}*/
		
		int counter = 1;
		for (Board sol : solutions) {
			System.out.println();
			System.out.println("Solution " + counter);
			System.out.println("------------------------------------");
			System.out.println(sol);
		}
	}
	
	public static void main(String[] args) {
		KnightsTour tour = new KnightsTour();
		tour.findKnightsTour();
	}

}
