package org.naveen.testprograms.graphs;

import java.util.LinkedList;

import java.util.Stack;

public class Problems {
	public static class Graph {
		private int v;
		private LinkedList<Integer> e[];
		
		public Graph(int v) {
			this.v = v;
			e = new LinkedList[v];
			
			for (int i = 0; i < v; i++) {
				e[i] = new LinkedList<Integer>();
			}
		}

		public void addEdge(int s, int t) {
			e[s].add(t);
		}

		public void dfs(int s) {
			boolean[] visited = new boolean[v];
			dfsUtil(s, visited);
		}

		private void dfsUtil(int s, boolean[] visited) {
			visited[s] = true;
			System.out.println(s + " ");
			for (Integer adj : e[s]) {
				if (visited[adj] == false) {
					dfsUtil(adj, visited);
				}
			}
		}

		public void dfsIterative(int s) {
			dfsUtilIterative(s);
		}

		private void dfsUtilIterative(int s) {
			boolean[] visited = new boolean[v];
			Stack<Integer> stack = new Stack<>();
			stack.push(s);

			System.out.println(s + " ");
			visited[s] = true;
			
			while (!stack.isEmpty()) {
				Integer top = stack.peek();
				int next = nextUnvisitedVertex(top, visited);
				if (next != -1) {
					System.out.println(next);
					visited[next] = true;
					stack.push(next);
				}

				else {
					stack.pop();
				}
			}
		}

		private int nextUnvisitedVertex(int s, boolean[] visited) {
			for (Integer adj : e[s]) {
				if (visited[adj] == false) {
					return adj;
				}
			}
			return -1;
		}

		public boolean hasCycle(int s) {
			boolean[] visited = new boolean[v];
			return hasCycleUtil(s, visited);
		}

		private boolean hasCycleUtil(int s, boolean[] visited) {
			visited[s] = true;
			System.out.println(s + " ");
			for (Integer adj : e[s]) {
				if (visited[adj] || hasCycleUtil(adj, visited)) {
					return true;
				}
			}
			return false;
		}

		public boolean isThereAPath(int s, int t) {
			boolean[] visited = new boolean[v];
			return isThereAPathUtil(s, t, visited);
		}

		private boolean isThereAPathUtil(int s, int t, boolean[] visited) {
			if (s == t)
				return true;

			visited[s] = true;
			
			for (Integer adj : e[s]) {
				if (visited[adj] == false
						&& (adj == t || isThereAPathUtil(adj, t, visited))) {
					return true;
				}
			}
			
			return false;
		}

		public static void testIsThereAPath() {
			Graph g = new Graph(4);
			g.addEdge(0, 1);
			g.addEdge(0, 2);
			g.addEdge(1, 2);
			g.addEdge(2, 0);
			g.addEdge(2, 3);
			g.addEdge(3, 3);

			for (int s = 0; s < g.v; s++) {
				for (int t = 0; t < g.v; t++) {
					boolean isPathThere = g.isThereAPath(s, t);
					System.out.println(s + " --> " + t + " : " + isPathThere);
				}
			}
		}

		public static void testCycleDetection() {
			Graph g = new Graph(4);
			g.addEdge(0, 1);
			// g.addEdge(0, 2);
			g.addEdge(1, 2);
			g.addEdge(2, 0);
			g.addEdge(2, 3);

			// g.addEdge(3, 3);

			boolean isCycleThere = g.hasCycle(2);
			System.out.println("is cycle there: " + isCycleThere);
		}

		public static void testDFS() {
			Graph g = new Graph(4);
			g.addEdge(0, 1);
			g.addEdge(0, 2);
			g.addEdge(1, 2);
			g.addEdge(2, 0);
			g.addEdge(2, 3);
			g.addEdge(3, 3);
			g.dfs(2);
		}

		public static void testDFSIterative() {
			Graph g = new Graph(5);
			g.addEdge(0, 1);
			g.addEdge(0, 2);
			g.addEdge(1, 2);
			g.addEdge(1, 3);
			g.addEdge(2, 0);
			g.addEdge(2, 3);
			g.addEdge(3, 3);
			g.addEdge(3, 4);
			g.dfsIterative(2);
		}
	}

	public static void main(String[] args) {
		Graph.testIsThereAPath();
	}

}