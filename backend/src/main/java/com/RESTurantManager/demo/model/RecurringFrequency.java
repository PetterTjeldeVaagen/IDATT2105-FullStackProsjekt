package com.RESTurantManager.demo.model;

/**
 * Enum representing the frequency of recurring tasks or events. Each enum constant has an associated number of days that represents the interval for the frequency.
 */
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

    /**
     * Constructor for RecurringFrequency enum.
     * @param days the number of days associated with the frequency
     */
    RecurringFrequency(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
