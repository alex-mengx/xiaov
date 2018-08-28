package org.b3log.xiaov.util;

import java.util.List;
import java.util.Random;

import com.scienjus.smartqq.model.GroupMessage;

public class SpecReactor implements Reactor {
	private static final double CHANCE = 0.1;

	private static final long INTERVAL = 1000l * 60l * 10l;

	private Random rand;

	private long lastGreeding = -1l;

	public SpecReactor(Random rand) {
		this.rand = rand;
	}

	@Override
	public String process(GroupMessage message) {
		if (UserUtils.quotes.containsKey(message.getUserId()) && RandomUtil.rollingDice(rand, CHANCE)
				&& System.currentTimeMillis() - lastGreeding > INTERVAL) {
			lastGreeding = System.currentTimeMillis();

			List<String> quote = UserUtils.quotes.get(message.getUserId()).get(UserUtils.QUOTES);
			return quote.get(RandomUtil.rangeIn(rand, quote.size()));
		}
		return null;
	}
}
