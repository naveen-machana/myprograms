package org.naveen.testprograms.graphs;

public class GraphCycleWithSets {
	
	static class Graph{
		
		private class Edge {
			int src, dest;
		}
		
		private int v, e;
		private Edge[] edges = null;
		
		public Graph(int v, int e) {
			this.v = v;
			this.e = e;
			this.edges = new Edge[e];
			for (int i = 0; i < e; i++) {
				this.edges[i] = new Edge();
			}
		}
		
		private int find(int[] parent, int i) {
			if (parent[i] == -1) return i;
			return find(parent, parent[i]);
		}
		
		private void union(int[] parent, int x, int y) {
			int xset = find(parent, x);
			int yset = find(parent, y);
			
			parent[xset] = yset;
		}
		
		public boolean isCycle() {
			int[] parent = new int[v];
			init(parent, -1);
			
			for (int i = 0; i < edges.length; i++) {
				int s = find(parent, edges[i].src);
				int d = find(parent, edges[i].dest);
				
				if (s == d) return true;
				
				union(parent, s, d);
			}
			return false;
		}
		
		private void init(int[] a, int value) {
			for (int i = 0; i < a.length; i++) a[i] = value;
		}
	}
	
	public static void testIsCycle() {
		Graph g = new Graph(3, 2);
		g.edges[0].src = 0;
		g.edges[0].dest = 1;
		
		g.edges[1].src = 1;
		g.edges[1].dest = 2;
		
		/*g.edges[2].src = 0;
		g.edges[2].dest = 2;*/
		
		System.out.println(g.isCycle());
	}
	
	public static void main(String[] args) {
		testIsCycle();
	}

}
