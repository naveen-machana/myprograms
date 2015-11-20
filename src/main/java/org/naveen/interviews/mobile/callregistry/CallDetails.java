package org.naveen.interviews.mobile.callregistry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CallDetails {
	private final Contact person;
	private final CallType callType;
	private final String phoneNumber;
	private final Date timeOfCall;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-mmm-yy hh:mm:ss:SSS");
	
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
