package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final ReentrantLock[][] board;
    private int height;
    private int width;

    public Board(int height, int width) {
        board = new ReentrantLock[height][width];
        this.height = height;
        this.width = width;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        createBlocks(10);
    }

    public ReentrantLock getCell(Cell cell) {
        return board[cell.getX()][cell.getY()];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void createBlocks(int num) {
        for(int i = 0; i < num; i++) {
            int posX = (int) (Math.random() * height);
            int posY = (int) (Math.random() * width);
            board[posX][posY].lock();
            System.out.println("Create block in (" + posX + "," + posY + ")");
        }
    }

}
