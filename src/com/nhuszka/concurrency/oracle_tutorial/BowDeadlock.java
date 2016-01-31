package com.nhuszka.concurrency.oracle_tutorial;

// https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html
public class BowDeadlock {

	static class Friend {
		private final String name;
		public Friend(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public synchronized void bow(Friend bower) {
			System.out.format("%s: %s"
					+ "  has bowed to me!%n",
					name, bower.getName());
			bower.bowBack(this);
		}
		public synchronized void bowBack(Friend bower) {
			System.out.format("%s: %s"
					+ " has bowed back to me!%n",
					name, bower.getName());
		}
	}

	public void start() {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		new Thread(new Runnable() {
			@Override
			public void run() {
				alphonse.bow(gaston);
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				gaston.bow(alphonse);
			}
		}).start();
	}
}