package com.nhuszka.concurrency.javaworld_tutorial.article2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// http://www.javaworld.com/article/2078848/java-concurrency/java-concurrency-java-101-the-next-generation-java-concurrency-without-the-pain-part-2.html
public class ReentrantLockCriticalSections {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		final ReentrantLock rl = new ReentrantLock();

		class Worker implements Runnable {
			private String name;

			Worker(String name) {
				this.name = name;
			}

			@Override
			public void run() {
				System.out.printf("Thread %s wants to enter Crit Sect%n", name);
				rl.lock();
				try {
					if (rl.isHeldByCurrentThread()) {
						System.out.printf("Thread %s has entered its critical section.%n", name);
					}
					System.out.printf("Thread %s is performing work for 2 seconds.%n", name);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
					System.out.printf("Thread %s has finished working.%n", name);
				} finally {
					rl.unlock();
				}
			}
		}

		executor.execute(new Worker("A"));
		executor.execute(new Worker("B"));

		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		executor.shutdownNow();
	}
}