package com.marinshalamanov.expr.test;

import java.util.Arrays;
import java.util.Random;

import com.marinshalamanov.expr.Calculator;
import com.marinshalamanov.expr.TestGenerator;

public class PerformanceTests {
	
	
	void testBigNumbersMultiplication() {
		TestGenerator testGenerator = new TestGenerator();

		int numLen = 100_000;
		char numCh[] = new char[numLen];
		Arrays.fill(numCh, '1');
		String number = new String(numCh);
		
		String expr = testGenerator.generateBalancedExpr(number, 2, 7, '+', '*');
		System.out.println("Expr length: " + expr.length()/1000000.0  + "MB");
		
		Calculator.calculate(expr, 1);
	}
	
	
	
	
	void run() {
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("Available processors: " + processors);
		System.out.println("===========================");
		testBigNumbersMultiplication();
	}
	
	public static void main(String[] args) {	
		new PerformanceTests().run();
	}
}
