package com.nhuszka.stream.operation;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.nhuszka.stream.bean.Rating;
import com.nhuszka.stream.util.RatingCreator;

public class Map {

	public List<String> getNamesWhoAreExcellent(List<Rating> ratings) {
		// lambda can be used
		// rating -> rating.getName()
		Function<Rating, String> rateToName = new Function<Rating, String>() {

			@Override
			public String apply(Rating rating) {
				return rating.getName();
			}
		};
		
		return ratings
				.stream()
				.filter(rating -> rating.getGrade() == RatingCreator.MAX_GRADE)
				.map(rateToName)
				.collect(Collectors.toList());
	}
}
