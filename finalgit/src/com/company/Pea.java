package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
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

    public enum peaType {PEA, FREEZEPEA}

    private static peaType pType;
    private boolean readyToShoot;

    // private int damage;
    public Pea(int x, int y, peaType peaType) throws IOException {
        setX(x);
        setY(y);
        readyToShoot = false;
        setRow((y - 130) / 120);
        setColumn((x - 40) / 100);
        pType = peaType;
        setFields();
        // setGif();
    }

    //getter
    public Image getpImage() {
        return pImage;
    }

    //setter
    public void setpImage(Image pImage) {
        this.pImage = pImage;
    }

    @Override
    protected void setFields() throws IOException {
        if (pType == peaType.PEA) {
            this.cost = 100;
//            this.power = 70;
            setHealth(70);
            pImage = new ImageIcon("images\\Gifs\\pea_shooter.gif").getImage();
            // this.damage = 30;
            //img = ImageIO.read(new File("images\\freezepea.png"));   888888888888888888888888888888888888888888888
        } else if (pType == peaType.FREEZEPEA) {
            this.cost = 175;
//            this.power = 100;
            setHealth(100);
            //this.damage = 35;
            pImage = new ImageIcon("images\\Gifs\\freezepeashooter.gif").getImage();
            img = ImageIO.read(new File("images\\freezepea.png"));
        }
    }

    //getter
    public int getCost() {
        return cost;
    }

    //
    //getter
    public peaType getpType() {
        return pType;
    }

    //getter
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    //settr
    public void setDyingpea() {
        if (getHealth() <= 30) {
            if (pType == peaType.PEA) {
                pImage = new ImageIcon("images\\Gifs\\pea_shooter_dying.gif").getImage();
            } else
                pImage = new ImageIcon("images\\Gifs\\sun_flower_dying.gif").getImage(); //88888888888888888888888888888888
        }
    }

    //getter
    public boolean isReadyToShoot() {
        return readyToShoot;
    }

    //setter
    public void setReadyToShoot(boolean readyToShoot) {
        this.readyToShoot = readyToShoot;
    }

    //setter
    public void setpea() {
        if (pType == peaType.PEA) {
            pImage = new ImageIcon("images\\Gifs\\pea_shooter.gif").getImage();
        } else pImage = new ImageIcon("images\\Gifs\\freezepeashooter.gif").getImage();

    }

    //setter
    public void setGif() {
        if (getHealth() <= 30) setDyingpea();
        else setpea();
    }

    /**
     * shoot bullets
     *
     * @throws Exception
     */
    public void shoot() throws Exception { //88888888888888888888888888888888888888888888 tagheer bede
        if (pType == peaType.PEA) {
            Bullet b = new Bullet((int) this.getX() + 50, this.getY() + 4, Bullet.bulletType.normal);
            bullets.add(b);
            b.setRow((y - 130) / 120);
        } else {
            Bullet b = new Bullet((int) this.getX() + 50, this.getY() + 4, Bullet.bulletType.frozen);
            bullets.add(b);
            b.setRow((y - 130) / 120);
        }
    }
}

