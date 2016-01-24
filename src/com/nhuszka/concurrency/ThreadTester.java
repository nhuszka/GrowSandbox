package com.nhuszka.concurrency;

import com.nhuszka.concurrency.thread.SleepMessages;
import com.nhuszka.concurrency.thread.ThreadExtended;
import com.nhuszka.concurrency.thread.ThreadRunnable;

public class ThreadTester {

	public static void main(String[] args) {
		new ThreadExtended().run();

		new Thread(new ThreadRunnable()).run();

		new SleepMessages().run();
	}
}
