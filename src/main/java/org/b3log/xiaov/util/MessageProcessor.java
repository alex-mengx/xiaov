package org.b3log.xiaov.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.scienjus.smartqq.model.GroupMessage;

public class MessageProcessor {

	private List<Reactor> reactors;

	private Random rand = new Random(System.currentTimeMillis());

	public MessageProcessor() {
		reactors = new ArrayList<>();
		reactors.add(new RepReactor(rand));
		reactors.add(new SpecReactor(rand));
	}

	public String process(final GroupMessage message) {
		return reactors.get(RandomUtil.rangeIn(rand, reactors.size())).process(message);
	}
}
