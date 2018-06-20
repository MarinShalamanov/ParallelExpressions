package com.marinshalamanov.expr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class TestGenerator {
	private Random rand = new Random();
	private final char ops[] = {'+', '-', '*'};
	
	public String generateBalancedExpr(String num, int depth, char op) {
		if(depth == 1) return num;
		else {
			String s = generateBalancedExpr(num, depth-1, op);
			return "( " + s + " "+ op + " "  + s + " )";
		}
	}
	
	public String generateBalancedExpr(String num, 
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
	
	
	public String generateRand(int depth) {
		if(depth == 1) {
			return Long.toString(Math.abs(rand.nextLong()));
		}
		else {
			String s = generateRand(depth-1);
			char op = ops[rand.nextInt(ops.length)];
			return "( " + s + " "+ op + " "  + s + " )";
		}
	}
	
	String testBigNumbersMultiplication() {
		TestGenerator testGenerator = new TestGenerator();
		int numLen = 100000;
		char numCh[] = new char[numLen];
		Arrays.fill(numCh, '1');
		String number = new String(numCh);
		String expr = testGenerator.generateBalancedExpr(number, 2, 7, '+', '*');
		return expr;
	}
	
	String test10000() {
		TestGenerator testGenerator = new TestGenerator();
		int numLen = 10000;
		char numCh[] = new char[numLen];
		Random r = new Random();
		for(int i = 0; i < numLen; i++) {
			numCh[i] = (char) ('0' + r.nextInt(10));
		}
		
		String number = new String(numCh);
		String expr = testGenerator.generateBalancedExpr(number, 3, 10, '+', '*');
		System.out.println("Expr length: " + expr.length()/1_000_000.0  + "MB");
		return expr;
	}
	
	
	String testMillionDigitNumbers() {
		int numLen = 1_000_000;
		char numCh[] = new char[numLen];
		Random r = new Random();
		for(int i = 0; i < numLen; i++) {
			numCh[i] = (char) ('0' + r.nextInt(10));
		}
		
		String number = new String(numCh);
			
		String expr = generateBalancedExpr(number, 4, 2, '+', '+');
		System.out.println("Expr length: " + expr.length()/1_000_000.0  + "MB");
		
		return expr;
	}
	
	
	String testSmallDigitNumbers() {
		String number = "7";
			
		String expr = generateBalancedExpr(number, 0, 23, '+', '*');
		System.out.println("Expr length: " + expr.length()/1_000_000.0  + "MB");
	
		return expr;	
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("test10000.txt"));
		TestGenerator testGenerator = new TestGenerator();
		
		String expr = testGenerator.test10000();
		pw.write(expr);
		pw.close();	
	}
}
