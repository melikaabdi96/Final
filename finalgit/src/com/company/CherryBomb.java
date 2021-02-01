package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * This class holds informations of cherryBomb
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class CherryBomb extends Plant {
    int numberOfFrame;
    public CherryBomb(int x , int y , int numberOfFrame) throws IOException {
        // super(x,y);
        this.numberOfFrame=numberOfFrame;
        setX(x);
        setY(y);
        setRow((y-130)/120);
        setColumn((x-40)/100);
        setCost(150);
        setHealth(70);
        img = ImageIO.read(new File("images\\\\cherry.png"));
        setGif();
    }

    public void setGif(){
        image = new ImageIcon("images\\Gifs\\cherry.gif").getImage();
    }

    public int getCost(){
        return cost;
    }
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getNumberOfFrame(){
        return numberOfFrame;
    }

}
