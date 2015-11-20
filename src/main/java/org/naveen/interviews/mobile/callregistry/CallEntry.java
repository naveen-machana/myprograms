package org.naveen.interviews.mobile.callregistry;

import java.util.LinkedList;

public class CallEntry {
	private final Contact contact;
	private final LinkedList<CallDetails> history;
	
	public CallEntry(Contact contact) {
		this.contact = contact;
		history = new LinkedList<>();
	}
	
	public void add(CallDetails logEntry) {
		history.addFirst(logEntry);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(contact.name())
			   .append("\n------------------------ ");
		for (CallDetails entry : history) {
			builder.append("\n").append(entry.toString());
		}
		return builder.append("\n\n").toString();
	}
}
