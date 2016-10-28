package com.example.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.utilities.FileHelper;
import com.example.utilities.StringUtility;


public class PhoneNumberProcessor
{
	private final static Logger log = Logger.getLogger(PhoneNumberProcessor.class.getName());

	List<String> phoneNumbers = new ArrayList<String>();
	private Map<String, List<String>> convertedNumberMap = new HashMap<String, List<String>>();
	private Predicate<String> validTranslationtester;

	HashMap<Character, char[]> phoneNumberConversionMap = new HashMap<Character, char[]>();
	
	private void initMap()
	{
		phoneNumberConversionMap.put('1', new char[] { '1' });
		phoneNumberConversionMap.put('2', new char[] { '2', 'A', 'B', 'C' });
		phoneNumberConversionMap.put('3', new char[] { '3', 'D', 'E', 'F' });
		phoneNumberConversionMap.put('4', new char[] { '4', 'G', 'H', 'I' });
		phoneNumberConversionMap.put('5', new char[] { '5', 'J', 'K', 'L' });
		phoneNumberConversionMap.put('6', new char[] { '6', 'M', 'N', 'O' });
		phoneNumberConversionMap.put('7', new char[] { '7', 'P', 'Q', 'R', 'S' });
		phoneNumberConversionMap.put('8', new char[] { '8', 'T', 'U', 'V' });
		phoneNumberConversionMap.put('9', new char[] { '9', 'W', 'X', 'Y', 'Z' });
		phoneNumberConversionMap.put('0', new char[] { '0' });
	}
	public PhoneNumberProcessor(List<Reader> readerList, Predicate<String> validPhoneNrWordTranslation)
	{
		initMap();
		validTranslationtester = validPhoneNrWordTranslation;
		
		FileHelper fileHelper = new FileHelper();
				
		for(Reader reader : readerList)
		{
			List<String> phoneNumbersPerFile = new ArrayList<String>();
			fileHelper.processInputFiles(reader, phoneNumbersPerFile, StringUtility.numberOnly);
			phoneNumbers.addAll(phoneNumbersPerFile);
		}
	}
	/***
	 * 
	 * @param inputNumber
	 * @return
	 */
	public List<String> getPossbileCombinations(String inputNumber)
	{
		List<String> words = new ArrayList<String>();
		
		if (!StringUtility.noneEmpty.test(inputNumber))
		{
			return words;
		}

		helper(words, new StringBuilder(), inputNumber, 0);
		
		return words;
	}
	/***
	 * 
	 * @param wordCombi
	 * @return
	 */
	public boolean isValidCombination(String wordCombi)
	{
		return validTranslationtester.test(wordCombi);
	}
	
	public Map<String, List<String>> getWordMap()
	{
		return convertedNumberMap;
	}

	private void helper(List<String> result, StringBuilder sb, String digits, int index)
	{
		if (index >= digits.length())
		{
			result.add(sb.toString());
			return;
		}

		char c = digits.charAt(index);
		char[] arr = phoneNumberConversionMap.get(c);

		for (int i = 0; i < arr.length; i++)
		{
			sb.append(arr[i]);
			helper(result, sb, digits, index + 1);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
