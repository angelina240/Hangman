package com.angelina.hangman;

import java.io.IOException;
import java.util.Scanner;

import static com.angelina.hangman.Main.clearConsole;

public class Hangman {
    private static final int MAX_ATTEMPTS = 7;
    private final Scanner scanner;

    public Hangman(Scanner scanner) {
        this.scanner = scanner;
    }

    public String runGame() throws IOException, InterruptedException {
        System.out.println("""
            **************************************+***********************
            xxx ¡Bienvenido al Juego del Ahorcado ╰(*°▽°*)╯ ! xxx
            """);
        String word = readWord();
        clearConsole();
        StringBuilder hiddenWord = createHiddenWord(word);
        return runGameLoop(word, hiddenWord);
    }

    String readWord() {
        return StringUtils.readSingleWord(scanner,
                "XXX Ingresa la palabra que quieres que el otro jugador adivine XXX" +
                        "\n **************************************+*********************** ",
                "--- Eso no parece una palabra 😒 ---").toUpperCase();
    }

    StringBuilder createHiddenWord(String word) {
        return new StringBuilder("_".repeat(word.length()));
    }


    void showGameProgress(String word, StringBuilder hiddenWord) {
        System.out.println("-------------------------------------------------");
        System.out.println("******** JUEGO DEL AHORCADO (*°▽°*) ***********");
        System.out.println("La palabra tiene " + word.length() + " letras.");
        System.out.println("Palabra: " + hiddenWord);
    }

    void showGameState(int attempts) {
        System.out.println("\nXXX Te quedan " + (MAX_ATTEMPTS - attempts) + " intentos XXX");
        showFrame(attempts);
    }

    char readGuessedLetter() {
        return StringUtils.readPossibleChar(scanner,
                """
                        
                        XXX Ingresa la letra que crees que sea XXX\
                        
                         -------------------------------------------------""",
                "--- Eso no parece una letra >:( ---");
    }

    boolean updateHiddenWord(String word, StringBuilder hiddenWord, char character) {
        boolean match = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == character) {
                hiddenWord.setCharAt(i, character);
                match = true;
            }
        }
        return match;
    }

    String runGameLoop(String word, StringBuilder hiddenWord) throws IOException, InterruptedException {
        int attempts = 0;
        String usedLetters = "";
        String feedbackMessage = "";

        while (attempts < MAX_ATTEMPTS) {
            showGameProgress(word, hiddenWord);
            showGameState(attempts);

            clearConsole();
            if (!feedbackMessage.isEmpty()) {
                System.out.println(feedbackMessage);
            }

            char character = Character.toUpperCase(readGuessedLetter());
            boolean match = updateHiddenWord(word, hiddenWord, character);

            feedbackMessage = warnIfLetterAlreadyEntered(character, usedLetters);
            if (!feedbackMessage.isEmpty()) {
                continue;
            }
            usedLetters += character;


            feedbackMessage = updateGameStatus(match, attempts);
            if (!match) {
                attempts++;
            }

            if (!hasHiddenLetters(hiddenWord)) {
                return "¡FELICIDADES! HAS GANADO :)";
            }
        }

        return "¡OH NO! HAS PERDIDO. （︶^︶） LA PALABRA ERA: " + word;
    }

    String warnIfLetterAlreadyEntered(char character, String usedLetters) {
        if (usedLetters.indexOf(character) != -1) {
            return "XXX ¡Ya ingresaste esa letra! Intenta con otra. XXX";
        }
        return "";
    }

    String updateGameStatus(boolean match, int attempts) {
        if (match) {
            return "\n¡MUY BIEN! SIGUE ASÍ :)";
        } else {
            attempts++;
            return "XXX INCORRECTO :( SUERTE EN LA SIGUIENTE XXX";
        }
    }

    boolean hasHiddenLetters(StringBuilder hiddenWord) {
        return hiddenWord.toString().contains("_");
    }

    void showFrame(int attempts) {
        if (attempts > 0) {
            System.out.println(HangmanFrames.Frames[attempts - 1]);
        }
    }
}
