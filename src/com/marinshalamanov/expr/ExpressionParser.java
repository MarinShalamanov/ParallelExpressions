package com.marinshalamanov.expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.marinshalamanov.expr.ast.AST;
import com.marinshalamanov.expr.ast.ASTNode;
import com.marinshalamanov.expr.ast.DivideOperation;
import com.marinshalamanov.expr.ast.MultiplyOperation;
import com.marinshalamanov.expr.ast.SubtractOperation;
import com.marinshalamanov.expr.ast.SumOperation;
import com.marinshalamanov.expr.ast.Value;

public class ExpressionParser {
	
	public List<StringPiece> tokenize(String str) {
		return StringUtil.splitOnWhitespace(str);
	}
	
	public List<StringPiece> toRPN(List<StringPiece> tokens) {
		List<StringPiece> result = new ArrayList<>();
		
		Stack<StringPiece> st = new Stack<>();
		
		for(StringPiece token : tokens) {
			if(isOperator(token)) {
				while (!st.isEmpty() 
						&& getPriority(token) < getPriority(st.peek())) {
					result.add(st.pop());
				}
				st.push(token);
			} else if (isNumber(token)){
				result.add(token);
			} else if (token.equals(OPEN_BRACKET)) {
				st.push(token);
			} else if (token.equals(CLOSED_BRACKET)) {
				while (!st.peek().equals(OPEN_BRACKET)) {
					result.add(st.pop());
				}
				st.pop();
			} else {
				throw new IllegalStateException("Unkown token " + token);
			}
		}
		while (!st.isEmpty()) result.add(st.pop());
		
		return result;
	}
	
	
	public AST buildOperationsTree(List<StringPiece> rpn) {
		Stack<ASTNode> st = new Stack<>();
		for (StringPiece token : rpn) {
			if(isNumber(token)) {
				st.push(new Value(token));
			} else { 
				// token is operation
				ASTNode arg2 = st.pop();
				ASTNode arg1 = st.pop();
				ASTNode curr;
				
				if(token.equals(MINUS)) 
					curr = new SubtractOperation(arg1, arg2);
				else if(token.equals(PLUS)) 
					curr = new SumOperation(arg1, arg2);
				else if(token.equals(MULTIPLICATION)) 
					curr = new MultiplyOperation(arg1, arg2);
				else if(token.equals(DIVISION)) 
					curr = new DivideOperation(arg1, arg2);
				else 
					throw new IllegalStateException("Unkown operator " + token);
				
				st.push(curr);
			}
		}
		
		ASTNode root = st.pop();
		AST ast = new AST(root);
		return ast;
	}
	
	public AST parse(String infix) {
		long startTime = System.currentTimeMillis();
		List<StringPiece> tokens = tokenize(infix);
		long currTime = System.currentTimeMillis();
		System.out.println("Tokenization done for " + (currTime - startTime)/1000);
		
		
		startTime = System.currentTimeMillis();
		tokens = toRPN(tokens);
		currTime = System.currentTimeMillis();
		System.out.println("To RPN for " + (currTime - startTime)/1000);
		
		startTime = System.currentTimeMillis();
		AST ast = buildOperationsTree(tokens);
		currTime = System.currentTimeMillis();
		System.out.println("AST built for " + (currTime - startTime)/1000);
		
		return ast;
	}
	
	private final static String MINUS = "-";
	private final static String PLUS = "+";
	private final static String MULTIPLICATION = "*";
	private final static String DIVISION = "/";
	private final static String OPEN_BRACKET = "(";
	private final static String CLOSED_BRACKET = ")";
	
	private final static String[] OPERATORS = {
			MINUS, PLUS, MULTIPLICATION, DIVISION};
	
	private int getPriority(StringPiece operator) {
		if(operator.equals(MINUS)) return 2;
		else if(operator.equals(PLUS)) return 1;
		else if(operator.equals(MULTIPLICATION)) return 3;
		else if(operator.equals(DIVISION)) return 4;
		else if(operator.equals(OPEN_BRACKET)) return 0;
		else return 0;
	}
	
	private boolean isOperator(StringPiece token) {
		return Arrays
				.stream(OPERATORS)
				.anyMatch(op -> token.equals(op));
	}
	
	private boolean isNumber(StringPiece token) {
		return token.length() > 0 
				&& Character.isDigit(token.charAt(0));
	}

	
	
	public static void main(String[] args) {
		
	}
}
