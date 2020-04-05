package TestCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ReverseStringUtility.ReverseWordInString;

public class ReverseStringCase extends ReverseWordInString  {

	@Test(description="User validate reverse string with alphabetics characters")
	public void TC001_ReverseStringWithAlphabeticCharacters() {
		reverse("My Name is nitish");
		Reporter.log("Alphabetic string have been reversed", true);
	}
	
	@Test(description="User validate reverse string with alphanumeric characters")
	public void TC002_ReverseStringWithAlphaNumericCharacters() {
		reverse("Sn123 Niit232 is nitis323h");
		Reporter.log("AlphaNumeric string have been reversed", true);
	}
	@Test(description="User validate reverse string with alphabetics and special characters")
	public void TC003_ReverseStringWithAlphabeticAndSpecialCharacters() {
		reverse("Sn1~ Niit$#%, i$ n!t!sh");
		Reporter.log("Only alphabetic characters have been reversed", true);
	}

	@Test(description="User validate reverse string with Numeric and special characters")
	public void TC004_ReverseStringWithNumericAndSpecialCharacters() {
		reverse("32~ 23$#3232%, 09$3 2!32!12");
		Reporter.log("Only Numeric characters have been reversed", true);
	}
	
	@Test(description="User validate string should not be reversed for special characters")
	public void TC004_ReverseStringWithSpecialCharacters() {
		reverse("@$%$% !@## %$)(_+**?.,; %?");
		Reporter.log("Special characters have not been reversed", true);
	}
	
	@Test(description="User validate reverse string with alphabetics , numerics and special characters")
	public void TC005_ReverseStringWithAlphabeticNumericAndSpecialCharacters() {
		reverse("Niti$s### S@j@!131 ;./\\ ^&*(_+=-<> 54ewe!2(_+**? f45*& y434 rej 43 @# 2232 #$");
		Reporter.log("Only Alphabetic and Numeric characters have been reversed", true);
	}
}
