package com.marinshalamanov.expr;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	
	public static List<StringPiece> splitOnWhitespace(String string) {
		List<StringPiece> res = new ArrayList<>();
		int begin = 0, end = 0;
		boolean readingWord = false;
		for(int i = 0; i < string.length(); i++) {
			if(Character.isWhitespace(string.charAt(i))) {
				if (readingWord) {
					end = i;
					res.add(new StringPiece(string, begin, end));
					readingWord = false;
				} else {
					begin = i+1;
				}
			} else {
				if (readingWord) {
					end = i+1;
				} else {
					begin = i;
					readingWord = true;
				}
			}
		}
		
		if (readingWord && begin != string.length()) {
			end = string.length();
			res.add(new StringPiece(string, begin, end));
		}
		
		return res;
	}
	
	public static String join(List<StringPiece> list, String delim) {
		String s = "";
		
		for(int i = 0; i < list.size(); i++) {
			if(i > 0) s+=delim;
			s += list.get(i).toString();
		}
		
		return s;
	}
}
