package org.naveen.interviews.mobile.callregistry;

import java.util.Date;
import java.util.Objects;

public class CallEvent {
	private final Date timeOfCall;
	private final CallType callType;
	private final String phoneNumber;
	
	public CallEvent(String phoneNumber, Date timeOfCall, CallType callType) {
		Objects.requireNonNull(phoneNumber);
		Objects.requireNonNull(timeOfCall);
		Objects.requireNonNull(callType);
		this.phoneNumber = phoneNumber;
		this.timeOfCall = (Date) timeOfCall.clone();
		this.callType = callType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Date getTimeOfCall() {
		return timeOfCall;
	}

	public CallType getCallType() {
		return callType;
	}
}
