package com.marinshalamanov.expr.ast;

import java.math.BigInteger;

public abstract class Operation extends ASTNode {
	protected ASTNode arg1, arg2;

	protected BigInteger result;
	
	public Operation(ASTNode arg1, ASTNode arg2) {
		super();
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	public ASTNode getArg1() {
		return arg1;
	}

	public ASTNode getArg2() {
		return arg2;
	}
	
	@Override
	public synchronized BigInteger calc() {
		return calcImpl();
	}
	
	protected abstract BigInteger calcImpl();
}

	
