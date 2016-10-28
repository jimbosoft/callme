package com.example.callme;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.example.processor.Dictionary;
import com.example.processor.PhoneNumberProcessor;
import com.example.processor.Wordsmith;
import com.example.utilities.FileHelper;
import com.example.utilities.StringUtility;
import com.example.utilities.ValidPhoneNrWord;

/**
 * Hello world!
 *
 */
public class CallMeMain 
{
    public static void main( String[] args )
    {
    	String dictionaryFileName = "dictionary.txt";

    	FileHelper fileHelper = new FileHelper();
    	List<Reader> inputFiles = new ArrayList<Reader>();
    	for (String s: args) 
    	{
    		if (s.startsWith("-d"))
    		{
    			dictionaryFileName = s.substring(1).trim();
    		}
    		else
    		{
    			Reader fileReader = fileHelper.getFileReader(s);
    			if (fileReader != null)
    			{
    				inputFiles.add(fileReader);
    			}
    		}
        }

		if (inputFiles.size() == 0)
		{
			inputFiles.add(new InputStreamReader(System.in));
		}
		
		Reader dictionaryReader = fileHelper.getFileReader(dictionaryFileName);
		if (dictionaryReader == null)
		{
			System.out.println("Error: no dictionary by the name of " 
					+ dictionaryFileName + " found, exiting ...");
			return;
		}
		
		Dictionary dictionary = new Dictionary(dictionaryReader);
    	PhoneNumberProcessor phoneNumbers = new PhoneNumberProcessor(inputFiles, new ValidPhoneNrWord());
    	Wordsmith converter = new Wordsmith(phoneNumbers.getWordMap(), dictionary);
     }
}
