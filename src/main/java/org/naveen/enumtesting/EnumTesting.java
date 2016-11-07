package org.naveen.enumtesting;

import java.lang.reflect.Field;

public class EnumTesting {

	public EnumTesting() {
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		CustomClassloader loader1 = new CustomClassloader();
        CustomClassloader loader2 = new CustomClassloader();
        if(loader1.equals(loader2)){
            System.out.println("two classloaders are same");
        }
        else {
            System.out.println("two classloaders are different");
        }
 
        Class<?> clsA = Class.forName("org.naveen.enumtesting.Days", true, loader1);
        Class<?> clsB = Class.forName("org.naveen.enumtesting.Days", true, loader2);
 
        System.out.println("org.naveen.enumtesting.Days class: " + clsA);
 
        //Object instanceA = clsA.newInstance();
        //Object instanceB = clsB.newInstance();
 
 
        if (clsA.equals(clsB)) {
            System.out.println("class loaded in different customer classloader are same");
        }
        else {
            System.out.println("class loaded in different customer classloader are different.");
        }
        
        Days sundayFromClsA = null;
        Days sundayFromClsB = null;
        
        Field[] fields = clsA.getDeclaredFields();
        for (Field f : fields) {
        	f.setAccessible(true);
        	if ("SUNDAY".equals(f.getName())) {        		
        		System.out.println(f.get(null).hashCode());
        	}
        }
        
        Field[] fields2 = clsB.getDeclaredFields();
        for (Field f : fields2) {
        	f.setAccessible(true);
        	if ("SUNDAY".equals(f.getName())) {
        		System.out.println(f.get(null).hashCode());
        	}
        }
	}

}
