package com.marinshalamanov.expr.ast;

import java.math.BigInteger;

public class SumOperation extends Operation {

	public SumOperation(ASTNode arg1, ASTNode arg2) {
		super(arg1, arg2);
	}

	@Override
	public BigInteger calcImpl() {
		if(result == null) {
			result = arg1.calc().add(arg2.calc());
		} 
		return result;
	}
	
	@Override
	public String toString() {
		return "+";
	}
}
