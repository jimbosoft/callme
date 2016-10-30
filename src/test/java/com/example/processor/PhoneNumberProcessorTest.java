package com.example.processor;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.example.processor.PhoneNumberProcessor;
import com.example.utilities.ValidPhoneNrWord;

public class PhoneNumberProcessorTest
{
	PhoneNumberProcessor phoneNumbers = new PhoneNumberProcessor();
	
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
		assertTrue(resultMap.size() == 0);
	}
	
	@Test
	public void processPhoneNumbersTest()
	{
		final Map<String, String> resultMap= new HashMap<String, String>();
		resultMap.put("1A3", ""); resultMap.put("1B3", "");	resultMap.put("1C3", "");
		resultMap.put("1AD", ""); resultMap.put("1AE", ""); resultMap.put("1AF", "");
		resultMap.put("1BD", ""); resultMap.put("1BE", ""); resultMap.put("1BF", "");
		resultMap.put("1CD", ""); resultMap.put("1CE", ""); resultMap.put("1CF", "");

		StringReader sr = new StringReader("#@123dert \n hello");

		List<Reader> readerList = new ArrayList<Reader>();
		readerList.add(sr);
		Map<String, List<String>> processedMap = phoneNumbers.processPhoneNumbers(
											readerList, new ValidPhoneNrWord());

		assertTrue(processedMap.size() == 1);
		List<String> words = processedMap.get("123");
		assertTrue(words != null);
		assertTrue(words.size() > 0);

		for (String s : words)
		{
			if (!resultMap.containsKey(s))
			{
				System.out.println("Can't find: " + s);
			}
			assertTrue(resultMap.remove(s) != null);
		}
		assertTrue(resultMap.size() == 0);
	}
}
