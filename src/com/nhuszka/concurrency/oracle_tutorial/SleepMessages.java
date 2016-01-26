package com.nhuszka.concurrency.oracle_tutorial;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html
public class SleepMessages {
	public void run() {
		String importantInfo[] = {
				"Mares eat oats",
				"Does eat oats",
				"Little lambs eat ivy",
				"A kid will eat ivy too"
		};

		for (String element : importantInfo) {
			// Pause for 0.1 seconds
			sleep(100);
			// Print a message
			System.out.println(element);
		}
	}

	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}