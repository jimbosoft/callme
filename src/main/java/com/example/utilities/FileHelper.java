package com.example.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.processor.PhoneWordGeneratorService;

/**
 * Helper class for processing files and readers
 * 
 * @author hejtmanekj
 *
 */
public class FileHelper
{
	private final static Logger log = Logger.getLogger(FileHelper.class.getName());
	private final String QUIT = "QUIT";
	/**
	 * Opens a file
	 * @param fileName - string containing the path and file name
	 * @return - open file reader or null
	 */
	public Reader getFileReader(String fileName)
	{
		Reader reader = null;
		
		try
		{
			reader = new FileReader(fileName);
		}
		catch (Exception ex)
		{
			log.warning("Failed open file: " + fileName);
		}
		return reader;
	}
	/**
	 * Read a word per line, clean unwanted characters and add to the collection
	 * 
	 * @param inputReader - the stream to read from
	 * @param collector - the collection to all the read words to
	 * @param cleaner - the function to clean words
	 */
	public void processInputFiles(Reader inputReader, Collection<String> collector, Function<String, String> cleaner)
	{	
		try
		{
			BufferedReader bufferinputReader = new BufferedReader(inputReader);		
			
			List<String> linesIn = new ArrayList<String>();
			
			String line = "";
			while ((line = bufferinputReader.readLine()) != null 
					&& !StringUtility.wordsOnlyAndToUpper.apply(line).equals(QUIT))
			{
				linesIn.add(line);
			}
			linesIn.stream().map(cleaner).filter(StringUtility.noneEmpty).forEach(s -> collector.add(s));	
			
		} catch (Throwable e)
		{
			log.info("Finished reading input " + e.getMessage());
		}		
	}	
}
