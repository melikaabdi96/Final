package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Element {
    protected BufferedImage img;
    protected Image image;
    protected int health;
    protected float x;
    protected int y;
    protected  long startTime;
    private boolean alive;
    private boolean stoped;
    protected int row;
    protected int column;

    Element(){
        stoped = false;
    }
    public void moveRight(int speed) {

        if(!stoped)x =  Math.min(x + speed,1000);
    }
    public void moveLeft(float speed) {

        if(!stoped)x =( Math.max(((float)x)- speed,10));
    }
    public void movedown(int speed) {
        if(!stoped) y =  Math.min(y + speed,725);
    }
    public float getX() {
        return x;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public BufferedImage getImg(){
        return img;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int x) {
        this.row = x;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int x) {
        this.column = x;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int x) {
        this.health = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long s) {
        this.startTime = s;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    protected void setFields() throws IOException, LineUnavailableException, UnsupportedAudioFileException, Exception {}

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

    public boolean isStoped() {
        return stoped;
    }



}

