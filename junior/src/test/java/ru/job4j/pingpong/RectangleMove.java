package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private Direction direction;

    enum Direction { LEFT, RIGHT }

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
        this.direction = Direction.RIGHT;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if(this.direction == Direction.RIGHT) {
                this.rect.setX(this.rect.getX() + 5);
            } else {
                this.rect.setX(this.rect.getX() - 5);
            }
            if(rect.getX() >= 290) {
                this.direction = Direction.LEFT;
            }
            if(rect.getX() <= 0) {
                this.direction = Direction.RIGHT;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}