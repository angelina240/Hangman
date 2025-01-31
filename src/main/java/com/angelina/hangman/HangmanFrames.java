package com.angelina.hangman;

public class HangmanFrames {
    public static String[] getFrames() {
        return new String[]{
                // 1 error
                """
                 +---+
                 |   |
                     |
                     |
                     |
                     |
                =========""",

                // 2 errores
                """
                 +---+
                 |   |
                 O   |
                     |
                     |
                =========""",

                //3 errores

                """
                 +---+
                 |   |
                 O   |
                 |   |
                     |
                =========""",

                // 4 errores
                """
                 +---+
                 |   |
                 O   |
                /|   |
                     |
                =========""",

                // 5 errores
                """
                 +---+
                 |   |
                 O   |
                /|\\  |
                     |
                =========""",

               //  x6 error(es)\n\n"
                """
                 +---+
                 |   |
                 O   |
                /|\\  |
                /    |
                =========""",

                // x7 error(es)\n\n"
                """
                 +---+
                 |   |
                 O   |
                /|\\  |
                / \\  |
                ========="""
        };

    }

}
