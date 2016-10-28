package com.example.utilities;

import java.util.function.Function;
import java.util.function.Predicate;

public class StringUtility
{
	/**
	 *  Return true if the string is not null or empty
	 */
	static public Predicate<String> noneEmpty = 
			inputString -> inputString != null && inputString.length() > 0;
			
	static public Function<String, String> numberOnly =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^0-9]", "") : "";
			
	static public Function<String, String> wordsOnlyAndToUpper =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^A-Za-z]", "").toUpperCase() : "";
			
	static public Function<String, String> noPunctuationAndToUpper =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^A-Za-z0-9]", "").toUpperCase() : "";

}
