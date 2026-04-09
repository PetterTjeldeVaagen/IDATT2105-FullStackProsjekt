package com.RESTurantManager.demo.security;

/**
 * Utility class for generating unique join codes for restaurants. The join code is a random string of uppercase letters and digits, used for employees to join a restaurant.
 */
public class JoinCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;

    /**
     * Generates a random join code consisting of uppercase letters and digits.
     * @return a randomly generated join code
     */
    public static String generateJoinCode() {
        StringBuilder joinCode = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = (int) (Math.random() * CHARACTERS.length());
            joinCode.append(CHARACTERS.charAt(index));
        }
        return joinCode.toString();
    }
}
