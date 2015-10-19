package org.naveen.testprograms.graphs;


public class HamiltonianCycle {
	
	static class Graph {
		private int v;
		private int[][] g;
		
		public Graph(int v) {
			this.v = v;
			g = new int[v][v];
		}
		
		public void addEdge(int s, int t) {
			g[s][t] = 1;
			g[t][s] = 1;
		}
		
		public boolean isSafe(int[] path, int pindex, int s) {
			if (g[path[pindex-1]][s] == 0) return false;
			
			for (int i = 0; i < pindex; i++) {
				if (path[i] == s) return false;
			}
			
			return true;
		}
		
		private boolean isThereACycleUtil(int[] path, int pindex) {
			
			if (pindex == v){
				return g[path[pindex-1]][path[0]] == 1; 
			}
			
			for (int i = 1; i < v; i++) {
				if (isSafe(path, pindex, i)) {
					path[pindex] = i;
					
					if (isThereACycleUtil(path, pindex+1)) return true;
					
					path[pindex] = -1;					
				}
			}
			
			return false;
		}
		
		public boolean isThereACycle() {
			int[] path = new int[v];
			path[0] = 0;
			return isThereACycleUtil(path, 1);
		}
		
		public static void testHamiltonianCycle() {
			Graph g = new Graph(5);
			g.addEdge(0, 1);
			g.addEdge(0, 3);
			g.addEdge(1, 2);
			g.addEdge(1, 3);
			g.addEdge(1, 4);
			g.addEdge(2, 4);
			//g.addEdge(3, 4);
			System.out.println("Hamiltonian Cycle: " + g.isThereACycle());
		}
		
	}
	
	public static void main(String[] args) {
		Graph.testHamiltonianCycle();
	}

}
