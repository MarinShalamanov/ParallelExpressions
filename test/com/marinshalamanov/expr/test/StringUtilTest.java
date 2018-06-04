package com.marinshalamanov.expr.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.expr.StringPiece;
import com.marinshalamanov.expr.StringUtil;

class StringUtilTest {

	@Test
	void testSimple() {
		List<StringPiece> split = StringUtil.splitOnWhitespace("aaa bb cc");
		assertEquals(3, split.size());
		assertEquals(split.get(0), "aaa");
		assertEquals(split.get(1), "bb");
		assertEquals(split.get(2), "cc");
	}
	
	@Test
	void testMultipleWhitespace() {
		List<StringPiece> split = StringUtil.splitOnWhitespace("  aaa   bb  cc ");
		assertEquals(3, split.size());
		assertEquals(split.get(0), "aaa");
		assertEquals(split.get(1), "bb");
		assertEquals(split.get(2), "cc");
	}
	
	@Test
	void testSingleWord() {
		List<StringPiece> split = StringUtil.splitOnWhitespace("  aaa   ");
		assertEquals(1, split.size());
		assertEquals(split.get(0), "aaa");
	}
	
	@Test
	void testNoWhiteSpace() {
		List<StringPiece> split = StringUtil.splitOnWhitespace("a");
		assertEquals(1, split.size());
		assertEquals(split.get(0), "a");
	}
	
	@Test
	void testEmpty() {
		List<StringPiece> split = StringUtil.splitOnWhitespace("");
		assertEquals(0, split.size());
	}

}
