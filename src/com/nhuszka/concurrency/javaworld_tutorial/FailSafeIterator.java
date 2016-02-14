package com.nhuszka.concurrency.javaworld_tutorial;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

// http://www.javaworld.com/article/2078809/java-concurrency/java-concurrency-java-101-the-next-generation-java-concurrency-without-the-pain-part-1.html?page=4
public class FailSafeIterator {

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> premiumPhone = new ConcurrentHashMap<String, String>();
		premiumPhone.put("Apple", "iPhone");
		premiumPhone.put("HTC", "HTC one");
		premiumPhone.put("Samsung", "S5");

		Iterator<String> iterator = premiumPhone.keySet().iterator();

		while (iterator.hasNext()) {
			System.out.println(premiumPhone.get(iterator.next()));
			premiumPhone.put("Sony", "Xperia Z");
		}
		System.out.println("-------");
		iterator = premiumPhone.keySet().iterator();
		while (iterator.hasNext()) {
			System.out.println(premiumPhone.get(iterator.next()));
		}
	}

}