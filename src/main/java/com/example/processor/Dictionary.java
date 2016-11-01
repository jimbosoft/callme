package com.example.processor;

import java.io.Reader;

public interface Dictionary
{
	public void load(Reader inputSource) throws Exception;
	public boolean contains(String word);
	public int size();
}
