package com.nhuszka.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.nhuszka.stream.bean.Rating;
import com.nhuszka.stream.util.RatingCreator;

public class StreamOperations {

	public void start() {
		List<Rating> ratings = RatingCreator.createRandomRatings();

		print("Excellent ratings " + getExcellentRatings(ratings));
	}

	public List<Rating> getExcellentRatings(List<Rating> ratings) {
		// lambda can be used
		// item -> item().getGrade() == 5
		Predicate<Rating> excellentRatings = new Predicate<Rating>() {

			@Override
			public boolean test(Rating item) {
				return item.getGrade() == 5;
			}
			
		};
		return ratings
				.stream()
				.parallel()
				.filter(excellentRatings)
				.collect(Collectors.toList());
	}

	private void print(String string) {
		System.out.println(string);
	}
}
