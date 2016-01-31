package com.nhuszka.concurrency.oracle_tutorial.producer_consumer;

import java.util.Random;

public class Producer implements Runnable {
	private Drop drop;

	public Producer(Drop drop) {
		this.drop = drop;
	}

	@Override
	public void run() {
		String importantInfo[] = {
				"Mares eat oats",
				"Does eat oats",
				"Little lambs eat ivy",
				"A kid will eat ivy too"
		};
		Random random = new Random();

		for (String element : importantInfo) {
			drop.put(element);
			try {
				Thread.sleep(random.nextInt(500));
			} catch (InterruptedException e) {
			}
		}
		drop.put("DONE");
	}
}