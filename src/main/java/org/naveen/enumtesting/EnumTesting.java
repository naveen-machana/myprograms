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
 
        if (clsA.equals(clsB)) {
            System.out.println("class loaded in different customer classloader are same");
        }
        else {
            System.out.println("class loaded in different customer classloader are different.");
        }
        
        // the below Days declaration could be confusing
        // now that we have the Days class loaded from different classloaders
        // if we try to typecast the object to Days, we would be 
        // getting typecast error.
        // Days sundayFromClsA = null;
        // Days sundayFromClsB = null;
        
        Object instanceLoadedFromA = null;
        Object instanceLoadedFromB = null;
        
        Field[] fields = clsA.getDeclaredFields();
        for (Field f : fields) {
        	f.setAccessible(true);
        	if ("SUNDAY".equals(f.getName())) {        		
        		instanceLoadedFromA = f.get(null);
        	}
        }
        
        Field[] fields2 = clsB.getDeclaredFields();
        for (Field f : fields2) {
        	f.setAccessible(true);
        	if ("SUNDAY".equals(f.getName())) {
        		instanceLoadedFromB = f.get(null);
        	}
        }
        
        System.out.println("Classloader from instanceA: " + instanceLoadedFromA.getClass().getClassLoader());
        System.out.println("Classloader from instanceB: " + instanceLoadedFromB.getClass().getClassLoader());
        System.out.println("instanceLoadedFromA hashCode: " + instanceLoadedFromA.hashCode());
        System.out.println("instanceLoadedFromB hashCode: " + instanceLoadedFromB.hashCode());
        System.out.println("Are 2 instances loaded from different classloaders same: " 
        + (instanceLoadedFromA == instanceLoadedFromB));
	}

}

