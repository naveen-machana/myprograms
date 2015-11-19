package org.naveen.interviews.mobile.callregistry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.text.SimpleAttributeSet;

public class CallManager {	
	
	private static final int MAX_SIZE = 20;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-mmm-yy hh:mm:ss:SSS");
	private static class LRUCache extends LinkedHashMap<String, CallManager.CallEntry> {
		public LRUCache() {
			super(16, 0.75f, true);
		}

		protected boolean removeEldestEntry(Map.Entry<String, CallManager.CallEntry> eldest) {
	        return size() > MAX_SIZE;
	    }
	}
	
	public static class CallDetails{
		private final Contact person;
		private final CallType callType;
		private final String phoneNumber;
		private final Date timeOfCall;
		
		CallDetails(Contact person, CallType callType, String phoneNumber, Date timeOfCall) {
			Objects.requireNonNull(person);
			Objects.requireNonNull(callType);
			Objects.requireNonNull(phoneNumber);
			Objects.requireNonNull(timeOfCall);
			this.person = person;
			this.callType = callType;
			this.phoneNumber = phoneNumber;
			this.timeOfCall = timeOfCall;
		}
		
		@Override
		public String toString() {			
			StringBuilder builder = new StringBuilder();
			builder.append("[").append(callType.toString())
				   .append(", ").append(phoneNumber)
				   .append(", ").append(sdf.format(timeOfCall)).append("]");
			return builder.toString();
		}
	}
	
	public static class CallEntry {
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

	private Map<String, CallManager.CallEntry> callLog = new LRUCache();
	
	public void performEvent(CallEvent event) {
		String phoneNumber = event.getPhoneNumber();
		Contact contact = ContactsManager.getContact(phoneNumber);
		CallDetails callDetails = new CallDetails(contact, event.getCallType(), phoneNumber, event.getTimeOfCall());
		
		CallEntry callLogEntry = callLog.get(phoneNumber);
		if (callLogEntry == null) {
			callLogEntry = new CallEntry(contact);
		}
		callLogEntry.add(callDetails);
		callLog.put(phoneNumber, callLogEntry);
	}
	
	public Iterator<Map.Entry<String, CallEntry>> getCallLog() {
		return callLog.entrySet().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		List<Map.Entry<String, CallEntry>> entries = new LinkedList<>(callLog.entrySet());
		for (int i = entries.size()-1; i >= 0; i--) {
			builder.append(entries.get(i).getValue().toString());
		}
		return builder.toString();
	}
}
