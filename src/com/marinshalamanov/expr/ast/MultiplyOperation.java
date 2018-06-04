package com.marinshalamanov.expr.ast;

import java.math.BigInteger;

public class MultiplyOperation extends Operation {
	public MultiplyOperation(ASTNode arg1, ASTNode arg2) {
		super(arg1, arg2);
	}

	@Override
	public BigInteger calcImpl() {
		if(result == null) {
			result = arg1.calc().multiply(arg2.calc());
		} 
		return result;
	}
	
	public String toSting() {
		return "*";
	}
}


