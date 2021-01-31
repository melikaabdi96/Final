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
    public enum bulletType{normal , frozen}
    private static  bulletType bulletType;
    public Bullet(int x , int y , bulletType bulletType) throws Exception {
        setX(x);
        setY(y);
        this.bulletType = bulletType;
        setFields();
        collided = false;
    }

    //getter
    public boolean isCollided() {
        return collided;
    }

    //setter
    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    //getttr
    public Bullet.bulletType getType() {
        return bulletType;
    }

    //setter
    public void setType(Bullet.bulletType type) {
        this.bulletType = type;
    }

    @Override
    protected void setFields() throws Exception {
        if(bulletType == bulletType.normal){
            this.damage = 30;
            bImage = ImageIO.read(new File("images\\pea.png"));
        }else if(bulletType == bulletType.frozen){
            this.damage = 35;
            bImage = ImageIO.read(new File("images\\freezepea.png"));
        }else throw new Exception(" No bullet type found !!!!");
    }

    //getter
    public int getDamage() {
        return damage;
    }


    /**
     * @return true if x of bullet is more than 1000
     */
    public boolean endOfFeild(){
        if(this.getX() > 1000)return true;
        else return false;
    }

    //getter
    public Image getbImage() {
        return bImage;
    }

    //setter
    public void setbImage(BufferedImage bImage) {
        this.bImage = bImage;
    }
}
