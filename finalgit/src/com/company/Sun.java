package com.company;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 * This class holds informations of sun
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Sun extends Element{
    private final int value = 25;
//    private final int lastingTime = 10;

    /**
     *
     * @param x location of sun
     * @param y location of sun
     * @throws IOException
     */
    public Sun(int x, int y) throws IOException {
        setX(x);
        setY(y);
        img = ImageIO.read(new File("images\\sun.png"));
        startTime = System.currentTimeMillis();
    }

    //getter
    public int getValue() {
        return value;
    }

//    public int getLastingTime() {
//        return lastingTime;
//    }
}
