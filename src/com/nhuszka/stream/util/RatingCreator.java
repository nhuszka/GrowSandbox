package com.nhuszka.stream.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nhuszka.stream.bean.Rating;

public class RatingCreator {

	public static final Integer MAX_GRADE = 5;
	private static final char[] nameAbbreviations = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static List<Rating> createRandomRatings() {
		List<Rating> ratings = new ArrayList<>();

		for (char nameAbbreviation : nameAbbreviations) {
			ratings.add(createRandomRating(nameAbbreviation));
		}

		return ratings;
	}

	public static Rating createRandomRating() {
		return new Rating.Builder()
				.withName(generateName())
				.withGrade(generateGrade())
				.build();
	}

	private static Rating createRandomRating(char nameAbbreviation) {
		return new Rating.Builder()
				.withName(nameAbbreviation + "")
				.withGrade(generateGrade())
				.build();
	}

	private static String generateName() {
		int randomIndex = getRandomNumber(nameAbbreviations.length);
		return nameAbbreviations[randomIndex] + "";
	}

	private static int getRandomNumber(int a) {
		return new Random().nextInt(a);
	}

	private static Integer generateGrade() {
		return getRandomNumber(MAX_GRADE) + 1;
	}
}
