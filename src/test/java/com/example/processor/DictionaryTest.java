package com.example.processor;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Test;

public class DictionaryTest
{

	@Test
	public void dictionaryTest() throws Exception
	{
		DictionaryService dictionay = loadDictionary(" all \n call \n m*&^e \n 09ca7lm \n l  e t");
		
		assertTrue(dictionay.contains("ALL"));
		assertTrue(dictionay.contains("CALL"));
		assertTrue(dictionay.contains("ME"));
		assertTrue(dictionay.contains("CALM"));
		assertTrue(dictionay.contains("LET"));
	}
	private DictionaryService loadDictionary(String inputStr) throws Exception
	{
		StringReader sr = null;
		DictionaryService dictionary = new DictionaryService();
		
		try
		{
			sr = new StringReader(inputStr);
			dictionary.load(sr);
		} catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sr != null)
			{
				sr.close();
			}
		}
		return dictionary;
	}

}
