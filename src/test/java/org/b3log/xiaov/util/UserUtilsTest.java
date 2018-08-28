package org.b3log.xiaov.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class UserUtilsTest {

	@Test
	public void test() {
		assertTrue("Name doesn't exist!", UserUtils.quotes.get(1489608406L).containsKey("弦子"));

		List<String> quotes = UserUtils.quotes.get(1489608406L).get("弦子");
		assertEquals("Quote doesn't match!", quotes.get(0), "弦子老师你好");
	}

}
