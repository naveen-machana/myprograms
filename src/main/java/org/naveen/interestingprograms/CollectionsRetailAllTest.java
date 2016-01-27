package org.naveen.interestingprograms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionsRetailAllTest {

	public static void main(String[] args) {
		List<Integer> listone = new ArrayList<Integer>();
		listone.add(1);
		listone.add(2);
		List<Integer> listtwo = new ArrayList<Integer>();
		//listtwo.add(3);
		//listtwo.add(4);
		
		listone.retainAll(listtwo);
		
		System.out.println(listone);
		

	}

}
