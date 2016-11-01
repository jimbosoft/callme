package com.example.utilities;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class FileHelperTest
{
	FileHelper fileHelper = new FileHelper();

	@Test
	public void fileReaderTest()
	{
		String testFileName = "testFile.txt";
		Reader bf = null;
		File file = null;
		try
		{
			file = new File(testFileName);
			file.createNewFile();

			bf = fileHelper.getFileReader(null);
			assertTrue(bf == null);
			bf = fileHelper.getFileReader("");
			assertTrue(bf == null);
			bf = fileHelper.getFileReader(testFileName);
			assertTrue(bf != null);
			
		} catch (Exception ex)
		{
			System.out.print("Test file failed " + ex.getMessage());
		}
		finally
		{
			try
			{
				if (bf != null)
				{
					bf.close();
				}
				if (file != null)
				{
					file.delete();
				}
			}
			catch (Exception ex)
			{
				System.out.print("Deleting test file failed " + ex.getMessage());
			}
		}
	}
	
	@Test
	public void testProcessInputFiles() throws IOException
	{
		StringReader sr = new StringReader("1234dert \n hello\n 42");
		List<String> returnedList = new ArrayList<String>();
		fileHelper.processInputFiles(sr, returnedList, StringUtility.numberOnly);
		boolean found1234 = false;
		boolean found42 = false;
		for (String s : returnedList)
		{
			if (s.equals("1234"))
			{
				found1234 = true;
			}
			if (s.equals("42"))
			{
				found42 = true;
			}
		}
		assertTrue(returnedList.size() == 2);
		assertTrue(found1234 && found42);
		
		sr.reset();
		returnedList.clear();
		fileHelper.processInputFiles(sr, returnedList, StringUtility.wordsOnlyAndToUpper);
		boolean founddert = false;
		boolean foundhello = false;
		for (String s : returnedList)
		{
			if (s.equals("DERT"))
			{
				founddert = true;
			}
			if (s.equals("HELLO"))
			{
				foundhello = true;
			}
		}
		assertTrue(returnedList.size() == 2);
		assertTrue(founddert && foundhello);		
	}
}
