package com.nhuszka.java8;

import com.nhuszka.java8.pratice.Practice;
import com.nhuszka.java8.pratice.PracticeProvider;

public class Showcase {

    public static void main(String[] args) {
        new PracticeProvider()
                .listPractices()
                .forEach(Practice::run);
    }
}
