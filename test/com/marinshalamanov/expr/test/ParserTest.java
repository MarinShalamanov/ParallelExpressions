package com.marinshalamanov.expr.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.expr.ExpressionParser;
import com.marinshalamanov.expr.StringPiece;
import com.marinshalamanov.expr.StringUtil;

class ParserTest {

	void testRPN(String infix, String expectedPostFix) {
		ExpressionParser parser = new ExpressionParser();
		List<StringPiece> tokens = parser.tokenize(infix);
		tokens = parser.toRPN(tokens);
		String rpn = StringUtil.join(tokens, " ");
		assertEquals(expectedPostFix, rpn);
	}
	
	@Test
	void testRPN() {
		testRPN("1 + 2 * 3", "1 2 3 * +");
		testRPN("1 * 2 + 3", "1 2 * 3 +");
		testRPN("1 * ( 2 + 3 )", "1 2 3 + *");
		testRPN("( 1 * 2 ) + 3", "1 2 * 3 +");
		testRPN("1 - 2 + 3", "1 2 - 3 +");
		testRPN("1 / 2 * 3", "1 2 / 3 *");
	}

}
