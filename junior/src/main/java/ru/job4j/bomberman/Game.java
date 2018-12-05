package ru.job4j.bomberman;

public class Game {

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        Player hero = new Player(new Cell(10,10), new Board(20, 20));
        Thread t = new Thread(hero);
        t.start();
    }

}
