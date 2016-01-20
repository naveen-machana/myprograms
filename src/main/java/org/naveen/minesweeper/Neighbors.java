package org.naveen.minesweeper;

public enum Neighbors {
	
	TOP_LEFT(-1, -1), TOP_CENTER(-1, 0), TOP_RIGHT(-1, 1),
	LEFT(0, -1), RIGHT(0, 1),
	BOTTOM_LEFT(1, -1), BOTTOM_CENTER(1, 0), BOTTOM_RIGHT(1, 1);
	
	private int x;
	private int y;
	Neighbors(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {return x;}
	public int y() {return y;}
	
}
