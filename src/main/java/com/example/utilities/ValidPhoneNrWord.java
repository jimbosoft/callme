package com.example.utilities;

import java.util.function.Predicate;

/**
 * Checks if the string has two consecutive digits
 * @return true if it does not 
 * 		   false if it does
 * @author hejtmanekj
 *
 */
public class ValidPhoneNrWord implements Predicate<String>
{
	/**
	 * Returns true if the word contains no consecutive digits
	 */
	public boolean test(String inpString)
	{
		if (inpString == null || inpString.length() == 0)
		{
			return false;
		}
		
		int lastIdx = -2;
		for (int i = 0; i < inpString.length(); i++)
		{
			if (Character.isDigit(inpString.charAt(i)))
			{
				if (lastIdx == i - 1)
				{
					return false;
				}
				lastIdx = i;
			}
		}
		return true;
	}
}