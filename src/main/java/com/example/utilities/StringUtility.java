package com.example.utilities;

import java.util.function.Function;
import java.util.function.Predicate;

/***
 * Helper functions for dealing with strings
 * 
 * @author hejtmanekj
 *
 */
public class StringUtility
{
	/**
	 *  Return true if the string is not null or empty
	 */
	static public Predicate<String> noneEmpty = 
			inputString -> inputString != null && inputString.length() > 0;
	
	/**
	 *  Removes all none digits from the string
	 * @param - string to convert
	 * @return - string with all none digits removed
	 */
	static public Function<String, String> numberOnly =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^0-9]", "") : "";
			
	/**
	 *  Removes all none alphabetic characters from the string 
	 *  and converts the result to upper case
	 * @param - string to convert
	 * @return - string in upper case with all none alphabetic characters removed
	 */
	static public Function<String, String> wordsOnlyAndToUpper =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^A-Za-z]", "").toUpperCase() : "";
			
	/**
	 *  Removes all none alpha/numeric characters from the string
	 * @param - string to convert
	 * @return - string with all none alpha/numeric characters removed
	 */
	static public Function<String, String> noPunctuationAndToUpper =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^A-Za-z0-9]", "").toUpperCase() : "";

}
