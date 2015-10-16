package org.naveen.testprograms.graphs;

public class AllPairsShortestPath {
	
	private static final int INFINITE = Integer.MAX_VALUE;
	private int[][] graph;
	private int size;
	
	public AllPairsShortestPath(int size) {
		graph = new int[size][size];
	}
	
	public void print(int[][] solution) {
		StringBuilder board = new StringBuilder();  
		for (int i = 0; i < solution.length; i++) {
			board.append("\n");
			for (int j = 0; j < solution[i].length; j++) {
				if (solution[i][j] == INFINITE) {
					board.append(String.format("%4S", "INF"));
				}
				else {					
					board.append(String.format("%4d", solution[i][j]));
				}
			}
		}
		System.out.println(board.toString());
	}
	
	public void findShortestPathsBetweenPairs() {
		int[][] solution = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				solution[i][j] = graph[i][j];
			}
		}
		
		for (int k = 0; k < size; k++) {
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (solution[i][k] != INFINITE &&
						solution[k][j] != INFINITE &&
						solution[i][k] + solution[k][j] < solution[i][j]) {
						solution[i][j] = solution[i][k] + solution[k][j];
					}
				}
				print (solution);
			}
			print (solution);
		}
		print (solution);
	}
	
	public static void test() {
		AllPairsShortestPath graph = new AllPairsShortestPath(4);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
