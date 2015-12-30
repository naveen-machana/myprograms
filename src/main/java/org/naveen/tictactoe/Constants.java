package org.naveen.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Constants {
	
	public static final char SPACE = ' ';
	public static final Cell TOP_LEFT = new Cell(0,0);
	public static final Cell TOP_RIGHT = new Cell(0,2);
	public static final Cell BOTTOM_LEFT = new Cell(2,0);
	public static final Cell BOTTOM_RIGHT = new Cell(2,2);
	public static final Cell CENTER = new Cell(1,1);
	
	public static final Cell LEFT_EDGE = new Cell(1,0);
	public static final Cell RIGHT_EDGE = new Cell(1,2);
	public static final Cell TOP_EDGE = new Cell(0, 1);
	public static final Cell BOTTOM_EDGE = new Cell(2,1);
	
	private static Cell[] edges = initEdges(); 
	private static Cell[] corners = initCorners();
	private static Map<Cell, List<Cell>> adjCorners = initAdjacentCorners();
	
	private static Cell[] initEdges() {
		Cell[] edges = new Cell[4];
		edges[0] = LEFT_EDGE;
		edges[1] = RIGHT_EDGE;
		edges[2] = TOP_EDGE;
		edges[3] = BOTTOM_EDGE;
		return edges;
	}
	
	private static Cell[] initCorners() {
		Cell[] corners = new Cell[4];
		corners[0] = TOP_LEFT;
		corners[1] = TOP_RIGHT;
		corners[2] = BOTTOM_LEFT;
		corners[3] = BOTTOM_RIGHT;
		return corners;
	}
	
	public static boolean isACorner(Cell cell) {
		for (Cell corner : corners) {
			if (corner.equals(cell)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isAnEdge(Cell cell) {
		for (Cell edge : edges) {
			if (edge.equals(cell)) {
				return true;
			}
		}
		return false;
	}
	
	public static Cell getCorner(int i) {
		return corners[i];
	}
	
	public static boolean isCenter(Cell cell) {
		return CENTER.equals(cell);
	}
	
	public static Cell getOppositeCorner(Cell cell) {
		if (TOP_LEFT.equals(cell)) return BOTTOM_RIGHT;
		if (TOP_RIGHT.equals(cell)) return BOTTOM_LEFT;
		if (BOTTOM_LEFT.equals(cell)) return TOP_RIGHT;
		if (BOTTOM_RIGHT.equals(cell)) return TOP_LEFT;
		return null;
	}
	
	public static Map<Cell, List<Cell>> initAdjacentCorners() {
		Map<Cell, List<Cell>> map = new HashMap<>();
		
		List<Cell> list = new ArrayList<>();	
		list.add(TOP_RIGHT);
		list.add(BOTTOM_LEFT);		
		map.put(TOP_LEFT, list);
		
		list = new ArrayList<>();
		list.add(TOP_LEFT);
		list.add(BOTTOM_RIGHT);
		map.put(TOP_RIGHT, list);
		
		list = new ArrayList<>();
		list.add(TOP_LEFT);
		list.add(BOTTOM_RIGHT);
		map.put(BOTTOM_LEFT, list);

		list = new ArrayList<>();
		list.add(TOP_RIGHT);
		list.add(BOTTOM_LEFT);
		map.put(BOTTOM_RIGHT, list);
			
		return map;
	}
	
	public static List<Cell> getAdjacentCorners(Cell corner) {
		return adjCorners.get(corner);
	}
	
	public Constants() {
		// TODO Auto-generated constructor stub
	}

}
