package com.nhuszka.concurrency;

import java.util.ArrayList;
import java.util.List;

import com.nhuszka.concurrency.oracle_tutorial.SimpleThreads;
import com.nhuszka.concurrency.thread.AmountIncrementer;
import com.nhuszka.concurrency.thread.amount.Amount;
import com.nhuszka.concurrency.thread.amount.NotThreadSafeAmount;
import com.nhuszka.concurrency.thread.amount.ThreadSafeAmount;

public class ThreadTester {

	public static void main(String[] args) {
		// new ThreadExtended().run();
		// new Thread(new ThreadRunnable()).run();
		//
		// new SleepMessages().run();
		SimpleThreads.runExample();

		// testThreadSafeAndNotSafe();
	}

	private static void testThreadSafeAndNotSafe() {
		Amount amountNotSafe = new NotThreadSafeAmount();
		System.out.println("\nNot thread safe");
		testAmountIncrement(amountNotSafe);
		sleep(500);
		System.out.println(amountNotSafe.getAmountHistory());

		sleep(200);

		Amount amountSafe = new ThreadSafeAmount();
		System.out.println("\nThread safe");
		testAmountIncrement(amountSafe);
		sleep(500);
		System.out.println(amountSafe.getAmountHistory());
	}

	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
