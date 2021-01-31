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
    public CherryBomb(int x , int y, int numberOfFrame) throws IOException {
        // super(x,y);
        setX(x);
        setY(y);
        setRow((y-130)/120);
        setColumn((x-40)/100);
        setCost(150);
        setHealth(70);
        img = ImageIO.read(new File("images\\\\cherry.png"));
        setGif();
    }

    //setter
    public void setGif(){
        image = new ImageIcon("images\\Gifs\\newCherryBomb.gif").getImage();
    }
//    public void setHalfGif(){
//        image = new ImageIcon("images\\Gifs\\walnut_half_life.gif").getImage();
//    }
//    public void setDeadGif(){
//        image = new ImageIcon("images\\Gifs\\walnut_dead.gif").getImage();
//    }
//
//    public void setGif(){
//        if(getHealth() > 100 &&  getHealth() <= 150){
//            setFullGif();
//        }else if(getHealth() > 50 &&  getHealth() <= 100){
//            setHalfGif();
//        }else if(getHealth() >= 1 &&  getHealth() <= 50){
//            setDeadGif();
//        }else setAlive(false);
//    }

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

    //getter
    public int getNumberOfFrame(){
        return numberOfFrame;
    }

    //setter
    public void setNumberOfFrame(int numberOfFrame) {
        this.numberOfFrame = numberOfFrame;
    }
//    private Image cimage;
//    public CherryBomb(int x , int y) throws IOException {
//        setX(x);
//        setY(y);
////        setRow((y-130)/120);
////        setColumn((x-40)/100);
//        setCost(150);
//        setHealth(70);
//        img = ImageIO.read(new File("images\\cherry.png"));
//        image = new ImageIcon("images\\Gifs\\newCherryBomb.gif").getImage(); // gif baraye terekidan
//        //System.out.print("8");
//    }
//    public int getCost(){
//        return cost;
//    }
//    public Image getCImage() {
//        System.out.print("9");
//        return cimage;
//    }
//    public void setGif(){
//        image = new ImageIcon("images\\Gifs\\newCherryBomb.gif").getImage(); // gif baraye terekidan
//
//    }
//
//    public Image getImage() {
//        return image;
//    }
////    public Image getImage() {
////        return image;
////    }
////
//    public void setImage(Image image) {
//        this.image = image;
//    }
}
