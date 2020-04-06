package com.gabrielflorio.operaplan;

import com.github.javafaker.Faker;

public abstract class FakeData {
    private static final Faker fake = new Faker();

    public static String skillName() {
        return fake.hacker().ingverb();
    }

    public static String taskTypeName() {
        return String.format("%s the %s", fake.hacker().verb(), fake.hacker().noun());
    }

    public static String customerName() {
        return fake.company().name();
    }

    public static String employeeName() {
        return fake.lordOfTheRings().character();
    }
}
