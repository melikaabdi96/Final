package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
/**
 * This class holds informations of Machine
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Machine extends  Element{
    private boolean readyToMove;
    public  Machine(int row) throws IOException {
        // row should be from 1 to 5
        setX(10);
        setY((120 * (row)) + 60  + 130 );
        setRow(row);
        readyToMove = false;
        img = ImageIO.read(new File("images\\Lawn_Mower.png"));//lawn_mower
        image = new ImageIcon("images\\Gifs\\lawn_mower.gif").getImage();
    }

    /**
     * @return true if location of machine = 1000
     */
    public boolean endOfFeild(){
        if(getX() == 1000) return true;
        else return false;
    }

    //getter
    public boolean isReadyToMove() {
        return readyToMove;
    }

    //setter
    public void setReadyToMove(boolean readyToMove) {
        this.readyToMove = readyToMove;
    }
}

