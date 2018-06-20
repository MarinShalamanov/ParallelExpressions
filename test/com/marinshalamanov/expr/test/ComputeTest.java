package com.marinshalamanov.expr.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.expr.Calculator;

class ComputeTest {

	void test(String expression, String expectedResult) {
		BigInteger result = Calculator.calculate(expression, 1);
		assertEquals(expectedResult, result.toString());
	}
	
	@Test
	void testSmallNumbers() {
		test("11111 + 22222 * 33333", "740737037");
		test("11111 + 22222 + 33333", "66666");
		test("11111 / 22222 + 33333", "33333");
		test("22222 / 11111 + 33333", "33335");
		test("22222 - 11111 + 33333", "44444");
		test("22222 - ( 11111 + 33333 )", "-22222");
		test("33333 / ( 11111 + 22222 ) - 1", "0");
	}

}
