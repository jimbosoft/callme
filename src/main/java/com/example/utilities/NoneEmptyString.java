package com.example.utilities;

import java.util.function.Predicate;

public class NoneEmptyString implements Predicate<String>
{
	@Override
	public boolean test(String inputString)
	{
		return inputString != null && inputString.length() > 0;
	}

}
