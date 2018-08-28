package org.b3log.xiaov.util;

import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.scienjus.smartqq.model.Font;
import com.scienjus.smartqq.model.GroupMessage;

public class MessageProcessorTest {

	private static int TEST_SET_SIZE = 10000;

	private MessageProcessor underTest;

	private Stream<GroupMessage> data;

	@Before
	public void init() {
		data = IntStream.range(0, TEST_SET_SIZE).mapToObj(
				i -> new GroupMessage(0l, i * 1000l, "message", Math.random() > 0.5 ? 0 : 1, Font.DEFAULT_FONT));
		underTest = new MessageProcessor();
	}

	@Test
	public void test() {
		assertTrue("Should have triggered action.",
				0 < data.map(msg -> underTest.process(msg)).filter(msg -> msg != null).count());
	}

}
