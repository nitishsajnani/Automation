package ReverseStringUtility;

import org.testng.Reporter;

public class ReverseWordInString {
/*
 * This function is used for split the string into words and store in string aerray 
 */
	public static void reverseWordInString(String str) {

		String[] words = str.split(" ");
		String reversedString = "";
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			reversedString = reversedString + reverseString(word) + " ";
		}
		Reporter.log(str,true);
		Reporter.log(reversedString,true);
	}

	public static void reverse(String word) {
	 reverseWordInString(word);
	}

	/*
	 * This function is used for reversed the string
	 */

	public static String reverseString(String input) {

		char[] inputArr = input.toCharArray();
		char[] tempArr = new char[input.length()];
		int i = 0;
		int j = 0;
		{
			for (char ch : inputArr) {
				if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
					tempArr[i] = ch;
					i++;
				}
			}
		}
		i--;
		while (j < i) {
			char temp = tempArr[i];
			tempArr[i] = tempArr[j];
			tempArr[j] = temp;
			j++;
			i--;
		}
		for (i = 0, j = 0; i < input.length(); i++) {
			if (Character.isAlphabetic(inputArr[i]) || Character.isDigit(inputArr[i])) {
				inputArr[i] = tempArr[j++];
			}
		}
		return new String(inputArr);
	}
}