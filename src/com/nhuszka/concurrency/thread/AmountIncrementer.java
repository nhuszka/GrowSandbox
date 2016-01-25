package com.nhuszka.concurrency.thread;

import com.nhuszka.concurrency.thread.amount.Amount;

public class AmountIncrementer implements Runnable {

	private Amount amountToIncrement;
	private int numOfIncrements = 3;

	public AmountIncrementer(Amount amountToIncrement) {
		this.amountToIncrement = amountToIncrement;
	}

	@Override
	public void run() {
		while (numOfIncrements > 0) {
			amountToIncrement.incrementAmount();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			numOfIncrements--;
		}
	}
}
