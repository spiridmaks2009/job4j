package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Enemy implements Runnable {
    private String name;
    private Cell position;
    private Board board;
    private ReentrantLock lock;

    public Enemy(String name, Cell position, Board board) {
        this.position = position;
        this.board = board;
        this.name = name;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public boolean move(Cell source, Cell dist) throws InterruptedException{
        boolean flag = false;
        System.out.println(name + " move from (" + source.getX() + "," + source.getY() + ") to ( "
                + dist.getX() + "," + dist.getY() + ")");
        ReentrantLock oldCell = board.getCell(source);
        ReentrantLock newCell = board.getCell(dist);

        if(newCell.tryLock(500, TimeUnit.MILLISECONDS)) {
            setPosition(dist);
            flag = true;
            oldCell.unlock();
        }
        return flag;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        ReentrantLock startPos = board.getCell(position);
        startPos.lock();
        try {
            while(!Thread.currentThread().isInterrupted()) {
                boolean result;
                Cell dest = new Cell((int) (-1.9 + Math.random()*3.8) + position.getX(), (int) (-1.9 + Math.random()*3.8) + position.getY());
                int dx = dest.getX();
                int dy = dest.getY();
                if(dx < 0 || dx >= board.getHeight() || dy < 0 || dy >= board.getWidth())
                {
                    result = false;
                } else {
                    result = move(position, dest);
                }
                if (!result) {
                    System.out.println(name + " waiting...");
                    Thread.sleep(5000);
                    continue;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException ignored) { }
    }
}
