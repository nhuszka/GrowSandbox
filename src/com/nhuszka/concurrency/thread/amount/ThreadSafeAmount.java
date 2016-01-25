package com.nhuszka.concurrency.thread.amount;

public class ThreadSafeAmount extends Amount {

	public synchronized void incrementAmount() {
		amount++;
	}
}
