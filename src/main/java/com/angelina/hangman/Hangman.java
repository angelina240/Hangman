package com.angelina.hangman;
import java.io.IOException;
import java.util.Scanner;

import static com.angelina.hangman.Main.clearConsole;

public class Hangman {
    private Scanner scanner;

    public Hangman(Scanner scanner) {
        this.scanner = scanner;
    }

    public void runGame() throws IOException, InterruptedException {
        System.out.println("xxx ¡Bienvenido al Juego del Ahorcado ╰(*°▽°*)╯ ! xxx");
        String word = readWord();
        clearConsole();
        game(word);
        char attempt = attempts();
    }

    String readWord() {
       return StringUtils.readSingleWord(scanner,
                "--- Ingresa la palabra que quieres que el otro jugador adivine ---",
                "--- Eso no parece una palabra 😒 ---");

    }

    void game(String word) {
        System.out.println("XXX JUEGO EMPEZADO (*°▽°*) XXX\n");
        System.out.println("La palabra tiene " + word.length() + " letras.\n");
        String hiddenWord = "_".repeat(word.length());
        System.out.println(hiddenWord);
    }


    char attempts(){
        return StringUtils.readSingleLetter(scanner,
                "\nXXX Ingresa la letra que crees que sea XXX",
                "--- Eso no parece una letra >:( ---");
    }



}
