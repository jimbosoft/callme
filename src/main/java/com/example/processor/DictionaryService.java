package com.example.processor;

import java.io.Reader;
import java.util.HashSet;

import com.example.utilities.FileHelper;
import com.example.utilities.StringUtility;

/***
 * Lookup of valid dictionary words
 * 
 * @author hejtmanekj
 *
 */
public class DictionaryService implements Dictionary
{
	private FileHelper fileHelper = new FileHelper();
	private HashSet<String> dictionary = new HashSet<String>();

	public DictionaryService()
	{
		super();
	}
	/***
	 * Load the words for the dictionary from an file
	 * @param fileName - name of file to read
	 * @throws Exception
	 */
	public void load(String fileName) throws Exception
	{
		Reader dictionaryReader = null;
		
		try
		{
			dictionaryReader = fileHelper.getFileReader(fileName);
			
			if (dictionaryReader != null)
			{
				load(dictionaryReader);
			}
			else
			{
				throw new Exception("Failed to load dictionary");
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			if (dictionaryReader != null)
			{
				dictionaryReader.close();
			}
		}
	}
	public void load(Reader inputSource) throws Exception
	{
		fileHelper.processInputFiles(inputSource, dictionary, StringUtility.wordsOnlyAndToUpper);
	}
	/***
	 * Check if the input word is in the dictionary
	 * 
	 * @param word - String to check if in the dictionary
	 * @return - true if the word is in the dictionary, false otherwise
	 */
	public boolean contains(String word)
	{
		return dictionary.contains(word);
	}
	/***
	 * How many words are in this dictionary
	 * @return - number of words
	 */
	public int size()
	{
		return dictionary.size();
	}

}
