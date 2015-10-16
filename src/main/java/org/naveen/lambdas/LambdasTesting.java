package org.naveen.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdasTesting {

	public static void main(String[] args) {
		/*List<String> strings = Arrays.<String>asList("1", "2", "3", "4", "6", "7");
		//List<String> strings = new ArrayList<>();
		
		String test = strings.stream().collect(Collectors.joining("', '", "ContractTypeId" + " IN ('", "')"));
		System.out.println(test);
		System.out.println(test.endsWith(" "));
		
		strings.stream().map(x -> Integer.parseInt(x))
					    .filter( x -> x > 3)
					    .collect( Collectors.mapping(x -> Double.valueOf(x), Collectors.toList()))
					    .forEach(System.out::println);*/
		
		Employee e1 = new Employee(1, null, 10);
		Employee e2 = new Employee(2, "e2", 20);
		Employee e3 = new Employee(1, "e3", 10);
		List<Employee> emps = Arrays.asList(e1, e2, e3);
		
		emps.stream()
				.map(Employee::getName)
				.filter(e -> e != null)
				.collect(Collectors.toSet())
				.forEach(System.out::println);
		
	}
	
	private static class Employee {
		private int id;
		private String name;
		private int sal;
		
		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public int getSal() {
			return sal;
		}

		public Employee(int id, String name, int sal) {
			this.id = id;
			this.name = name;
			this.sal = sal;
		}
	}

}
