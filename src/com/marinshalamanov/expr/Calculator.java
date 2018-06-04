package com.marinshalamanov.expr;

import java.math.BigInteger;

import com.marinshalamanov.expr.ast.AST;

public class Calculator {
	public static BigInteger calculate(String expression, int threads) {
		long startTime = System.currentTimeMillis();
		
		ExpressionParser parser = new ExpressionParser();
		AST ast = parser.parse(expression);
		
		
		long preprocessingEnd = System.currentTimeMillis();
		double preprocessingSecs = (preprocessingEnd - startTime)/1000.0;
		System.out.println("Preprocessing done for " + preprocessingSecs + " secs.");
		
		ASTExecutor astExecutor = new ASTExecutor();
		BigInteger result = astExecutor.compute(ast, threads);
		
		
		
		long endTime = System.currentTimeMillis();
		double totalSecs = (endTime - startTime)/1000.0;
		double compSecs = (endTime - preprocessingEnd)/1000.0;
		System.out.println("Computation Time: " + compSecs);
		System.out.println("Total Time: " + totalSecs);
		
		return result;
	}
	
}
