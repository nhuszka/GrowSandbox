package com.nhuszka.stream.operation;

import java.util.List;
import java.util.function.BinaryOperator;

import com.nhuszka.stream.bean.Rating;

public class Reduce {

	public Double computeAverage(List<Rating> ratings) {
		// lambda can be used
		// (a, b) -> a + b
		BinaryOperator<Integer> adder = new BinaryOperator<Integer>() {

			@Override
			public Integer apply(Integer a, Integer b) {
				return a + b;
			}
		};

		double size = ratings.size();
		return ratings
				.stream()
				.map(rating -> rating.getGrade())
				.reduce(adder)
				.get() / size;

		// in this case we don't get an Optional:
		// .reduce(0, adder) / size;
	}
}