package com.nhuszka.java8.pratice;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;
import java.util.stream.Collectors;

class StreamPractice implements Practice {

    @Override
    public void run() {
        List<Student> students = Arrays.asList(
                new Student("Ben", 5),
                new Student("Ben", 4),
                new Student("John", 3),
                new Student("George", 2),
                new Student("John", 1),
                new Student("John", 4)
        );
        group(students);
        stats(students);
    }

    private void group(Collection<Student> students) {

        Map<Object, List<Student>> studentsByGrade = new HashMap<>();
        for (Student student : students) {
            int grade = student.getGrade();
            List<Student> studentsHavingGrade = studentsByGrade.getOrDefault(grade, new ArrayList<>());
            studentsHavingGrade.add(student);
            studentsByGrade.put(grade, studentsHavingGrade);
        }
        System.out.println("Old style: \n" + studentsByGrade);


        studentsByGrade = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
        System.out.println("Stream style: \n" + studentsByGrade);
    }

    private void stats(List<Student> students) {
        double average = students.stream().mapToDouble(Student::getGrade).summaryStatistics().getAverage();
        System.out.println("Average grade: " + String.format("%.2f", average));
    }

    private static class Student {

        private final String name;
        private final int grade;

        private Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public int getGrade() {
            return grade;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Student student = (Student) o;

            return new EqualsBuilder()
                    .append(grade, student.grade)
                    .append(name, student.name)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(name)
                    .append(grade)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }
}
