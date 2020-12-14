import TicTacToe.TicTacToe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите одно число - размерность поля");
        var fieldSize = new Scanner(System.in).nextInt();
        new TicTacToe(fieldSize).start();
    }
}
