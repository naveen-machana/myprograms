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
	
	private static class LRUCache extends LinkedHashMap<NumberTimed, CallEntry> {
		public LRUCache() {
			super(32, 0.75f, true);
		}

		protected boolean removeEldestEntry(Map.Entry<NumberTimed, CallEntry> eldest) {
	        return size() > MAX_SIZE;
	    }
	}	
	
	private Map<NumberTimed, CallEntry> callLog = new LRUCache();
	
	public void performEvent(CallEvent event) {
		String phoneNumber = event.getPhoneNumber();
		Contact contact = ContactsManager.getContact(phoneNumber);
		CallDetails callDetails = new CallDetails(contact, event.getCallType(), phoneNumber, event.getTimeOfCall());
		
		NumberTimed cacheKey = new NumberTimed(phoneNumber, event.getTimeOfCall());
		CallEntry callLogEntry = callLog.get(cacheKey);		
		
		if (callLogEntry == null) {
			callLogEntry = new CallEntry(contact);
		}
		callLogEntry.add(callDetails);
		callLog.put(cacheKey, callLogEntry);
	}
	
	public Iterator<Map.Entry<NumberTimed, CallEntry>> getCallLog() {
		return callLog.entrySet().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		List<Map.Entry<NumberTimed, CallEntry>> entries = new LinkedList<>(callLog.entrySet());
		for (int i = entries.size()-1; i >= 0; i--) {
			builder.append(entries.get(i).getValue().toString());
		}
		return builder.toString();
	}
	
	private static class NumberTimed{
		private String number;
		private Date day;
		NumberTimed(String number, Date day) {
			Objects.requireNonNull(number);
			Objects.requireNonNull(day);
			this.number = number;
			this.day = day;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) return true;
			if (!(o instanceof NumberTimed)) return false;
			NumberTimed n = (NumberTimed)o;
			if (!n.number.equals(this.number)) return false;
			return compareDayIgnoreTime(day, n.day);
		}
		
		@Override
		public int hashCode() {
			int prime = 17, res = 0;
			res = prime * number.hashCode();
			res = res * 17 + (day.getYear());
			res = res * 17 + (day.getMonth());
			res = res * 17 + (day.getDay());
			return res;
			
		}
		
		private boolean compareDayIgnoreTime(Date d1, Date d2) {
			if (d1.getYear() != d2.getYear()) return false;
		    if (d1.getMonth() != d2.getMonth()) return false;		        
		    if (d1.getDate() != d2.getDate()) return false;
		    return true;
		}
	}
}
