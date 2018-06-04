package com.marinshalamanov.expr.ast;

import java.math.BigInteger;

public class DivideOperation  extends Operation {
	
	public DivideOperation(ASTNode arg1, ASTNode arg2) {
		super(arg1, arg2);
	}

	@Override
	public BigInteger calcImpl() {
		if(result == null) {
			result = arg1.calc().divide(arg2.calc());
		} 
		return result;
	}
	
	@Override
	public String toString() {
		return "/";
	}
}

