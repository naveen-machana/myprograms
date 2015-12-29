package org.naveen.reflection.serialization;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class SerializingObject {
	
	private static boolean not(boolean flag) {
		return !flag;
	}
	
	private static Field[] getInstanceVariables(Object obj) {
		Class cls = obj.getClass();
		LinkedList<Field> fields = new LinkedList<>();
		
		while (cls != null) {
			Field[] flds = cls.getDeclaredFields();
			for (Field fld : flds) {
				
				if (not(Modifier.isStatic(fld.getModifiers()))) {
					
					fields.add(fld);					
				}
			}
			cls = cls.getSuperclass();
		}
		
		return fields.toArray(new Field[0]);
	}
	
	public static Document serializeObject(Object obj) throws IllegalArgumentException, IllegalAccessException {
		return serializeHelper(obj, new Document(new Element("Serialized")), new IdentityHashMap<Object, String>());
	}
	
	private static Document serializeHelper(Object obj, Document target, Map<Object, String> map) throws IllegalArgumentException, IllegalAccessException{
		Class sourceClass = obj.getClass();
		String id = Integer.toString(map.size());
		map.put(obj, id);
		
		Element oElement = new Element("object");
		oElement.setAttribute("class", sourceClass.getName());
		oElement.setAttribute("id", id);
		target.getRootElement().addContent(oElement);
		
		if (!sourceClass.isArray()) {
			Field[] vars = getInstanceVariables(obj);
			for (Field fld : vars) {
				
				if (!Modifier.isPublic(fld.getModifiers())) 
					fld.setAccessible(true);
				
				Element fElt = new Element("field");
				fElt.setAttribute("name", fld.getName());
				fElt.setAttribute("declaringclass", fld.getDeclaringClass().getName());
				Class fldType = fld.getType();
				Object value = fld.get(obj);
				
				if (Modifier.isTransient(fld.getModifiers())) {
					value = null;
				}
				
				fElt.addContent(serializeVariable(fldType, value, target, map));
				oElement.addContent(fElt);				
			}
		}
		else {
			Class componentType = sourceClass.getComponentType();
			int l = Array.getLength(obj);
			oElement.setAttribute("length", Integer.toString(l));
			
			for (int i = 0; i < l; i++) {
				oElement.addContent(serializeVariable(componentType, Array.get(obj, i), target, map));
			}
		}
		return target;
	}
	
	private static Element serializeVariable(Class type, Object o, Document target, Map<Object, String> map) throws IllegalArgumentException, IllegalAccessException{
		if (o == null) {
			return new Element("null");
		}
		else if (! type.isPrimitive()) {
			Element e = new Element("reference");
			if (map.containsKey(o)) {
				e.setText(map.get(o));
			}
			else {
				e.setText(Integer.toString(map.size()));
				serializeHelper(o, target, map);				
			}
			return e;
		}
		else {
			Element e = new Element("value");
			e.setText(o.toString());
			return e;
		}
	}
	
	public static void main(String[] args) {
		Animal panda1 = new Animal( "Tian Tian", "male", "Ailuropoda melanoleuca", 271 );
		Animal panda2 = new Animal( "Mei Xiang", "female", "Ailuropoda melanoleuca", 221 );
		Zoo national = new Zoo( "National Zoological Park", "Washington, D.C." );
		
		national.add( panda1 );
		national.add( panda2 );
		
		try {
			XMLOutputter out = new XMLOutputter();
			Document d = serializeObject( national );
			out.output(d, System.out);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private static class Zoo {
		public Zoo(String name, String city) {
			this.name = name;
			this.city = city;
			this.animals = new Animal[10];
		}
		
		public void add(Animal animal) {
			if (index >= 100) {
				throw new OutOfMemoryError();
			}
			animals[index++] = animal;
		}
		private String name;
		private String city;
		private Animal[] animals;
		private transient int index = 0;
	}
	
	private static class Animal {
		public Animal(String name, String gender, String classification, int weight) {
			this.name = name;
			this.gender = gender;
			this.classification = classification;
			this.weight = weight;
		}
		private String name;
		private String gender;
		private String classification;
		private int weight;	
		
	}
	

}
