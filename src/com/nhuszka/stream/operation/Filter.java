package com.nhuszka.stream.operation;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.nhuszka.stream.bean.Rating;
import com.nhuszka.stream.util.RatingCreator;

public class Filter {

	public List<Rating> filterExcellentRatings(List<Rating> ratings) {
		// lambda can be used
		// item -> item().getGrade() == RatingCreator.MAX_GRADE;
		Predicate<Rating> excellentRatingFilter = new Predicate<Rating>() {

			@Override
			public boolean test(Rating item) {
				return item.getGrade() == RatingCreator.MAX_GRADE;
			}

		};

		return ratings
				.stream()
				.parallel()
				.filter(excellentRatingFilter)
				.collect(Collectors.toList());
	}
}
