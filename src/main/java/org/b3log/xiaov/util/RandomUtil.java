package org.b3log.xiaov.util;

import java.util.Random;

public class RandomUtil {

	protected static int rangeIn(final Random rand, final int excludes) {
		return rand.nextInt(excludes);
	}

	protected static boolean rollingDice(final Random rand, final double chance) {
		return rand.nextDouble() < chance;
	}
}
