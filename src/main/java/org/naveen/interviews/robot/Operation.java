package org.naveen.interviews.robot;

public interface Operation {
	boolean performOperation(Object obj) throws Exception;
	double requiredPower();
	boolean validate() throws Exception;
}
