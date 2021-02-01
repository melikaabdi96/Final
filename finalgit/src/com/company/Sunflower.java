package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
/**
 * This class holds informations of sunflower
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Sunflower extends Plant {
    private Sun sun = null ;
    public Sunflower(int x, int y ) throws IOException {
        setAlive(true);
        setX(x);
        setY(y);
        setColumn((x-40)/100);
        setRow((y-130)/120);
        setCost(50);
        setHealth(50);
        img = ImageIO.read(new File("images\\sunflower.png"));
        setLightSunflower();
        setStartTime(System.currentTimeMillis());
    }
    //getter
    public int getCost(){
        return cost;
    }
    //setter
    public void setDyingSunflower(){
        image = new ImageIcon("images\\Gifs\\sun_flower_dying.gif").getImage();
    }
    //setter
    public void setLightSunflower(){
        image = new ImageIcon("images\\Gifs\\sun_flower.gif").getImage();
    }
    //setter
    public void setDarkSunflower(){
        image = new ImageIcon("images\\Gifs\\sunflower.gif").getImage();
    }

    public void setGif(){

        if(sun == null)setLightSunflower();
        else setDarkSunflower();

        if(getHealth() <= 30){
            setDyingSunflower();
        }
    }
    //setter
    public Sun getSun() {
        return sun;
    }
    //setter
    public void setSun(Sun sun) {
        this.sun = sun;
    }
}
