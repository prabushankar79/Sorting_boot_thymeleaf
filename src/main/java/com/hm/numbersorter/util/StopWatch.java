package com.hm.numbersorter.util;

/**
 * @author prabu 
 * 
 * This class is a Util class just to calculate the elapsed time between operation start and operation end
 */

public class StopWatch {

	private final long startTime;

	public StopWatch(long start) {
		startTime = start;
	}

	public long elapsed() {
		return System.currentTimeMillis() - startTime;
	}

	public static StopWatch start() {
		return new StopWatch(System.currentTimeMillis());
	}
}