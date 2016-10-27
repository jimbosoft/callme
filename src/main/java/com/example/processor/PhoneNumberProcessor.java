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


public class PhoneNumberProcessor
{
	private final static Logger log = Logger.getLogger(PhoneNumberProcessor.class.getName());

	private Map<String, List<String>> convertedNumberMap = new HashMap<String, List<String>>();
	private Predicate<String> validTranslationtester;

	public PhoneNumberProcessor(List<String> inputFiles, Predicate<String> validPhoneNrWordTranslation)
	{
		validTranslationtester = validPhoneNrWordTranslation;
		
		FileHelper fileHelper = new FileHelper();
		
		List<String> phoneNumbers = new ArrayList<String>();
		
		if (inputFiles.size() > 0)
		{
			List<Reader> readerList = inputFiles.stream()
				.map(fileName -> fileHelper.getFileReader(fileName))
				.filter(f -> f != null).collect(Collectors.toList());

			for(Reader r : readerList)
			{
				List<String> phoneNumbersPerFile = new ArrayList<String>();
				fileHelper.processInputFiles(r, phoneNumbers, FileHelper.CleanigMode.NUMBERONLY);
				phoneNumbers.addAll(phoneNumbersPerFile);
			}
		}
		else
		{
			Reader inputReader = new InputStreamReader(System.in);			
			fileHelper.processInputFiles(inputReader, phoneNumbers, FileHelper.CleanigMode.NUMBERONLY);
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
}
