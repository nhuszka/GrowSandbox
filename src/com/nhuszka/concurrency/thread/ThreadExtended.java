package com.nhuszka.concurrency.thread;

public class ThreadExtended extends Thread {

	@Override
	public void run() {
		System.out.println("Extended thread");
	}
}
