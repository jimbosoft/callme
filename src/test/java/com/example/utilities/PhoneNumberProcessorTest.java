package com.example.utilities;

import static org.junit.Assert.*;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.junit.Test;

import com.example.processor.PhoneNumberProcessor;

public class PhoneNumberProcessorTest
{
	PhoneNumberProcessor phoneNumbers = new PhoneNumberProcessor(new ArrayList<Reader>(), new ValidPhoneNrWord());
	
	@Test
	public void getPossbileCombinationsTest()
	{
		String phoneNumber = "123";
		final Map<String, String> resultMap= new HashMap<String, String>();
		resultMap.put("123", "");
		resultMap.put("1A3", ""); resultMap.put("1B3", "");	resultMap.put("1C3", "");
		resultMap.put("12D", ""); resultMap.put("12E", "");	resultMap.put("12F", "");
		resultMap.put("1AD", ""); resultMap.put("1AE", ""); resultMap.put("1AF", "");
		resultMap.put("1BD", ""); resultMap.put("1BE", ""); resultMap.put("1BF", "");
		resultMap.put("1CD", ""); resultMap.put("1CE", ""); resultMap.put("1CF", "");
		
		
		List<String> resultList = phoneNumbers.getPossbileCombinations(phoneNumber);
		
		for (String s : resultList)
		{
			if (!resultMap.containsKey(s))
			{
				System.out.println("Can't find: " + s);
			}
			assertTrue(resultMap.remove(s) != null);
		}
		//resultList.forEach(w -> assertTrue(resultMap.remove(w) != null));
		assertTrue(resultMap.size() == 0);
	}
}
