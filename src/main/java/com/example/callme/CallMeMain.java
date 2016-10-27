package com.example.callme;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.example.processor.Dictionary;
import com.example.processor.PhoneNumberProcessor;
import com.example.processor.Wordsmith;
import com.example.utilities.FileHelper;
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
    	List<String> inputFiles = new ArrayList<String>();
    	for (String s: args) 
    	{
    		if (s.startsWith("-d"))
    		{
    			dictionaryFileName = s.substring(1).trim();
    		}
    		else
    		{
    			inputFiles.add(s);
    		}
        }
		FileHelper fileHelper = new FileHelper();
		
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
