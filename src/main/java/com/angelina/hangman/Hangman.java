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
                +++ ¡Bienvenido al Juego del Ahorcado ╰(*°▽°*)╯ ! +++
                """);
        String word = readWord();
        clearConsole();
        StringBuilder hiddenWord = createHiddenWord(word);
        return runGameLoop(word, hiddenWord);
    }

    String readWord() {
        return StringUtils.readSingleWord(scanner,
                ">>> Ingresa la palabra que quieres que el otro jugador adivine <<<" +
                        "\n **************************************+*********************** ",
                "--- Eso no parece una palabra 😒 ---").toUpperCase();
    }

    StringBuilder createHiddenWord(String word) {
        return new StringBuilder("_".repeat(word.length()));
    }

    String runGameLoop(String word, StringBuilder hiddenWord) throws IOException, InterruptedException {
        int attempts = 0;
        String usedLetters = "";
        String feedbackMessage = null;

        while (attempts < MAX_ATTEMPTS) {
            showGameProgress(word, hiddenWord);
            showGameState(attempts);

            clearConsole();
            if (feedbackMessage != null)
                System.out.println(feedbackMessage);

            char character = Character.toUpperCase(readGuessedLetter());

            feedbackMessage = warnIfLetterAlreadyEntered(character, usedLetters);
            if (showFeedbackMessageIfExist(feedbackMessage)) {
                continue;
            }

            boolean match = updateHiddenWord(word, hiddenWord, character);
            usedLetters += character;
            feedbackMessage = getFeedbackAttempt(match);
            if (!match)
                attempts++;
            if (!hasHiddenLetters(hiddenWord))
                return "¡FELICIDADES! HAS GANADO :)";
        }

        return "¡OH NO! HAS PERDIDO. （︶^︶） LA PALABRA ERA: " + word;
    }

    void showGameProgress(String word, StringBuilder hiddenWord) {
        System.out.println("-------------------------------------------------");
        System.out.println("******** JUEGO DEL AHORCADO (*°▽°*) ***********");
        System.out.println("La palabra tiene " + word.length() + " letras.");
        System.out.println("Palabra: " + hiddenWord);
    }

    void showGameState(int attempts) {
        System.out.println("\n!Te quedan " + (MAX_ATTEMPTS - attempts) + " intentos!");
        if (attempts > 0)
            showFrame(attempts);
    }

    char readGuessedLetter() {
        return StringUtils.readPossibleChar(scanner,
                """
                        
                        >>> Ingresa la letra que crees que sea <<<\
                        
                         -------------------------------------------------""",
                "xxx Eso no parece una letra >:( xxx");
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

    private boolean showFeedbackMessageIfExist(String feedbackMessage) {
        return feedbackMessage != null;
    }

    String warnIfLetterAlreadyEntered(char character, String usedLetters) {
        if (usedLetters.contains(String.valueOf(character)))
            return "XXX ¡Ya ingresaste esa letra! Intenta con otra. XXX";
        return null;
    }

    String getFeedbackAttempt(boolean match) {
        return match
                ? "\n¡MUY BIEN! SIGUE ASÍ :)"
                : "XXX INCORRECTO :( SUERTE EN LA SIGUIENTE XXX";
    }

    boolean hasHiddenLetters(StringBuilder hiddenWord) {
        return hiddenWord.toString().contains("_");
    }

    void showFrame(int attempts) {
        System.out.println(HangmanFrames.Frames[attempts - 1]);
    }
}
