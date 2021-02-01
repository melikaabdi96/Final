package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * This class holds informations of bullets
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Bullet extends Element {
    private int damage ;
    private boolean collided ;
    private BufferedImage bImage;
    public enum bulletType{NORMAL, FROZEN}
    private   bulletType bType;
    public Bullet(int x , int y , bulletType bType) throws Exception {
        setX(x);
        setY(y);
        this.bType = bType;
        setFields();
        collided = false;
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public Bullet.bulletType getType() {
        return bType;
    }

    protected void setFields() throws Exception {
        if(bType== bulletType.NORMAL){
            this.damage = 30;
            this.bImage = ImageIO.read(new File("images\\pea.png"));
        }else if(bType == bulletType.FROZEN){
            this.damage = 35;
            this.bImage = ImageIO.read(new File("images\\freezepea.png"));
        }
    }

    public int getDamage() {
        return damage;
    }


    /**
     * @return true if x of bullet is more than 1000
     */
    public boolean endOfFeild(){
        return this.getX() > 1000;
    }

    public Image getbImage() {
        return bImage;
    }

    public void setbImage(BufferedImage bImage) {
        this.bImage = bImage;
    }
}
