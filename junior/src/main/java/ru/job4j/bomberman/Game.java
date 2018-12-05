package ru.job4j.bomberman;

public class Game {

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        Board board = new Board(20, 20);
        Player hero = new Player("Superman", new Cell(10,10), board);
        Thread t = new Thread(hero);
        t.start();

        Player enemy = new Player("Enemy", new Cell(5,10), board);
        Thread t2 = new Thread(enemy);
        t2.start();

        Player enemy2 = new Player("Enemy2", new Cell(10,15), board);
        Thread t3 = new Thread(enemy2);
        t3.start();
    }

}
