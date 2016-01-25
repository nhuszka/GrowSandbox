package com.nhuszka.concurrency.thread;

import com.nhuszka.concurrency.thread.amount.Amount;

public class AmountIncrementer implements Runnable {

	private Amount amountToIncrement;

	public AmountIncrementer(Amount amountToIncrement) {
		this.amountToIncrement = amountToIncrement;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Before " + amountToIncrement.getAmount());
			amountToIncrement.incrementAmount();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("After " + amountToIncrement.getAmount());
		}
	}
}
