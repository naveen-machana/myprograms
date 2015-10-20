package org.naveen.lambdas;

import java.util.function.Function;

public class EnumValueByName {
	
	private static boolean isStringNotEmpty(String name) {
		return ((name != null) && !("".equals(name)));
	}
	
	public static <T extends Enum<T>, R> R getEnumValueByName(String enumToBeSearched, 
			Class<T> enumTypeClass,
			Function<T, R> method) {
		
		if (isStringNotEmpty(enumToBeSearched)) {
			
			T[] enums = enumTypeClass.getEnumConstants();
			
			if (enums != null) {
				for (T t : enums) {
					if (t.name().equalsIgnoreCase(enumToBeSearched)) {
						return method.apply(t);
					}
				}
				
			}
		}
		
		return null;		
	}
	
	public static void testWithValidEnumName() {
		Integer v = EnumValueByName.<PriorityType, Integer>getEnumValueByName("HIGH", PriorityType.class, t -> t.getPriority());
		System.out.println(v);
	}
	
	public static void testWithInvalidEnumName() {
		Integer v = EnumValueByName.<PriorityType, Integer>getEnumValueByName("INCORRECT", PriorityType.class, t -> t.getPriority());
		if (v == null) {
			System.out.println("v is null");			
		}
		else {
			System.out.println("v is not null");
		}
	}
	
	public static void testWithValidEnumNameForString() {
		String v = EnumValueByName.<PriorityTypeName, String>getEnumValueByName("HIGH", PriorityTypeName.class, t -> t.getPriority());
		System.out.println(v);
	}
	
	public static void main(String[] args) {
		testWithValidEnumNameForString();
	}
	
	enum PriorityTypeName {
		HIGH("ONE"), MEDIUM("TWO"), LOW("THREE");
		
		private final String priority;
		
		PriorityTypeName(String priority) {
			this.priority = priority;
		}
		
		public String getPriority() {
			return this.priority;
		}
	}
	
	enum PriorityType {
		HIGH(1), MEDIUM(2), LOW(3);
		
		private final int priority;
		
		PriorityType(int priority) {
			this.priority = priority;
		}
		
		public int getPriority() {
			return this.priority;
		}
	}
	
}
