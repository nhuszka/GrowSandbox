package com.nhuszka.concurrency.thread.amount;

public class NotThreadSafeAmount extends Amount {

	public void incrementAmount() {
		amount++;
	}
}
