package org.naveen.interestingprograms;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedCollection<E> extends AbstractCollection<E> {
	
	private class Node {
		E e;
		Node next;
		Node(E e) {
			this.e = e;
		}
	}
	
	private Node first = new Node(null);
	private Node last = first;
	
	public boolean ad(E e) {
		last.next = new Node(e);
		last = last.next;
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Node current = first;
			
			@Override
			public boolean hasNext() {
				return current.next != null;
			}

			@Override
			public E next() {
				if (current.next != null){
					current = current.next;
					return current.e;
				}
				else throw new NoSuchElementException();
			}		
		};
	}

	@Override
	public int size() {
		return 0;
	}

}
