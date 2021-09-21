package main;

import java.util.Scanner;

public class TicTacToe {
    Scanner scann = new Scanner(System.in);
    String player1;
    String player2;
    int turns = 0;
    boolean logicValue = false;

    public static String[][] matrix = { {"1", "2", "3"},
                                        {"4", "5", "6"},
                                        {"7", "8", "9"}};

    public void showTheMatrix() {
        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {

                System.out.print("|");
                System.out.print(matrix[line][column]);

            }
            System.out.print("|");
            System.out.println("\n" + "-------");
        }
    }

    public void choosingThePlayer() {
        String catchTheChoice;
        System.out.println("Player 1, make your choice [ X ] or [ O ] ? ");
        catchTheChoice = scann.nextLine();
        if (catchTheChoice.equalsIgnoreCase("X")) {
            player1 = "X";
            player2 = "O";
        } else if (catchTheChoice.equalsIgnoreCase("O")) {
            player1 = "O";
            player2 = "X";
        } else {
            System.out.println("[ERROR] TRY AGAIN!");
            choosingThePlayer();
        }
    }

    public void start() {
        while (turns != 9) {
            player1Playing();
            player2Playing();
        }
    }

    public void player1Playing() {
        System.out.println("Player 01, Move [" + player1 + "], In to position : ");
        String movement = scann.nextLine();
        boolean logicValue = false;

        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                if (movement.equals(matrix[li][co])) {
                    matrix[li][co] = player1;
                    logicValue = true;
                }
            }
        }
        showTheMatrix();
        mainDiagonal(player1);
        secondaryDiagonal(player1);
        horizontalLine(player1);
        verticalLine(player1);
        moveValidate(logicValue, player1);
        draw();
    }

    public void moveValidate( boolean value, String player ) {
        if (value) {
            System.out.println(player + " [DONE]you movement is finished.");
            turns += 1;
        } else {
            System.out.println("INVALID MOVEMENT!");
            return;
        }
    }

    public void player2Playing() {
        System.out.println("Player 02, Move [" + player2 + "], In to position : ");
        String movement = scann.nextLine();
        boolean logicValue = false;

        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                if (movement.equals(matrix[li][co])) {
                    matrix[li][co] = player2;
                    logicValue = true;
                }
            }
        }
        showTheMatrix();
        mainDiagonal(player2);
        secondaryDiagonal(player2);
        horizontalLine(player2);
        verticalLine(player2);
        moveValidate(logicValue, player2);
        draw();
    }

    public void mainDiagonal( String value ) {
        logicValue = true;
        for (int l = 0; l < 3; l++) {
            if (matrix[l][l] != value) {
                logicValue = false;
            }
        }
        if (logicValue == true) {
            System.out.println("You Winn!!! ");
            System.exit(1);
        }
    }

    public void secondaryDiagonal( String value ) {
        logicValue = true;

        for (int l = 0, c = 2; l < 3 && c >= 0; l++, c--) {
            if (matrix[l][c] != value) {
                logicValue = false;
            }
        }

        if (logicValue == true) {
            System.out.println("You Winn!!! ");
            System.exit(1);
        }
    }

    public void horizontalLine( String value ) {
        int counter = 0;

        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (matrix[l][c] == value) {
                    counter++;
                }
            }
            if (counter == 3) {
                logicValue = true;
                System.out.println("You Winn!!! ");
                System.exit(1);
            }
            counter = 0;
        }
    }

    public void verticalLine(String value) {
        int counter = 0;

        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (matrix[c][l] == value) {
                    counter++;
                }
            }
            if (counter == 3) {
                logicValue = true;
                System.out.println("You Winn!!! ");
                System.exit(1);
            }
            counter = 0;
        }
    }

    public boolean draw() {
        if (turns >= 9) {
            logicValue = true;
            System.out.println(" < D R A W >  (-.-')");
            System.exit(1);
        }
        return logicValue;
    }
}