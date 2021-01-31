package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * This class holds informations of giantwallnut
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class GiantWalNut extends Plant {
    //    private Image image;
    public GiantWalNut(int x , int y) throws IOException {
        // super(x,y);
        setX(x);
        setY(y);
        setRow((y-130)/120);
        setColumn((x-40)/100);
        setCost(50);
        setHealth(150);
        img = ImageIO.read(new File("images\\walnut.png"));
        setFullGif();
    }

    //setter
    public void setFullGif(){
        image = new ImageIcon("images\\Gifs\\walnut_full_life.gif").getImage();
    }

    //setter
    public void setHalfGif(){
        image = new ImageIcon("images\\Gifs\\walnut_half_life.gif").getImage();
    }

    //setter
    public void setDeadGif(){
        image = new ImageIcon("images\\Gifs\\walnut_dead.gif").getImage();
    }

    //setter
    public void setGif(){
        if(getHealth() > 100 &&  getHealth() <= 150){
            setFullGif();
        }else if(getHealth() > 50 &&  getHealth() <= 100){
            setHalfGif();
        }else if(getHealth() >= 1 &&  getHealth() <= 50){
            setDeadGif();
        }else setAlive(false);
    }

    //getter
    public int getCost(){
        return cost;
    }

    //getter
    public Image getImage() {
        return image;
    }

    //setter
    public void setImage(Image image) {
        this.image = image;
    }
}
