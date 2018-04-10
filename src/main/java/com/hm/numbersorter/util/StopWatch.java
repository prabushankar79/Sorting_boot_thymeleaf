package com.hm.numbersorter.util;

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