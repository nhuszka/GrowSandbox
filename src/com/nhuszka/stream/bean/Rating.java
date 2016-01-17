package com.nhuszka.stream.bean;

public class Rating {

	private String name;
	private Integer grade;

	private Rating() {
	}

	public String getName() {
		return name;
	}

	public Integer getGrade() {
		return grade;
	}

	@Override
	public String toString() {
		return name + grade;
	}

	public static class Builder {

		private Rating rating = new Rating();

		public Builder() {

		}

		public Builder withName(String name) {
			rating.name = name;
			return this;
		}

		public Builder withGrade(Integer grade) {
			rating.grade = grade;
			return this;
		}

		public Rating build() {
			return rating;
		}
	}
}
