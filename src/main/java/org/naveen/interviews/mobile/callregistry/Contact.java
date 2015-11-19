package org.naveen.interviews.mobile.callregistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.naveen.interviews.mobile.callregistry.CallManager.CallDetails;

public class Contact {
	private String name;
	private final List<String> numbers;
	private final List<String> defenseCopy;
	
	public Contact(String name, String number) {
		this.name = name;
		this.numbers = new ArrayList<>();
		this.defenseCopy = Collections.unmodifiableList(this.numbers);
		this.numbers.add(number);
	}
	
	public String name() { return this.name; }
	public List<String> numbers() { return defenseCopy; }
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name)
			   .append("\n------------------------ ");
		for (String entry : numbers) {
			builder.append("\n").append(entry.toString());
		}
		return builder.append("\n").toString();
	}
}
