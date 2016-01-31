package com.nhuszka.concurrency.oracle_tutorial.producer_consumer;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html
public class ProducerConsumer {

	public void start() {
		Drop drop = new Drop();
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}
}