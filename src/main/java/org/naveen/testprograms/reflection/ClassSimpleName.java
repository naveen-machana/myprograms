package org.naveen.testprograms.reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class ClassSimpleName {
	
	private static Map<Class<?>, List<?>> maps = initialize();
	
	public static void main(String[] args) {
		Class<ClassSimpleName> classFile = ClassSimpleName.class;
		System.out.println(classFile.getSimpleName());
		
		TestClass<ClassSimpleName> t = new TestClass<>();
		t.printTsClass();		
		
		List<String> ints = getList(String.class);
		System.out.println(ints);
		
	}
	
	private static Map<Class<?>, List<?>> initialize(){
		Map<Class<?>, List<?>> lookups = new HashMap<>();
		List<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(2);
		List<String> strings = new LinkedList<>();
		strings.add("one");
		strings.add("two");
		
		lookups.put(Integer.class, ints);
		lookups.put(String.class, strings);
		
		return lookups;
	}
	
	@SuppressWarnings({"unchecked" })
	private static <T> List<T> getList(Class<T> type){
		 return (List<T>)maps.get(type);
	}
	
	private static class TestClass<T> {
		private T instance;
		public TestClass(){}
		public TestClass(T instance){
			this.instance = instance;
		}
		@SuppressWarnings("unchecked")
		void printTsClass(){
			//Class<T> typeClass = (Class<T>) ((TypeVariable)getClass().getTypeParameters()).getActualTypeArguments()[0];
			//System.out.println(typeClass.getSimpleName());
		}
	}

}
