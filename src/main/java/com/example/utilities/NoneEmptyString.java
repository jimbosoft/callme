package com.example.utilities;

import java.util.function.Predicate;


public class NoneEmptyString implements Predicate<String>
{
	/**
	 * Test if the string in not null AND not empty
	 */
	@Override
	public boolean test(String inputString)
	{
		return inputString != null && inputString.length() > 0;
	}

}
