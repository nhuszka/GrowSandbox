package com.nhuszka.concurrency;

import java.util.ArrayList;
import java.util.List;

import com.nhuszka.concurrency.thread.AmountIncrementer;
import com.nhuszka.concurrency.thread.SleepMessages;
import com.nhuszka.concurrency.thread.ThreadExtended;
import com.nhuszka.concurrency.thread.ThreadRunnable;
import com.nhuszka.concurrency.thread.amount.Amount;
import com.nhuszka.concurrency.thread.amount.NotThreadSafeAmount;
import com.nhuszka.concurrency.thread.amount.ThreadSafeAmount;

public class ThreadTester {

	public static void main(String[] args) {
		new ThreadExtended().run();

		new Thread(new ThreadRunnable()).run();

		new SleepMessages().run();

		testThreadSafeAndNotSafe();
	}

	private static void testThreadSafeAndNotSafe() {
		Amount amount = new NotThreadSafeAmount();
		System.out.println("\nNot thread safe");
		testAmountIncrement(amount);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Amount amountSafe = new ThreadSafeAmount();
		System.out.println("\nThread safe");
		testAmountIncrement(amountSafe);
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
