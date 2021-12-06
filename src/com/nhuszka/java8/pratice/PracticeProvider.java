package com.nhuszka.java8.pratice;

import java.util.Arrays;
import java.util.List;

public class PracticeProvider {

    public List<Practice> listPractices() {
        return Arrays.asList(
          new StreamPractice()
        );
    }
}
