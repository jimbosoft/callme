package com.example.callme;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.processor.DictionaryService;
import com.example.processor.PhoneWordGeneratorService;
import com.example.processor.Wordsmith;
import com.example.utilities.FileHelper;
import com.example.utilities.ValidPhoneNrWord;

public class CallMeMain
{
	public static void main(String[] args)
	{
		String dictionaryFileName = "dictionary.txt";
		List<String> inputFiles = new ArrayList<String>();
		Map<String, List<String>> phoneNumberWordMap = new HashMap<String, List<String>>();
		DictionaryService dictionary = new DictionaryService();
		//
		// Read command line arguments
		//
		boolean dictionaryOption = false;
		for (String s : args)
		{
			if (dictionaryOption)
			{
				dictionaryFileName = s.trim();		
				dictionaryOption = false;
			}
			else if (s.startsWith("-d"))
			{
				dictionaryOption = true;
			} else
			{
				inputFiles.add(s);
			}
		}
		//
		// Set the dictionary
		//
		try
		{
			dictionary.load(dictionaryFileName);
		} catch (Exception ex)
		{
			System.out.println("Error: no dictionary by the name of " + dictionaryFileName + " found, exiting ...");
			return;
		}
		//
		// Produce all possible words for all inputs
		//
		PhoneWordGeneratorService phoneNumberProcessor = new PhoneWordGeneratorService();
		phoneNumberWordMap = phoneNumberProcessor.processPhoneNumberInput(inputFiles, new ValidPhoneNrWord());
		//
		// Print to the screen the resulting valid word equivalents
		//
		Wordsmith converter = new Wordsmith();
		for (Map.Entry<String, List<String>> entry : phoneNumberWordMap.entrySet())
		{
			List<String> resultList = converter.produceWords(entry.getValue(), dictionary);

			for (int i = 0; i < resultList.size(); i++)
			{
				if (i == 0)
				{
					System.out.print(entry.getKey() + ": ");
				}
				System.out.print(resultList.get(i) + " ");
			}
			System.out.println();
		}
	}
}
