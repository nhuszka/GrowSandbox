package com.nhuszka.concurrency.thread.amount;

public abstract class Amount {

	protected int amount = 0;

	public int getAmount() {
		return amount;
	}

	public abstract void incrementAmount();
}
