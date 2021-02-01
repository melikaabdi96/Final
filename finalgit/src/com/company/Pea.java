package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class holds informations of peas
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Pea extends Plant {
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Image pImage;
    public enum peaType{ PEA , FREEZEPEA}
    private   peaType pType;
    private boolean readyToShoot;
    // private int damage;
    public Pea(int x , int y , peaType peaType) throws IOException {
        setX(x);
        setY(y);
        readyToShoot = false;
        setRow((y-130)/120 );
        setColumn((x-40)/100);
        pType = peaType;
        setFields();
    }
    public Image getpImage() {
        return pImage;
    }

    public void setpImage(Image pImage) {
        this.pImage = pImage;
    }
    //    @Override
    protected void setFields() throws IOException {
        if(pType == peaType.PEA){
            this.cost = 100;
            setHealth(70);
            pImage = new ImageIcon("images\\Gifs\\pea_shooter.gif").getImage();
        }else if(pType == peaType.FREEZEPEA){
            this.cost = 175;
            setHealth(100);
            pImage = new ImageIcon("images\\Gifs\\freezepeashooter.gif").getImage();
        }
    }
    public int getCost(){
        return cost;
    }
    //
    public peaType getpType() {
        return pType;
    }

    public ArrayList<Bullet> getBullets(){
        return bullets;
    }

    public void setDyingpea(){
        if(getHealth() <= 20) {
            if (pType == peaType.PEA) {
                pImage = new ImageIcon("images\\Gifs\\pea_shooter_dying.gif").getImage();
            } else pImage = new ImageIcon("images\\Gifs\\pea_shooter_dying.gif").getImage(); //88888888888888888888888888888888
        }
    }

    public boolean isReadyToShoot() {
        return readyToShoot;
    }

    public void setReadyToShoot(boolean readyToShoot) {
        this.readyToShoot = readyToShoot;
    }

    public void setpea(){
        if(pType == peaType.PEA){
            pImage = new ImageIcon("images\\Gifs\\pea_shooter.gif").getImage();
        }else pImage = new ImageIcon("images\\Gifs\\freezepeashooter.gif").getImage();

    }
    public void setGif(){
        if(getHealth() <= 30)setDyingpea();
        else setpea();
    }

    /**
     * shoot bullets
     *
     * @throws Exception
     */
    public void shoot() throws Exception {
        if(pType == peaType.PEA) {
            Bullet b = new Bullet((int)this.getX() + 50, this.getY() + 4, Bullet.bulletType.NORMAL);
            bullets.add(b);
            b.setRow((y-130)/120 );
        }else{
            Bullet b = new Bullet((int)this.getX() + 50, this.getY() + 4, Bullet.bulletType.FROZEN);
            bullets.add(b);
            b.setRow((y-130)/120 );
        }
    }

}
