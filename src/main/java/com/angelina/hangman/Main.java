package com.angelina.hangman;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String choice;

        clearConsole();

        while (true) {
            showMenu();
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    clearConsole();
                    new Hangman(scanner).runGame();
                    break;
                case "0":
                    System.out.println("¡Gracias por jugar! :)");
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

            System.out.println("Presiona ENTER para continuar...");
            scanner.nextLine();

            clearConsole();
        }
    }

    static void showMenu() {
        System.out.println("XXX JUEGO DEL AHORCADO 🎮🕹️☠️ XXX");
        System.out.println("--- Ingrese \"1\" para jugar --- \n" +
                "--- Ingrese \"0\" para salir ---");
    }

    static void clearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
