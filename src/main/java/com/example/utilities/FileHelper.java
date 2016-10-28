package com.example.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.example.processor.PhoneNumberProcessor;

public class FileHelper
{
	private final static Logger log = Logger.getLogger(FileHelper.class.getName());
		
	private StringUtility stringUtil = new StringUtility();
	
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
	
	public void processInputFiles(Reader inputReader, Collection<String> collector, Function<String, String> cleaner)
	{	
		try
		{
			BufferedReader bufferinputReader = new BufferedReader(inputReader);		
			bufferinputReader.lines().map(cleaner).filter(stringUtil.noneEmpty).forEach(s -> collector.add(s));	
			
		} catch (Exception e)
		{
			log.info("Finished reading input " + e.getMessage());
		}		
	}	
}
