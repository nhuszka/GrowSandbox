package com.nhuszka.stream.bean;

public class Rating {

	private String topic;
	private Integer grade;

	private Rating() {
	}

	public String getTopic() {
		return topic;
	}

	public Integer getGrade() {
		return grade;
	}

	public String toString() {
		return topic + ": " + grade;
	}

	public static class Builder {

		private Rating rating = new Rating();

		public Builder() {

		}

		public Builder withTopic(String topic) {
			rating.topic = topic;
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
