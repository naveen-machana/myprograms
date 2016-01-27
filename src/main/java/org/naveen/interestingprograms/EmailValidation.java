package org.naveen.interestingprograms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidation {

	public static void main(String[] args) {
		String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		
		String email =  "naresh.pagadapu@gmail.com";
		Matcher matcher = pattern.matcher(email);
		if (! matcher.matches()) {
			System.out.println("Invalid email address");
		}
		else {
			System.out.println("Valid email address");
		}
	}

}
