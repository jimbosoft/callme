package com.example.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidPhoneNrWordTest
{

	@Test
	public void test()
	{
		ValidPhoneNrWord validWord = new ValidPhoneNrWord();
		assertTrue(!validWord.test(null));
		assertTrue(!validWord.test(""));
		assertTrue(validWord.test("tw3erd3s6fg"));
		assertTrue(!validWord.test("tw3erd34s6fg"));
		assertTrue(!validWord.test("1tw3erd34s6fg2"));
		assertTrue(!validWord.test("00tw3erd34s6fg"));
		assertTrue(!validWord.test("tw3erd34s6fg99"));
	}

}
