package com.RESTurantManager.demo.security;

public class JoinCodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;

    public static String generateJoinCode() {
        StringBuilder joinCode = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = (int) (Math.random() * CHARACTERS.length());
            joinCode.append(CHARACTERS.charAt(index));
        }
        return joinCode.toString();
    }
}
