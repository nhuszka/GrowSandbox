package com.nhuszka.concurrency.thread.amount;

public class ThreadSafeAmount extends Amount {

	@Override
	public synchronized void incrementAmount() {
		amount++;
		amountHistory.append(amount).append("\n");
	}
}
