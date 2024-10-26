package com.example.pagination;

import java.util.Random;

public enum Grade {
    A, B, C, D, E;

    private static final Random randomG = new Random();

    public static Grade randomGrade(){
        Grade[] grades = values();
        return grades[randomG.nextInt(grades.length)];
    }
}
