package org.b3log.xiaov.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.scienjus.smartqq.model.GroupMessage;

public class RepReactor implements Reactor {

	private static final double MAX_PROB = .2d;
	private static final long RANGE = 30l * 60l * 1000l;

	private Map<Long, List<GroupMessage>> stats;

	private Random rand;

	public RepReactor(Random rand) {
		stats = new HashMap<>();
		this.rand = rand;
	}

	@Override
	public String process(final GroupMessage message) {
		long userId = message.getUserId();

		return repeatingDice(message, userId);
	}

	private String repeatingDice(final GroupMessage message, long userId) {
		stats.computeIfAbsent(userId, l -> new ArrayList<>());
		stats.get(userId).add(message);

		return rollingDice(userId, message.getTime()) ? message.getContent() : null;
	}

	private boolean rollingDice(final long id, final long currentTime) {
		return rand.nextDouble() < computeProb(id, currentTime);
	}

	private double computeProb(final long id, final long currentTime) {
		removeExpiredMessage(currentTime);
		double raw = (double) stats.get(id).size() / stats.values().stream().mapToInt(List::size).sum();
		return raw * MAX_PROB;
	}

	private void removeExpiredMessage(final long currentTime) {
		stats.values().forEach(l -> l.removeIf(msg -> currentTime - msg.getTime() > RANGE));
	}
}
