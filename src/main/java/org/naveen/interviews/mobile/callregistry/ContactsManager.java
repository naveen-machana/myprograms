package org.naveen.interviews.mobile.callregistry;

import java.util.HashMap;
import java.util.Map;

public class ContactsManager {
	private static Map<String, Contact> contacts = new HashMap<>();
	static {
		contacts.put("9999999999", new Contact("Sandy", "9999999999"));
		contacts.put("9999999998", new Contact("Mary", "9999999998"));
		contacts.put("9999999997", new Contact("Sean", "9999999997"));
		contacts.put("9999999996", new Contact("English", "9999999996"));
	}
	private static final String UNKNOWN_CONTACT = "Unknown Contact";
	public static Contact getContact(String number) {
		Contact ct = contacts.get(number);
		if (ct == null) {
			ct = new Contact(UNKNOWN_CONTACT, number);
		}
		return ct;
	}
}
