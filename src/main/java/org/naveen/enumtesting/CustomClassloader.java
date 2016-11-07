package org.naveen.enumtesting;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

// the below code is taken from 
// http://yiyujia.blogspot.in/2011/10/java-class-loader-and-static-variable.html
public class CustomClassloader extends ClassLoader {

	public CustomClassloader() {
		super(CustomClassloader.class.getClassLoader());
	}

	public CustomClassloader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("loading class '" + name + "'");
		if (name.startsWith("org.naveen.")) {
			return getClass(name);
		}
		return super.loadClass(name);
	}
	
    private synchronized  Class<?> getClass(String name)
            throws ClassNotFoundException {
     
            // is this class already loaded?
            Class<?> cls = findLoadedClass(name);
            if (cls != null) {
                System.out.println("class " + name + "has been loaded.");
                return cls;
            } else {
                System.out.println("class " + name + " has not been loaded. Loading now.");
            }
     
     
            // We are getting a name that looks like
            // org.naveen.enumtesting.Days
            // and we have to convert it into the .class file name
            // like org/naveen/enumtesting/Days.class
            String file = name.replace('.', File.separatorChar)
                + ".class";
            byte[] b = null;
            try {
                // This loads the byte code data from the file
                b = loadClassData(file);
                // defineClass is inherited from the ClassLoader class
                // and converts the byte array into a Class
                cls = defineClass(name, b, 0, b.length);
                resolveClass(cls);
                return cls;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    
    private byte[] loadClassData(String name) throws IOException {
        // Opening the file
        InputStream stream = getClass().getClassLoader()
            .getResourceAsStream(name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        // Reading the binary data
        in.readFully(buff);
        in.close();
        return buff;
    }
}
