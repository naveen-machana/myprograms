package org.naveen.reflection.serialization;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DynamicLoadingExamples {
	// ...
	private Properties props;
	private CustomerDatabase custDB;

	// ...
	public synchronized CustomerDatabase createDBFacade() {
		if (custDB == null) {
			try {
				String dbClassName = props.getProperty("db.class", "com.wci.app.StubCustomerDB");
				Class cls = Class.forName(dbClassName);
				custDB = (CustomerDatabase) cls.newInstance();
			} catch (ClassNotFoundException ex) {
				// ...
			} catch (InstantiationException ex) {
				// ...
			} catch (IllegalAccessException ex) {
				// ...
			}
		}
		return custDB;
	}
	
	static class A {
		public void a() {
			System.out.println("in a");
		}
	}
	
	static class B extends A {
		public void a() {
			System.out.println("in b");
		}
	}
	
	public static void main(String[] args) {
		A a = new B();
		try {
			Method m = a.getClass().getMethod("a", null);
			m.invoke(a, null);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//((A)a).a();		
	}
	
	private interface CustomerDatabase{}
}
