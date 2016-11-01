package com.example.processor;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/***
 * Converts a combination of letters and digits
 * to a valid word equivalent separated by a '-'
 * eg CALL-8-ME
 * 
 * @author hejtmanekj
 *
 */
public class Wordsmith
{
	public Wordsmith()
	{
		super();
	}
	/***
	 * Converts a list of possible words to a list a valid words 
	 * by applying the dictionary
	 * 
	 * @param wordComboList a list of character combinations
	 * @param dictionary where to find possible words
	 * @return
	 */
	public List<String> produceWords(List<String> wordComboList, DictionaryService dictionary)
	{
		List<String> results = new ArrayList<String>();

		if (dictionary == null || dictionary.size() == 0 
				|| wordComboList == null || wordComboList.size() == 0)
		{
			return results;
		}
	    for (String combo : wordComboList)
		{
		    search(combo, dictionary, new Stack<String>(), results);			
		}
	    
	    return results;
	}
	/***
	 * Recursive function that finds all possible word combinations for the "input" word
	 * by lookup in the dictionary
	 * 
	 * @param input - the combination of letters for which to find find the words
	 * @param dictionary - lookup for valid words
	 * @param words - stack used for recursive storage of words
	 * @param results - list of resulting strings in the format of word-number-word
	 */
	public void search(String input, DictionaryService dictionary, Stack<String> words, List<String> results)
	{
		for (int i = 0; i < input.length(); i++)
		{
			boolean digit = Character.isDigit(input.charAt(i));
			
			if (input.substring(0, i).length() > 0 && digit)
			{
				break;
			}
			// take the first i characters of the input and see if it is a word
			String substring = input.substring(0, i + 1);

			if (dictionary.contains(substring) || (i == 0 && digit))
			{
				if ( i == 0 && Character.isDigit(input.charAt(i)))
				{
					words.push(Character.toString(input.charAt(i)));
				}
				else
				{
					// the beginning of the input matches a word, store on stack
					words.push(substring);
				}

				if (i == input.length() - 1)
				{
					StringBuilder outputStr = new StringBuilder();
					for (String s : words)
					{
						outputStr.append(s).append('-');
					}
					if (outputStr.length() > 0)
					{
						outputStr.deleteCharAt(outputStr.length() - 1);
					}
					// there's no input left, copy the words stack to results
					results.add(outputStr.toString());
				} else
				{
					// there's more input left, search the remaining part
					search(input.substring(i + 1), dictionary, words, results);
				}

				// pop the matched word back off so we can move onto the next i
				words.pop();
			}
		}
	}
}
