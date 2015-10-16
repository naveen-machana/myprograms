package org.naveen.testprograms;

import java.util.LinkedList;
import java.util.List;

public class NQueensOnChess {
	
	//private List<Integer> moves = new LinkedList<Integer>();
	
	public NQueensOnChess() {
//		moves.add(3);
//		moves.add(4);
//		moves.add(5);
//		moves.add(6);
//		moves.add(7);		
//		
//		
//		moves.add(0);
//		moves.add(1);
//		moves.add(2);
	}
	
	private static final int SIZE = 5;

	private static class Cell {
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
		
		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
	}

	public static class Board {

		private final int[][] a = new int[SIZE][SIZE];

		private LinkedList<Cell> filledQueens = new LinkedList<Cell>();
		
		public void setCell(Cell cell, int num) {
			a[cell.r()][cell.c()] = num;
			filledQueens.addLast(cell);
			
			System.out.println(this);
		}
		
		public void resetCell(Cell cell) {
			a[cell.r()][cell.c()] = 0;
			filledQueens.removeLast();
			System.out.println(this);
		}
		
		public boolean isComplete() {
			return filledQueens.size() == SIZE;
		}

		public boolean isSafe(Cell cell) {
			return isValidCell(cell) &&
				   isRowSafe(cell) && 
				   isColSafe(cell) &&
				   is45to225Safe(cell) && 
				   is135to315Safe(cell);
		}
		
		private boolean isCellAssigned(Cell cell) {
			return a[cell.r()][cell.c()] != 0;
		}

		private boolean isValidCell(Cell cell) {
			return cell.r() >= 0 && cell.r() < SIZE && cell.c() >= 0
					&& cell.c() < SIZE;
		}

		private Cell resolve(Cell cell, Cell offset) {
			return new Cell(cell.r() + offset.r(), cell.c() + offset.c());
		}
		
		private boolean is135to315Safe(Cell thisCell) {

			final Cell _135_SIDE_OFFSET = new Cell(-1, -1);
			final Cell _315_SIDE_OFFSET = new Cell(1, 1);

			// top left
			if (!isSafeOnDiagonal(thisCell, _135_SIDE_OFFSET)) {
				return false;
			}

			// bottom right
			if (!isSafeOnDiagonal(thisCell, _315_SIDE_OFFSET)) {
				return false;
			}

			return true;
		}
		
		private boolean is45to225Safe(Cell thisCell) {

			final Cell _45_SIDE_OFFSET = new Cell(-1, 1);
			final Cell _225_SIDE_OFFSET = new Cell(1, -1);

			// top right
			if (!isSafeOnDiagonal(thisCell, _45_SIDE_OFFSET)) {
				return false;
			}

			// bottom left
			if (!isSafeOnDiagonal(thisCell, _225_SIDE_OFFSET)) {
				return false;
			}

			return true;
		}
		
		private boolean isSafeOnDiagonal(Cell baseCell, Cell offset) {
			for (Cell diagonalCell = resolve(baseCell, offset); 
				 isValidCell(diagonalCell); 
				 diagonalCell = resolve(diagonalCell, offset)) {
					if (isCellAssigned(diagonalCell)) {
						return false;
					}
				}
			return true;
		}

		private boolean isColSafe(Cell thisCell) {

			for (Cell filledCell : filledQueens) {
				if (thisCell.c() == filledCell.c()) {
					return false;
				}
			}

			return true;
		}

		private boolean isRowSafe(Cell thisCell) {
			for (Cell filledCell : filledQueens) {
				if (thisCell.r() == filledCell.r()) {
					return false;
				}
			}

			return true;
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
		
	}

	public boolean solve(Board board, int x, int r) {
		
		if (board.isComplete()) {
			return true;
		}
		
		//for (Integer move : moves) {
		  for (int move = 0; move < SIZE; move++) {
			Cell newCell = new Cell(r, move);
			if (board.isSafe(newCell)) {
				board.setCell(newCell, x);
				
				if (solve(board, x + 1, r + 1)) {
					return true;
				}
				
				board.resetCell(newCell);
			}
		}
		
		return false;
	}
	
	public void testSolve() {
		Board board = new Board();
		if (solve(board, 1, 0)) {
			System.out.println("Solution found");
			System.out.println("--------------------------");
			System.out.println(board);
		}
	}
	
	public static void main(String[] args) {
		NQueensOnChess puzzle = new NQueensOnChess();
		puzzle.testSolve();
	}
}