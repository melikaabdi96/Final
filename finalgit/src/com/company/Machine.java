package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Machine extends  Element{

    public  Machine(int row) throws IOException {
        // row should be from 1 to 5
        setX(10);
        setY((120 * (row-1)) + 60  + 130 );
        setRow(row);
        img = ImageIO.read(new File("images\\Lawn_Mower.png"));//lawn_mower
        image = new ImageIcon("images\\Gifs\\lawn_mower.gif").getImage();
    }


    public boolean endOfFeild(){
        if(getX() == 1000) return true;
        else return false;
    }


}

