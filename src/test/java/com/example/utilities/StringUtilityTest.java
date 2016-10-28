package com.example.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilityTest
{	
	String mixedString = "_a+b=c{d[e}f]g:1;2\"3'4,5<6.h>i/j?k l";
	String wordString = "ABCDEFGHIJKL";
	String numberString = "123456";
	
	@Test
	public void testNoneEmpty()
	{
		assertTrue(!StringUtility.noneEmpty.test(null));
		assertTrue(!StringUtility.noneEmpty.test(""));
		assertTrue(StringUtility.noneEmpty.test(" "));
		assertTrue(StringUtility.noneEmpty.test("1"));
	}
	@Test
	public void testCleanString()
	{
		assertTrue(StringUtility.noPunctuationAndToUpper.apply(null).equals("")); 
		assertTrue(StringUtility.noPunctuationAndToUpper.apply("").equals("")); 
		assertTrue(StringUtility.noPunctuationAndToUpper.apply("!@#$").equals("")); 
		assertTrue(StringUtility.noPunctuationAndToUpper.apply("!1@2#3$4%5^6&7*8(9)0-1 ").equals("12345678901")); 
		assertTrue(StringUtility.noPunctuationAndToUpper.apply("!1@2#3$4%5^6&7*8(9)0-1 ").equals("12345678901")); 
		assertTrue(StringUtility.noPunctuationAndToUpper.apply(mixedString).equals("ABCDEFG123456HIJKL")); 
	}
	@Test
	public void testRemoveDigits()
	{
		assertTrue(StringUtility.wordsOnlyAndToUpper.apply(null).equals("")); 
		assertTrue(StringUtility.wordsOnlyAndToUpper.apply("").equals("")); 
		assertTrue(StringUtility.wordsOnlyAndToUpper.apply("!@#$").equals("")); 
		assertTrue(StringUtility.wordsOnlyAndToUpper.apply(mixedString).equals(wordString)); 
	}
	@Test
	public void testRemoveLetters()
	{
		assertTrue(StringUtility.numberOnly.apply(null).equals("")); 
		assertTrue(StringUtility.numberOnly.apply("").equals("")); 
		assertTrue(StringUtility.numberOnly.apply("!@#$").equals("")); 
		assertTrue(StringUtility.numberOnly.apply(mixedString).equals(numberString)); 
	}		
}
