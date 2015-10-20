package org.naveen.testprograms.graphs;

public class GraphCycleWithUnionFindRank {
	
	static class Graph {
		
		private static class Edge {
			int src, dest;
		}
		
		private static class Subset{
			int parent;
			int rank;
		}
		
		private int v, e;
		private Edge[] edges;
		
		public Graph(int v, int e) {
			this.v = v;
			this.e = e;
			this.edges = new Edge[e];
			
			for (int i = 0; i < e; i++) edges[i] = new Edge();
		}
		
		private int find(Subset[] sets, int i) {
			if (sets[i].parent != i) {
				sets[i].parent = find(sets, sets[i].parent);
			}
			return sets[i].parent;
		}
		
		private void union(Subset[] sets, int x, int y) {
			int xset = find(sets, x);
			int yset = find(sets, y);
			
			if (sets[xset].rank < sets[yset].rank) {
				sets[xset].parent = yset;
			}
			
			else if (sets[xset].rank > sets[yset].rank) {
				sets[yset].parent = xset;
			}
			
			else {
				sets[xset].parent = yset;
				sets[yset].rank++;
			}
		}
		
		public boolean isCycle() {
			Subset[] sets = new Subset[v];
			
			for (int i = 0; i < v; i++) {
				sets[i] = new Subset();
				sets[i].parent = i;
			}
			
			for (int i = 0; i < e; i++) {
				int s = find(sets, edges[0].src);
				int d = find(sets, edges[0].dest);
				
				if (s == d) return true;
				union(sets, s, d);
			}
			
			return false;
		}
	}
	
	public static void main(String[] args) {

	}

}
