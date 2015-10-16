package org.naveen.testprograms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class CrypticText {
	
	private Map<Character, Integer> charmap = new MyCharacterMap<>();
	private char[] input1;
	private char[] input2;
	private char[] result;
	
	private static class MyCharacterMap<K, V> extends HashMap<K, V> {
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public Collection<V> values() {
			Collection<V> collection = new TreeSet<V>();
			for (Map.Entry<K, V> entry : this.entrySet()) {
				if (entry.getValue() != null) {
					collection.add(entry.getValue());
				}
			}
			
			return collection;
		}
		
	}
	
	public CrypticText(char[] input1, 
					   char[] input2,
					   char[] result) {
		this.input1 = input1;
		this.input2 = input2;
		this.result = result;
		
		for (char c : input1) {
			charmap.put(c, null);
		}
		
		for (char c : input2) {
			charmap.put(c, null);
		}
		
		for (char c : result) {
			charmap.put(c, null);
		}
	}

	
	private boolean isAllCharsMapped() {
		return charmap.keySet().size() == charmap.values().size();
	}
	
	private boolean isSolved() {
		if (isAllCharsMapped()) {
			Integer operand1no = convertToNumber(input1);
			Integer operand2no = convertToNumber(input2);
			Integer resultno = convertToNumber(result);
			
			System.out.println(this);			
			return operand1no + operand2no == resultno;
		}
		return false;
	}
	
	private boolean isSafe(char c, Integer n) {
		return charmap.values().contains(n) == false;
	}
	
	private int npoweri(int n, int i) {
		if (i == 0) return 1;
		if (i % 2 == 0) {
			return npoweri(n, i/2) * npoweri(n, i/2);
		}
		else {
			return n * npoweri(n, i/2) * npoweri(n, i/2);
		}
	}
	
	private String getStringOfNumber(char[] chars) {
		StringBuilder number = new StringBuilder();
		for (char c : chars) {
			number.append(charmap.get(c));
		}
		return number.toString();
	}
	
	private Integer convertToNumber(char[] chars) {
		
		return Integer.parseInt(getStringOfNumber(chars));
	}
	
	public boolean solve(List<Character> keys, int current) {

		if (isSolved()) {
			return true;
		}

		for (int i = 0; i <= 9; i++) {
			if (current < keys.size()) {
				if (isSafe(keys.get(current), i)) {
					charmap.put(keys.get(current), i);

					if (solve(keys, current + 1)) {
						return true;
					}

					charmap.put(keys.get(current), null);
				}
			}
		}
		return false;
	}
	
	public void testSolve() {
		List<Character> keys = new ArrayList<>(charmap.keySet());
		if (keys.size() > 9) {
			throw new RuntimeException("More than 9 keys are there in input");
		}
		if (solve(keys, 0)) {
			System.out.println("-----------------------------");
			System.out.println("Puzzle solved and solution is");
			
			System.out.println(this);
		}
		else {
			System.out.println("-----------------------------");
			System.out.println("No solution found");
		}
	}
	
	@Override
	public String toString() {
		
		int i1 = convertToNumber(input1);
		int i2 = convertToNumber(input2);
		int r = convertToNumber(result);
		
		StringBuilder strbuilder = new StringBuilder();
		strbuilder.append("------------------------------\n");
		strbuilder.append(input1).append(" = ").append(getStringOfNumber(input1));
		strbuilder.append("\n");
		strbuilder.append(input2).append(" = ").append(getStringOfNumber(input2));		
		strbuilder.append("\n");
		strbuilder.append(result).append(" = ").append(getStringOfNumber(result));
		if (i1 + i2 == r) {
			strbuilder.append(" = ").append(r);			
		}
		else {
			strbuilder.append(" != ").append(i1 + i2);
		}
		
		return strbuilder.toString();
	}
	
	public static void main(String[] args) {
		CrypticText t = new CrypticText("send".toCharArray(),
										"some".toCharArray(),
										"tasks".toCharArray());
		t.testSolve();
		//System.out.println(t.npoweri(10, 5));

	}

}