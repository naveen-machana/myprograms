package org.naveen.designpattern.examples;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDecoratorProxy {
	
	@SuppressWarnings("unchecked")
	public static <T extends Map<K, V>, K, V> Map<K, V> getMap(final Class<T> mapClass, final T mapInst) {
		return (Map<K, V>) Proxy.newProxyInstance(mapInst.getClass().getClassLoader(), 
				new Class[]{mapClass}, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						System.out.println("before method call " + method.getName());
						
						Object value = method.invoke(mapInst, args);
						if (method.getName().equals("size")) {
							return 1;
						}
						
						System.out.println("After method call: " + method.getName());
						return value;
					}
			
		});
		
	}
	
	public static void main(String[] args) {
		
		Map<String, String> map = getMap(Map.class, new HashMap<String, String>());
		Map<String, String> map2 = getMap(Map.class, new HashMap<String, String>());
		
		//System.out.println("instance type: " + map.getClass());
		//System.out.println("map.getClass().equals(HashMap.getClass()) : " + map.getClass().equals(HashMap.class));
		//map.put("1", "1");
		//map.put("2", "2");
		
		//map2.put("1", "1");
		//map2.put("2", "2");
		
		HashMap<String, String> one = new HashMap<>();
		TreeMap<String, String> two = new TreeMap<>();
		one.put("one", "one");
		one.put("abc", "abc");
		one.put("two", "two");
		
		
		two.put("one", "one");
		two.put("two", "two");
		two.put("abc", "abc");
		
		System.out.println("hashamp.equals(treemap): " + two.equals(one));
		
//		System.out.println("map instanceof Map: "+ (map instanceof Map));
//		System.out.println("one.equals(map): " + one.equals(map));
//		System.out.println("one.equals(two)" + one.equals(two));
		
		System.out.println(map.size());
		
		System.out.println(map.equals(map2));
		System.out.println("print: " + map.get("1"));

	}

}
