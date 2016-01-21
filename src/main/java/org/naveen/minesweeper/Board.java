package org.naveen.minesweeper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {
	private int rows;
	private int cols;
	private int totalCells;
	private int noMines;
	private Cell[][] board;
	private boolean printOriginal = false;
	private int noOfOpened = 0;	
	private Scanner in = new Scanner(System.in);
	private boolean isCompleted = false;
	
	public Board(int rows, int cols, int noOfMines) {
		validate(rows, cols, noOfMines);
		this.rows = rows;
		this.cols = cols;
		this.noMines = noOfMines;
		board = new Cell[rows][cols];
		intializeBoard();
		totalCells = rows * cols;
	}
	
	private boolean isCompleted() {
		return (isCompleted || (noMines == (totalCells - noOfOpened)));
	}
	
	public void setPrintOriginal(boolean printOriginal) {
		this.printOriginal = printOriginal;
	}
	
	private void intializeBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(i, j);
			}
		}
	}

	private void validate(int rows, int cols, int noOfMines) {		
		if (rows > 128 || cols > 128 || noOfMines >= (rows * cols)){
			throw new RuntimeException("Rows and cols should be less than 128. "
					+ "And also number of mines should not exceed " + (rows * cols));
		}
	}
	
	public void play() {
		while (!isCompleted()) {
			System.out.println("Enter values for [Row, Column] : ");
			int r = in.nextInt();
			int c = in.nextInt();
			Cell cell = new Cell(r, c);
			
			// this is the first attempt of the user
			// generate the board
			try {
				if (noOfOpened == 0) {
					randomize(cell);
				}
				openNeighbors(cell);
				System.out.println(this);
			} catch (InvalidUserSelection e) {
				System.out.println("Please provide a valid input. Row < " + rows + ", Column < " + cols);
				System.out.println("Also select a closed cell.");				
			} catch(GameOverException e) {		
				System.out.println(this);
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void openNeighbors(Cell selectedCell) throws InvalidUserSelection {
		if (!isValid(selectedCell)) {
			throw new InvalidUserSelection("Invalid values provided for row or column or both");
		}
		
		Cell boardCell = getCell(selectedCell);
		
		if (boardCell.isOpened()) {
			throw new InvalidUserSelection("Cell is already opened. Select a closed cell. ");
		}
		
		if (boardCell.containsMine()) {
			this.isCompleted = true;
			throw new GameOverException("Sorry!!! You lose the game. You stepped on a mine. ");
		}
		else {
			List<Cell> currentNeighbors = new LinkedList<>();
			List<Cell> nextNeighbors = new LinkedList<>();
			currentNeighbors.add(boardCell);			
			
			//exit:
			while (! currentNeighbors.isEmpty()) {
				for (Cell cell : currentNeighbors) {
					
					openCell(cell);					
					checkIfCompleted();
					
					for (Neighbors neighbor : Neighbors.values()) {
						Cell resolvedCell;
						if (((resolvedCell = resolve(cell, neighbor)) != null) &&
							(!resolvedCell.isOpened()) &&
							(!resolvedCell.containsMine()) && 
							(!nextNeighbors.contains(resolvedCell)) &&
							(!currentNeighbors.contains(resolvedCell))) {
							
							// if resolved cell is an empty cell then add it to the next neighbors list
							// so that its neighbors can also be opened
							if (resolvedCell.value() == Cell.SPACE)
								nextNeighbors.add(resolvedCell);
							else {
								// if resolved cell is next to a mine, don't open neighbors of 
								// the resolved cell
								openCell(resolvedCell);		
								checkIfCompleted();
							}
						}
					}				
				}
				// next neighbors list contains cells that are to be opened in the
				// next run. if next neighbors list is empty
				// the loop will be completed
				currentNeighbors = nextNeighbors;
				nextNeighbors = new LinkedList<>();
			}			
		}		
	}

	protected void randomize(Cell userSelectionCell){
		// examples: if user selected 2x5 cell in an 8x8 grid
		// then we convert it to 2*8+5 = 21, which is the cell user selected
		// total number of cells that the 8x8 grid contains is 64;
		// so 7x7 = (7*8+7 = 63);
		Random r = new Random();
		int cell = userSelectionCell.x() * rows + userSelectionCell.y();
		List<Integer> mines = new ArrayList<>(noMines);
		for (int i = 0; i < noMines; i++) {
			int pick;
			do{
				pick = r.nextInt(totalCells);
			} while (cell == pick || mines.contains(pick));
			
			mines.add(pick);
			Cell mineCell = getCell(pick);
			mineCell.setValue(Cell.MINE);
		}
		
		// assign count to neighbors
		fillNeighbors(mines);
	}
	
	private void fillNeighbors(List<Integer> mines) {
		
		for (Integer cell : mines) {
			
			for (Neighbors direction : Neighbors.values()) {
				Cell resolvedCell = null;
				if ((resolvedCell = resolve(cell, direction)) != null
					&& (!mines.contains(resolvedCell))) {
					resolvedCell.setValue(resolvedCell.value() + 1);
				}
			}
		}
	}
	
	private void checkIfCompleted() {
		if (isCompleted()) {
			this.isCompleted = true;
			//break exit;
			throw new GameOverException("Congratulations!!! You won the game.");
		}
	}
	
	private void openCell(Cell cell) {
		noOfOpened++;
		cell.setOpened(true);
	}
	
	private Cell resolve(int cellNumber, Neighbors direction) {
		return resolve(new Cell(cellNumber / rows, cellNumber % cols), direction);
	}
	
	private Cell resolve(Cell cell, Neighbors direction) {
		Cell newCell = new Cell(cell.x() + direction.x(), cell.y() + direction.y());
		if (isValid(newCell)) {
			return getCell(newCell);
		}
		return null;
	}
	
	private boolean isValid(Cell cell) {
		if (cell.x() < 0 || cell.x() >= rows || 
			cell.y() < 0 || cell.y() >= cols)
			return false;
		
		return true;
	}
	
	private Cell getCell(int cellNumber) {
		return board[cellNumber / rows][cellNumber % cols];
	}
	
	private Cell getCell(Cell cell) {
		return board[cell.x()][cell.y()];
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				String value = board[i][j].stringValue();
				
				if (printOriginal == false) {
					value = board[i][j].isOpened() ? value : "C";
					
					if (value.equals("0")){
						value = board[i][j].isOpened() ? " " : "C";						
					}
				}
				builder.append(String.format("%4s  |", value));
			}
			builder.append("\n");
			builder.append("--------------------------------------------------------\n");
		}
		return builder.toString();
	}
	
	/*
	public static BoardBuilder builder() {
		return new BoardBuilder();
	}

	private static class BoardBuilder {
		
		private int cols = -1;
		private int rows = -1;
		private int noOfMines = -1;
		
		public BoardBuilder() {
		}
		
		public BoardBuilder cols(int cols) {
			this.cols = cols;
			return this;
		}
		
		public BoardBuilder rows(int rows) {
			this.rows = rows;
			return this;
		}
		
		public BoardBuilder numberOfMines(int mines) {
			this.noOfMines = mines;
			return this;
		}
		
		public Board buildBoard() {
			validate(rows, cols, noOfMines);
			Board board = new Board(rows, cols, noOfMines);
			return board;
			
		}
		
		private void validate(int rows, int cols, int noOfMines) {
			
			if (rows > 128 || cols > 128 || noOfMines >= (rows * cols)){
				throw new RuntimeException("Rows and cols should be less than 128. "
						+ "And also number of mines should not exceed " + (rows * cols));
			}
		}

	}*/
}
