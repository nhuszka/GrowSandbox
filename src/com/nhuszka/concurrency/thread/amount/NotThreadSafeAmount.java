package com.nhuszka.concurrency.thread.amount;

public class NotThreadSafeAmount extends Amount {

	@Override
	public void incrementAmount() {
		amount++;
		amountHistory.append(amount + "\n");
	}
}
