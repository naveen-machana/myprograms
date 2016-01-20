package org.naveen.trees;

public class IncreasingSubsequenceOf3WithMaxProduct {
	
	static class Tree {
		static class Node {
			int data;
			Node left;
			Node right;		
			int height = 1;		
			
			Node(int data) {
				this.data = data;
			}
		}
		
		private Node rightRotate(Node root) {
			Node y = root.left;
			Node t3 = y.right;
			root.left = t3;
			y.right = root;
			
			root.height = max(height(root.left), height(root.right))+1;
			y.height = max(height(y.left), height(y.right)) + 1;
			return y;
		}
		
		private Node leftRotate(Node root) {
			Node y = root.right;
			Node t2 = y.left;
			root.right = t2;
			y.left = root;
			
			root.height = max(height(root.left), height(root.right))+1;
			y.height = max(height(y.left), height(y.right))+1;
			return y;
		}
		
		public Node insert(Node root, int x) {
			if (root == null) {
				return new Node(x); 
			}
			else if (root.data == x) return root;
			else if (root.data < x) {
				Node newnode = insert(root.right, x);
				root.right = newnode;
			}
			else {
				root.left = insert(root.left, x);
			}
			
			root.height = max(height(root.left), height(root.right)) + 1;
			int balance = balance(root);
			
			if (balance > 1 && root.left.data > x) {
				return rightRotate(root);
			}
			
			if (balance < -1 && root.right.data < x) {
				return leftRotate(root);
			}
			
			if (balance > 1 && root.left.data < x) {
				root.left = leftRotate(root.left);
				return rightRotate(root);
			}
			
			if (balance < -1 && root.right.data < x) {
				root.right = rightRotate(root.right);
				return leftRotate(root);
			}
			return root;
		}
		
		public void largestSmallLessThanCurrent(Node root, int x, Integer[] prev) {
			if (root == null) return;			
			
			if (root.data == x) {
				prev[0] = root.data;
				return;
			}			
			
			if (root.data > x) {				
				largestSmallLessThanCurrent(root.left, x, prev);
			}
			else {
				prev[0] = max(prev[0], root.data);
				largestSmallLessThanCurrent(root.right, x, prev);
			}
		}
		
		private int max(int x, int y) {
			return x > y ? x : y;
		}
		
		private int balance(Node root) {
			return height(root.left) - height(root.right);
		}
		
		private int height(Node root) {
			if (root == null) return -1;
			return root.height;
		}
		
		public Node find(Node root, int x) {
			if (root == null) return null;
			
			if (root.data == x) {
				return root;
			}
			else if (root.data < x) {
				return find(root.right, x);
			}
			else return find(root.left, x);
		}
		
		public void inorder(Node root) {
			if (root == null) return;
			inorder(root.left);
			System.out.println(root.data + ", ");
			inorder(root.right);
		}
	}
	
	static int[] maxValueOnRightSide(int[] a) {
		int n = a.length;
		int max = a[n-1];
		int[] rightmax = new int[n];
		rightmax[n-1] = -1;
		
		for (int i = n-2; i >= 0; i--) {
			if (a[i] > max){
				rightmax[i] = -1;
				max = a[i];
			}
			else {
				rightmax[i] = max;
			}
		}
		return rightmax;
	}
	
	static void test() {
		Tree t = new Tree();
		int[] a = {1, 5, 10, 8, 9};
		int[] leftLargestSmall = new int[a.length];
		
		Tree.Node root = null;
		
		Integer[] ceil = new Integer[1];
		for (int i = 0; i < a.length; i++) {
			ceil[0] = -1;
			t.largestSmallLessThanCurrent(root, a[i], ceil);
			leftLargestSmall[i] = ceil[0];
			root = t.insert(root, a[i]);
		}
		
		int[] rightmax = maxValueOnRightSide(a);
		
		int maxproduct = Integer.MIN_VALUE;
		for (int i = 1; i < a.length; i++) {
			if (leftLargestSmall[i] != -1 && rightmax[i] != -1) {
				int product = leftLargestSmall[i] * a[i] * rightmax[i];
				if (product > maxproduct) {
					maxproduct = product;
				}
			}
		}
		
		System.out.println("Maximum product: " + maxproduct);
	}
	
	public static void main(String[] args) {
		test();
	}

}
