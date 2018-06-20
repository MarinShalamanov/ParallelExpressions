package com.marinshalamanov.expr;

public class StringPiece {
	// the string piece is from[begin], from[begin+1]... from[end-1]
	
	private String from;
	private int begin, end;
	
	
	public StringPiece(String from, int begin, int end) {
		this.from = from;
		this.begin = begin;
		this.end = end;
	}
	
	public int length() {
		return end - begin;
	}
	
	public char charAt(int i) {
		return from.charAt(begin+i);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			String s = (String)obj;
			if(s.length() != this.length()) 
				return false;
			
			for(int i = 0; i < this.length(); i++) 
				if(this.charAt(i) != s.charAt(i))
					return false;
			
			return true;
		} else if(obj instanceof StringPiece) {
			StringPiece s = (StringPiece)obj;
			
			if (this.from == s.from) {
				if (this.begin == s.begin && this.end == s.end) 
					return true;	
			}
			
			if(s.length() != this.length()) 
				return false;
			
			for(int i = 0; i < this.length(); i++) 
				if(this.charAt(i) != s.charAt(i))
					return false;
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return from.substring(begin, end);
	}
}
