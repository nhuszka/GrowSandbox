package com.nhuszka.concurrency.thread.amount;

public class NotThreadSafeAmount extends Amount {

	@Override
	public void incrementAmount() {
		amount++;
		System.out.println(amount);
	}
}
