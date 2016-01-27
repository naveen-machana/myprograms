package org.naveen.interestingprograms;

public class ExtractStrings {
	
	public static void replace(String expression, String delimiter, String delimitedString, int currentDelimiterPosition, int numberOfTokensToBeReplaced) {
		
		if (currentDelimiterPosition >= delimiter.length()) {			
			return;
		}

		char currentDelimiter = delimiter.charAt(currentDelimiterPosition);
		delimitedString = delimitedString + currentDelimiter;
		int beginPosition = 0;
		int currentPosition = delimitedString.indexOf(currentDelimiter);
		int numberOfTokensReplaced = 0;

		while (currentPosition != -1) {
			String token = delimitedString.substring(beginPosition, currentPosition);
			if (numberOfTokensToBeReplaced > 1) {				
				if (currentDelimiterPosition + 1 >= delimiter.length()) {
					String currentTokenNumber = "${" + numberOfTokensReplaced + "}$";
					expression = expression.replace(currentTokenNumber, token);
					
					if (numberOfTokensReplaced + 1 == numberOfTokensToBeReplaced) {
						System.out.println(expression);
					}
					numberOfTokensReplaced++;
				}			
				replace(expression, delimiter, token, currentDelimiterPosition + 1, numberOfTokensToBeReplaced);
			}
			else {
				String expressionBackUp = expression;
				String currentTokenNumber = "${" + numberOfTokensReplaced + "}$";
				expression = expression.replace(currentTokenNumber, token);				
				System.out.println(expression);
				expression = expressionBackUp;
			}
			beginPosition = currentPosition + 1;
			currentPosition = delimitedString.indexOf(currentDelimiter, beginPosition);
		}		
	}
	
	public static void replaceTokens(String expression, String delimiter, String delimitedString) {
		
		int i = 0;
		while (true) {
			String currentTokenNumber = "${" + i + "}$";
			if (! expression.contains(currentTokenNumber)) {
				break;
			}
			i++;
		}
		
		if (i == 0) {
			return;
		}
		
		replace(expression, delimiter, delimitedString, 0, i);
		
	}
	
	public static void main(String[] args) {
		
		String expression = "esd_sp_SystemSetupBaseData_ModelStates @DataModelId=@DataModelId,@ProcessName = N'TemplateApproval' ,@StateName = N'${0}$', @UICulture = N'en-US' ,@StateCultureValue = N'${1}$'";
		String delimiter = ",:";
		String delimitedString = "Draft:Draft,Review:Review,Approval:Approval;IntReview:IntReview,Sem:Sem,Test:Test";
		//String delimitedString = "Draft,Review,Approval,IntReview,Sem,Test";
		replaceTokens(expression, ";,:", delimitedString);

	}

}
