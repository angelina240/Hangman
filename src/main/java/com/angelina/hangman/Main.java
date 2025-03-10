package com.angelina.hangman;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String choice;
        String message = null;

        while (true) {
            clearConsole();
            showMenu(message);
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    clearConsole();
                    new Hangman(scanner).runGame();
                    clearConsole();
                    break;
                case "0":
                    System.out.println("¡Gracias por jugar! :)");
                    return;
                default:
                    message = "Opción no válida";
                    break;
            }
        }
    }

    static void showMenu(String message) {
        System.out.println("*****************************");
        System.out.println("XXX JUEGO DEL AHORCADO U_U XXX");
        System.out.println("""
                 1) Jugar
                 0) Salir
                 Elige una opción
                *******************************""");
        if (message != null){
            System.out.println(message);
        }

    }

    static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

}