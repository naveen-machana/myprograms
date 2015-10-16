package org.naveen.testprograms;

import java.util.Arrays;
import java.util.List;

public class BridgeGenerics {
	
	class Node<E> {
		E data; 
		Node(E e){
			data = e;
		}
		
		public void setData(E o){
			this.data = o;
		}
	}
	
	class MyNode extends Node<Integer> {

		MyNode(Integer e) {
			super(e);
		}
		
		public void setData(Integer e){
			this.data = e;
		}
		
	}
	
	public static <E> List<E> singleton(E elt){
		return Arrays.<E>asList(elt);
	}
	
	public static void main(String[] args) {
		List<Integer> l1 = Arrays.asList(1,2,3);
		List<Integer> l2 = Arrays.asList(4, 5);
		List<List<Integer>> l3 = Arrays.asList(l1, l2);
	}

}
