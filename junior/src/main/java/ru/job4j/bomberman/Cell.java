package ru.job4j.bomberman;

public class Cell {
    private int x;
    private int y;

    public Cell(int X, int Y) {
        this.x = X;
        this.y = Y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
