package com.nhuszka.stream;

import java.util.List;

import com.nhuszka.stream.bean.Rating;
import com.nhuszka.stream.operation.Filter;
import com.nhuszka.stream.operation.Foreach;
import com.nhuszka.stream.operation.Map;
import com.nhuszka.stream.operation.Reduce;
import com.nhuszka.stream.util.RatingCreator;

public class StreamOperations {

	public void start() {
		List<Rating> ratings = RatingCreator.createRandomRatings();

		print("Ratings:");
		new Foreach().printEach(ratings);
		print("Excellent " + new Filter().filterExcellentRatings(ratings));
		print("Excellent names " + new Map().getNamesWhoAreExcellent(ratings));
		print("Average " + new Reduce().computeAverage(ratings));
	}

	private void print(String string) {
		System.out.println(string);
	}
}
