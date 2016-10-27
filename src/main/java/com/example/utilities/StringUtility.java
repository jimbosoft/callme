package com.example.utilities;

import java.util.function.Function;
import java.util.function.Predicate;

public class StringUtility
{
	public Predicate<String> noneEmpty = 
			inputString -> inputString != null && inputString.length() > 0;
			
	public Function<String, String> numberOnly =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^0-9]", "") : "";
			
	public Function<String, String> wordsOnly =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^A-Za-z]", "").toUpperCase() : "";
			
	public Function<String, String> noPunctuation =
			inpString -> noneEmpty.test(inpString) ? inpString.replaceAll("[^A-Za-z0-9]", "").toUpperCase() : "";

}
