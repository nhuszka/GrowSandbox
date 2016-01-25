package com.nhuszka.concurrency.thread.amount;

public class ThreadSafeAmount extends Amount {

	@Override
	public synchronized void incrementAmount() {
		amount++;
		System.out.println(amount);
	}
}
