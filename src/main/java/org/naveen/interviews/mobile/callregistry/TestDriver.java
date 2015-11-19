package org.naveen.interviews.mobile.callregistry;

import java.util.Date;

public class TestDriver {

	public static void main(String[] args) {
		CallEvent missedCallFromSandy = new CallEvent("9999999999", new Date(), CallType.MISSED); 
		CallEvent dialledCallToMary = new CallEvent("9999999998", new Date(), CallType.OUTGOING);
		CallEvent missedCallFromSandy2 = new CallEvent("9999999999", new Date(), CallType.MISSED);
		CallEvent incomingCallFromSean = new CallEvent("9999999997", new Date(), CallType.INCOMING);
		CallEvent unknownIncomingCall = new CallEvent("8999999999", new Date(), CallType.INCOMING);
		CallEvent unknownOutGoingCall = new CallEvent("8799999999", new Date(), CallType.OUTGOING);
		CallEvent outGoingCallToSandy = new CallEvent("9999999999", new Date(), CallType.OUTGOING);
		
		CallManager callManager = new CallManager();
		callManager.performEvent(missedCallFromSandy);
		callManager.performEvent(dialledCallToMary);
		callManager.performEvent(missedCallFromSandy2);
		callManager.performEvent(incomingCallFromSean);
		callManager.performEvent(unknownIncomingCall);
		callManager.performEvent(unknownOutGoingCall);
		callManager.performEvent(outGoingCallToSandy);
		
		System.out.println(callManager);
	}

}
