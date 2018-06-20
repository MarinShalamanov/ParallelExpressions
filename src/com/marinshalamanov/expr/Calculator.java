package com.marinshalamanov.expr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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
	
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("Available processors: " + processors);
		System.out.println("===========================");
		
		Options options = new Options();
		// add t option
		options.addOption("f", true, "Input file");
		options.addOption("o", true, "Output file");
		options.addOption("t", true, "Num threads.");
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse( options, args);
		
		String inputFilename = cmd.getOptionValue("f");
		String outputFilename = "out.txt";
		if (cmd.hasOption("o")) {
			outputFilename = cmd.getOptionValue("o"); 
		}
		
		int numThreads = 1;
		if (cmd.hasOption("t")) {
			numThreads = Integer.parseInt(cmd.getOptionValue("t"));
		}
		
		Scanner scanner = new Scanner(new File(inputFilename));
		String inputExpression = scanner.nextLine();
		scanner.close();
		
		BigInteger result = calculate(inputExpression, numThreads);
		
		PrintWriter pw = new PrintWriter(new File(outputFilename));
		pw.println(result.toString());
		pw.close();
		
		System.out.println("Done.");
		System.out.println("The answer is written to " + outputFilename);
	}
	
}
