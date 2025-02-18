package com.angelina.hangman;

import java.util.*;

public class StringUtils {

    static char readPossibleChar(Scanner scanner, String label, String errorMessage) {
        while (true) {
            System.out.println(label);
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() == 1 && input.matches("[A-ZÑÁÉÍÓÚ]")) {
                return input.charAt(0);
            }
            System.out.println(errorMessage);
        }
    }


    static String readSingleWord(Scanner scanner, String label, String errorMessage) {
        String disallowedChars = ".,;:-!/&%#\"";

        while (true) {
            System.out.println(label);
            String text = scanner.nextLine().trim().toUpperCase();

            boolean isEmptyOrHasSpaces = text.isEmpty() || text.contains(" ");
            boolean hasInvalidChar = false;

            for (int i = 0; i < text.length(); i++) {
                char character = text.charAt(i);
                if (Character.isDigit(character) || disallowedChars.contains(String.valueOf(character))) {
                    hasInvalidChar = true;
                    break;
                }
            }

            if (!isEmptyOrHasSpaces && !hasInvalidChar) {
                return text;
            }

            System.out.println("xxx " + errorMessage + " xxx");
        }
    }
}
