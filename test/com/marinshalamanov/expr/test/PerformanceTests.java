package com.marinshalamanov.expr.test;

import java.util.Arrays;
import java.util.Random;

import com.marinshalamanov.expr.Calculator;

public class PerformanceTests {
	Random rand = new Random();
	char ops[] = {'+', '-', '*'};
	
	String generateBalancedExpr(String num, int depth, char op) {
		if(depth == 1) return num;
		else {
			String s = generateBalancedExpr(num, depth-1, op);
			return "( " + s + " "+ op + " "  + s + " )";
		}
	}
	
	String generateBalancedExpr(String num, 
			int topDepth, int bottomDepth, 
			char topOp, char bottomOp) {
		
		if(bottomDepth == 1) return num;
		else if(topDepth > 0){
			String s = generateBalancedExpr(num, 
					topDepth-1, bottomDepth, 
					topOp, bottomOp);
			return "( " + s + " "+ topOp + " "  + s + " )";
		} else {
			String s = generateBalancedExpr(num, 
					0, bottomDepth-1, 
					topOp, bottomOp);
			return "( " + s + " "+ bottomOp + " "  + s + " )";
		}
	}
	
	
	String generateRand(int depth) {
		if(depth == 1) {
			return Long.toString(Math.abs(rand.nextLong()));
		}
		else {
			String s = generateRand(depth-1);
			char op = ops[rand.nextInt(ops.length)];
			return "( " + s + " "+ op + " "  + s + " )";
		}
	}
	
	void testBigNumbersMultiplication() {

		int numLen = 100000;
		char numCh[] = new char[numLen];
		Arrays.fill(numCh, '1');
		String number = new String(numCh);
		
		String expr = generateBalancedExpr(number, 2, 7, '+', '*');
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
