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

        Enemy enemy = new Enemy("Enemy", new Cell(11,11), board);
        Thread t2 = new Thread(enemy);
        t2.setDaemon(true);
        t2.start();

        Enemy enemy2 = new Enemy("Enemy2", new Cell(12,12), board);
        Thread t3 = new Thread(enemy2);
        t3.setDaemon(true);
        t3.start();

        Enemy enemy3 = new Enemy("Enemy3", new Cell(9,8), board);
        Thread t4 = new Thread(enemy3);
        t4.setDaemon(true);
        t4.start();
    }

}
