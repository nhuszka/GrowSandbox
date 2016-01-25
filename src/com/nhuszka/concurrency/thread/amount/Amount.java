package com.nhuszka.concurrency.thread.amount;

public abstract class Amount {

	protected int amount = 0;
	protected StringBuilder amountHistory = new StringBuilder();

	public String getAmountHistory() {
		return amountHistory.toString();
	}

	public abstract void incrementAmount();
}
