package com.nhuszka.concurrency;

import java.util.ArrayList;
import java.util.List;

import com.nhuszka.concurrency.thread.AmountIncrementer;
import com.nhuszka.concurrency.thread.amount.Amount;
import com.nhuszka.concurrency.thread.amount.ThreadSafeAmount;

public class ThreadTester {

	public static void main(String[] args) {
		// new ThreadExtended().run();
		//
		// new Thread(new ThreadRunnable()).run();
		//
		// new SleepMessages().run();

		Amount amount = new ThreadSafeAmount();

		// TODO does not work well
		testAmountIncrement(amount);
	}

	private static void testAmountIncrement(Amount amount) {
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; ++i) {
			threads.add(new Thread(new AmountIncrementer(amount)));
		}
		for (Thread thread : threads) {
			thread.start();
		}
	}
}
