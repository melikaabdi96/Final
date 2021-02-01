package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class holds informations of elements
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Element {
    protected BufferedImage img;
    protected Image image;
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
        if(!stoped) y =  Math.min(y + speed,690);
    }
    public double getX() {
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
//    /**
//     * set fields of elements
//     * @throws IOException
//     * @throws LineUnavailableException
//     * @throws UnsupportedAudioFileException
//     * @throws Exception
//     */
//
//    protected void setFields() throws IOException, LineUnavailableException, UnsupportedAudioFileException, Exception {}

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

    public boolean isStoped() {
        return stoped;
    }



}
