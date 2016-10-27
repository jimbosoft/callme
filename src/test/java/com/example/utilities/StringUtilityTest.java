package com.example.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilityTest
{
	StringUtility strUtil = new StringUtility();
	
	String mixedString = "_a+b=c{d[e}f]g:1;2\"3'4,5<6.h>i/j?k l";
	String wordString = "ABCDEFGHIJKL";
	String numberString = "123456";
	
	@Test
	public void testCleanString()
	{
		assertTrue(strUtil.noPunctuation.apply("!1@2#3$4%5^6&7*8(9)0-1 ").equals("12345678901")); 
		assertTrue(strUtil.noPunctuation.apply(mixedString).equals("ABCDEFG123456HIJKL")); 
	}
	@Test
	public void testRemoveDigits()
	{
		assertTrue(strUtil.wordsOnly.apply(mixedString).equals(wordString)); 
	}
	@Test
	public void testRemoveLetters()
	{
		assertTrue(strUtil.numberOnly.apply(mixedString).equals(numberString)); 
	}		
}
