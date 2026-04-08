package com.RESTurantManager.demo.model;

public enum RecurringFrequency {
    DAILY(1),
    WEEKLY(7),
    BIWEEKLY(14),
    MONTHLY(30),
    BIMONTHLY(60),
    QUARTERLY(90),
    EVERY_SIX_MONTHS(182),
    YEARLY(365),
    BIANNUALLY(730);

    private final int days;

    RecurringFrequency(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
