package com.angelina.hangman;
import java.io.IOException;
import java.util.Scanner;
import static com.angelina.hangman.Main.clearConsole;

public class Hangman {
    private final Scanner scanner;
    public Hangman(Scanner scanner) {
        this.scanner = scanner;
    }
    public void runGame() throws IOException, InterruptedException {
        System.out.println("xxx ¡Bienvenido al Juego del Ahorcado ╰(*°▽°*)╯ ! xxx");
        String word = readWord().toUpperCase();
        clearConsole();
        StringBuilder hiddenWord = createHiddenWord(word);
        characterValidation(word, hiddenWord);
    }

    String readWord() {
        return StringUtils.readSingleWord(scanner,
                "XXX Ingresa la palabra que quieres que el otro jugador adivine XXX",
                "--- Eso no parece una palabra 😒 ---");

    }

    void staticGamePart(String word, StringBuilder hiddenWord) {
        System.out.println("\nXXX JUEGO EMPEZADO (*°▽°*) XXX");
        System.out.println("La palabra tiene " + word.length() + " letras.");
        System.out.println("Palabra: " + hiddenWord);
    }

    StringBuilder createHiddenWord(String word) {
        return new StringBuilder("_".repeat(word.length()));
    }

    char readGuessedLetter() {
        return StringUtils.readPossibleChar(scanner,
                "\nXXX Ingresa la letra que crees que sea XXX",
                "--- Eso no parece una letra >:( ---");
    }

    void characterValidation(String word, StringBuilder hiddenWord) throws IOException, InterruptedException {
        int attempts = 7;

        while (attempts > 0) {
            staticGamePart(word, hiddenWord);
            showGameState(attempts);
            char character = readGuessedLetter();

            boolean match = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == character) {
                    hiddenWord.setCharAt(i, character);
                    match = true;
                }
            }
            clearConsole();

            if (match) {
                System.out.println("¡MUY BIEN! SIGUE ASÍ :)");
            } else {
                System.out.println("XXX INCORRECTO :( SUERTE EN LA SIGUIENTE XXX");
                attempts--;
            }

            if (!hiddenWord.toString().contains("_")) {
                System.out.println("¡FELICIDADES! HAS GANADO :)");
                return;
            }
        }

        showGameState(attempts);
        System.out.println("¡OH NO! HAS PERDIDO. （︶^︶） LA PALABRA ERA: " + word);
    }


    void showGameState(int attempts) {
        System.out.println("\nXXX Te quedan " + attempts + " intentos XXX");
        showFrame(attempts);
    }

    void showFrame(int attempts) {
        String[] frames = HangmanFrames.getFrames();
        int hangmanFrame = 6 - attempts;
        if (hangmanFrame >= 0 && hangmanFrame < frames.length) {
            System.out.println(frames[hangmanFrame]);
        }
    }
}
