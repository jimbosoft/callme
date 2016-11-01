package com.example.processor;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.example.utilities.ValidPhoneNrWord;

public class WordsmithTest
{
	@Test
	public void produceWordsTest() throws Exception
	{
		final Map<String, String> resultMap= new HashMap<String, String>();
		resultMap.put("OVER-8-HE", ""); resultMap.put("OVERT-HE", "");	resultMap.put("OVER-THE", "");

		DictionaryService dictionary = loadDictionary(" over \n overt \n the \n he");
		
		PhoneWordGeneratorService ppp = new PhoneWordGeneratorService();
		List<String> combolist = ppp.getPossbileCombinations("6837843").stream()
				.filter(new ValidPhoneNrWord())
				.collect(Collectors.toList());

		Wordsmith ws = new Wordsmith();
		List<String> possibeWords = ws.produceWords( combolist, dictionary);
		assertTrue(possibeWords != null);
		assertTrue(possibeWords.size() > 0);
		
		checkResult(possibeWords, resultMap);

	}
	
	@Test
	public void produceWordsTest2() throws Exception
	{
		final Map<String, String> resultMap= new HashMap<String, String>();
		resultMap.put("2-ALL-8-ME-8", ""); resultMap.put("2-ALL-8-MET", "");	resultMap.put("2-ALL-8-NET", "");
		resultMap.put("2-ALL-TOE-8", ""); resultMap.put("BALK-8-ME-8", ""); resultMap.put("BALK-8-MET", "");
		resultMap.put("BALK-8-NET", ""); resultMap.put("BALK-TOE-8", ""); resultMap.put("BALL-8-ME-8", "");
		resultMap.put("BALL-8-MET", ""); resultMap.put("BALL-8-NET", ""); resultMap.put("BALL-TOE-8", "");
		resultMap.put("BALL-8-ME-8", ""); resultMap.put("BALL-8-MET", ""); resultMap.put("BALL-8-NET", "");
		resultMap.put("BALL-TOE-8", ""); resultMap.put("CALK-8-ME-8", ""); resultMap.put("CALK-8-MET", "");
		resultMap.put("CALK-TOE-8", ""); resultMap.put("CALL-8-ME-8", ""); resultMap.put("CALL-8-MET", "");
		resultMap.put("CALL-8-NET", ""); resultMap.put("CALL-8-NET", ""); resultMap.put("CALK-8-NET", "");
		resultMap.put("CALL-TOE-8", "");
		
		DictionaryService dictionary = loadDictionary(" all \n call \n me \n calm \n let \n calk \n ball \n balk \n met \n net \n toe");
		
		PhoneWordGeneratorService ppp = new PhoneWordGeneratorService();
		List<String> combolist = ppp.getPossbileCombinations("22558638").stream()
				.filter(new ValidPhoneNrWord())
				.collect(Collectors.toList());

		Wordsmith ws = new Wordsmith();
		List<String> possibeWords = ws.produceWords( combolist, dictionary);

		assertTrue(possibeWords != null);
		assertTrue(possibeWords.size() > 0);

		checkResult(possibeWords, resultMap);
	}
	
	private void checkResult(List<String> possibeWords, Map<String, String> resultMap)
	{
		for (String word : possibeWords) 
		{
			if (!resultMap.containsKey(word))
			{
				System.out.println("Can't find: " + word);
			}
			assertTrue(resultMap.remove(word) != null);
		}
		assertTrue(resultMap.size() == 0);
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
