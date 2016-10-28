package com.example.processor;

import java.io.Reader;
import java.util.HashSet;

import com.example.utilities.FileHelper;
import com.example.utilities.StringUtility;

public class Dictionary
{
	private HashSet<String> dictionary = new HashSet<String>();

	public Dictionary(Reader dictionaryReader)
	{
		super();
		FileHelper fileHelper = new FileHelper();
		
		if (dictionaryReader != null)
		{
			fileHelper.processInputFiles(dictionaryReader, dictionary, StringUtility.wordsOnlyAndToUpper);
		}
	}
	/***
	 * 
	 * @param word
	 * @return
	 */
	public boolean isInDictionary(String word)
	{
		return dictionary.contains(word);
	}

}
