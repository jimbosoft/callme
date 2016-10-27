package com.example.utilities;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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
		bf = fileHelper.getFileReader(null);
		assertTrue(bf == null);
		bf = fileHelper.getFileReader("");
		assertTrue(bf == null);
	}
}
