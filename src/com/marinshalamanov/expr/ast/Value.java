package com.marinshalamanov.expr.ast;

import java.math.BigInteger;

import com.marinshalamanov.expr.StringPiece;

public class Value extends ASTNode {
	private BigInteger value;
	private StringPiece string;
	
	public Value(BigInteger value) {
		this.value = value;
	}
	
	public Value(Integer value) {
		this.value = BigInteger.valueOf(value);
	}
	
	public Value(StringPiece value) {
		this.string = value;
	}
	
	public BigInteger calc() {
		if(value == null) {
			this.value = new BigInteger(string.toString());
		}
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
