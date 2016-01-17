package com.nhuszka.stream.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nhuszka.stream.bean.Rating;

public class RatingCreator {

	private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
	private static final Integer MAX_GRADE = 5;

	public static List<Rating> createRandomRatings() {
		List<Rating> ratings = new ArrayList<>();

		for (char topic : CHARACTERS) {
			ratings.add(createRandomRating(topic));
		}

		return ratings;
	}

	public static Rating createRandomRating() {
		return new Rating.Builder()
				.withTopic(generateTopic())
				.withGrade(generateGrade())
				.build();
	}

	private static Rating createRandomRating(char topic) {
		return new Rating.Builder()
				.withTopic(topic + "")
				.withGrade(generateGrade())
				.build();
	}

	private static String generateTopic() {
		int randomIndex = getRandomNumber(CHARACTERS.length);
		return CHARACTERS[randomIndex] + "";
	}

	private static int getRandomNumber(int a) {
		return new Random().nextInt(a);
	}

	private static Integer generateGrade() {
		return getRandomNumber(MAX_GRADE + 1);
	}
}
