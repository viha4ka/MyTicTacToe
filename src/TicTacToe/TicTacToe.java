package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private final char COMPUTER_DOT = 'o';
    private final char PLAYER_DOT = 'x';
    private final char EMPTY_DOT = '.';
    private final Scanner sc = new Scanner(System.in);
    private final Random rnd = new Random();
    private final int FIELD_SIZE;
    private final char[][] FIELD;
    private int x = -1;
    private int y = -1;


    public TicTacToe(int fieldSize) {
        FIELD_SIZE = fieldSize;
        FIELD = new char[fieldSize][fieldSize];
        for (int i = 0; i < FIELD_SIZE; i++)
            for (int j = 0; j < FIELD_SIZE; j++)
                FIELD[i][j] = EMPTY_DOT;
    }

    public void start() {
        var moveCount = 0;
        printMap();
        while (true) {
            if (moveCount % 2 == 0)
                playerMakeMove();
            else
                computerMakeMove();

            printMap();
            if (isGameEnded(moveCount, x, y))
                break;
            moveCount++;
        }
    }

    private boolean isGameEnded(int moveCount, int x, int y) {
        char playerSymbol;
        if (moveCount % 2 == 0)
            playerSymbol = PLAYER_DOT;
        else
            playerSymbol = COMPUTER_DOT;

        //Столбец
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (FIELD[x][i] != playerSymbol)
                break;
            if (i == FIELD_SIZE - 1) {
                println(playerSymbol + " победили");
                return true;
            }
        }

        //Ряд
        for (int i = 0; i < FIELD_SIZE; i++) {
            if (FIELD[i][y] != playerSymbol)
                break;
            if (i == FIELD_SIZE - 1) {
                println(playerSymbol + " победили");
                return true;
            }
        }

        //Главная диагональ
        if (x == y) {
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (FIELD[i][i] != playerSymbol)
                    break;
                if (i == FIELD_SIZE - 1) {
                    println(playerSymbol + " победили");
                    return true;
                }
            }
        }

        //Побочная диагональ
        if (x + y == FIELD_SIZE - 1) {
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (FIELD[i][(FIELD_SIZE - 1) - i] != playerSymbol)
                    break;
                if (i == FIELD_SIZE - 1) {
                    println(playerSymbol + " победили");
                    return true;
                }
            }
        }

        //Ничья
        if (moveCount == (Math.pow(FIELD_SIZE, 2) - 1)) {
            println("Ничья");
            return true;
        }
        return false;
    }

    private void printMap() {
        print("  ");
        for (int i = 1; i <= FIELD_SIZE; i++)
            print(i + " ");

        println("");
        for (int i = 0; i < FIELD_SIZE; i++) {
            print(i + 1 + " ");

            for (int j = 0; j < FIELD_SIZE; j++)
                print(FIELD[i][j] + " ");

            println("");
        }
        println("");
    }

    private void playerMakeMove() {
        println("Ваш ход. Введите через пробел два числа от 1 до " + FIELD_SIZE);
        while (true) {
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            if (isWrongMove(x, y))
                println("Такой ход не возможен");
            else
                break;
        }
        FIELD[x][y] = PLAYER_DOT;
    }


    private void computerMakeMove() {
        while (isWrongMove(x, y)) {
            x = rnd.nextInt(FIELD_SIZE);
            y = rnd.nextInt(FIELD_SIZE);
        }
        FIELD[x][y] = COMPUTER_DOT;
    }

    private boolean isWrongMove(int x, int y) {
        return x < 0 || x >= FIELD_SIZE || y < 0 || y >= FIELD_SIZE || FIELD[x][y] != EMPTY_DOT;
    }

    private void println(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }
}
