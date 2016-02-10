package com.nhuszka.concurrency.javaworld_tutorial;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// http://www.javaworld.com/article/2078809/java-concurrency/java-concurrency-java-101-the-next-generation-java-concurrency-without-the-pain-part-1.html?page=2
public class CyclicBarrierJoinAllPlayersThenStart {

	public static void main(String[] args) {
		Runnable action = new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.printf("Thread %s executing barrier action.%n", name);
			}
		};

		final CyclicBarrier barrier = new CyclicBarrier(3, action);
		Runnable task = new Runnable() {
			@Override
			public void run() {

				String name = Thread.currentThread().getName();
				System.out.printf("%s about to join game...%n", name);
				try {
					barrier.await();
				} catch (BrokenBarrierException bbe) {
					System.out.println("barrier is broken");
					return;
				} catch (InterruptedException ie) {
					System.out.println("thread interrupted");
					return;
				}
				System.out.printf("%s has joined game%n", name);
			}
		};

		ExecutorService[] executors = new ExecutorService[]{
				Executors.newSingleThreadExecutor(),
				Executors.newSingleThreadExecutor(),
				Executors.newSingleThreadExecutor()
		};

		for (ExecutorService executor : executors) {
			executor.execute(task);
			executor.shutdown();
		}
	}
}