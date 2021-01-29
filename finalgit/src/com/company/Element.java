package com.company;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class holds informations of elements
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Element {
    //image of elements
    protected BufferedImage img;
    //image of elements
    protected Image image;
    //health of elements
    protected int health;
    protected double x;
    protected int y;
    protected  long startTime;
    private boolean alive;
    private boolean stoped;
    protected int row;
    protected int column;

    Element(){

        stoped = false;
    }

    /**
     * move to rigth
     * @param speed of element
     */
    public void moveRight(double speed) {

        if(!stoped)x =  Math.min(x + speed,1000);
    }

    /**
     * move to left
     * @param speed of element
     */
    public void moveLeft(double speed) {

        if(!stoped)x = Math.max(x- speed,10);
    }

    /**
     * move down
     * @param speed of element
     */
    public void movedown(int speed) {
        if(!stoped) y =  Math.min(y + speed,725);
    }

    //getter
    public double getX() {
        return x;
    }

    //getter
    public Image getImage() {
        return image;
    }

    //setter
    public void setImage(Image image) {
        this.image = image;
    }

    //getter
    public BufferedImage getImg(){
        return img;
    }

    //setter
    public void setX(int x) {
        this.x = x;
    }

    //getter
    public int getRow() {
        return row;
    }

    //setter
    public void setRow(int x) {
        this.row = x;
    }

    //getter
    public int getColumn() {
        return column;
    }

    //setter
    public void setColumn(int x) {
        this.column = x;
    }

    //getter
    public int getHealth() {
        return health;
    }

    //setter
    public void setHealth(int x) {
        this.health = x;
    }

    //getter
    public int getY() {
        return y;
    }

    //setter
    public void setY(int y) {
        this.y = y;
    }

    //getter
    public long getStartTime() {
        return startTime;
    }

    //setter
    public void setStartTime(long s) {
        this.startTime = s;
    }

    //setter
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    //check if element if alive
    public boolean isAlive() {
        return alive;
    }

    /**
     * set fields of elements
     * @throws IOException
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws Exception
     */
    protected void setFields() throws IOException, LineUnavailableException, UnsupportedAudioFileException, Exception {}

    //stop the element
    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

    //check if element is stopped
    public boolean isStoped() {
        return stoped;
    }



}
