package com.nhuszka.stream.operation;

import java.util.List;
import java.util.function.Consumer;

import com.nhuszka.stream.bean.Rating;

public class Foreach {

	public void printEach(List<Rating> ratings) {
		// lambda can be used
		// item -> System.out.println(item)
		Consumer<Rating> printer = new Consumer<Rating>() {

			@Override
			public void accept(Rating item) {
				System.out.print(item + " ");
			}

		};

		ratings.stream().forEach(printer);
		System.out.println();
	}
}
