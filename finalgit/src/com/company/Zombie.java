package com.company;

import com.company.ball.GameState;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * This class holds informations of zombies
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Zombie extends Element {
    //  protected   int power ;
//    public enum type{coneHead , normal , bucketHead}
    //speed of zombie
    private double speed;
    private int attack;
    public enum zombiType{CONEHEAD , NORMAL , BUCKHEAD , FOOTBALL}
    private zombiType zombieType;
    //    private int speed;
//    private int attack;
    private boolean firstFrozenBulletShot;
    private GameState.gameType gameType;
    private long attackTime;
    private boolean firstTime;

    /**
     * @param zombieType type of zombie
     * @param gameType type of game
     * @throws Exception
     */
    public Zombie(zombiType zombieType , GameState.gameType gameType) throws Exception {
        this.gameType = gameType;
        firstTime = true;
        firstFrozenBulletShot = true;
        setX(900);
        int random = new SecureRandom().nextInt(5) + 1;
        setRow(random-1);
        int y = 120* random + 130 ; // margin = 20 , 136 = height of each row
        setY(y);
        this.zombieType = zombieType;
        setAlive(true);
        setFields();
    }


    @Override
    protected void setFields() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        //this.sound = new Sound("zombie.wav");
        try{
            if (zombieType.equals(zombiType.NORMAL)) {
                img = ImageIO.read(new File("images\\zombie1.png"));
                setHealth(200);
                this.speed = 0.8333 ;//5/6;
                this.attack = 5;
            } else if (zombieType.equals(zombiType.CONEHEAD)) {
                img = ImageIO.read(new File("images\\zombie2.png"));
                setHealth(560);
                this.speed =  (gameType== GameState.gameType.NORMAL?  0.952: 1.111);//20/21 , 10/9
                this.attack = (gameType== GameState.gameType.NORMAL? 10 : 15);
            } else if (zombieType.equals(zombiType.BUCKHEAD)) {
                img = ImageIO.read(new File("images\\StrongZombie.png"));
                setHealth(1300);
                this.speed =  (gameType== GameState.gameType.NORMAL?  0.952: 1.111);//20/21 , 10/9
                this.attack = (gameType== GameState.gameType.NORMAL? 20 : 25);
            } else if (zombieType.equals(zombiType.FOOTBALL)) {
                img = ImageIO.read(new File("images\\football.png"));
                setHealth(1500);
                this.speed = 0.8333;// 5/6;
                this.attack = 30;
            }}catch (Exception e){
            e = new Exception(" Can't read input file!");
        }
    }

    //getter
    public long getAttackTime() {
        return attackTime;
    }

    //setter
    public void setAttackTime(long attackTime) {
        if(firstTime){
            this.attackTime = attackTime;
            firstTime =false;
        }
    }

    //setter
    public void setDyingGif(){
        if(zombieType.equals(zombiType.FOOTBALL)){
            image = new ImageIcon("images\\Gifs\\zombie_football_dying.gif").getImage();
        }else {
            image = new ImageIcon("images\\Gifs\\zombie_normal_dying.gif").getImage();
        }
    }

    //setter
    public void setBurntGif(){
        this.image = new ImageIcon("images\\Gifs\\burntZombie.gif").getImage();
    }

    //setter
    public void setZombieGif() throws Exception{
        //calHealth();
        if (zombieType.equals(zombiType.NORMAL)){
            this.image = new ImageIcon("images\\Gifs\\zombie_normal.gif").getImage();
        } else if (zombieType.equals(zombiType.CONEHEAD)) {
            if (health >= 200) {
                image = new ImageIcon("images\\Gifs\\z3.gif").getImage();
            } else {
                this.image = new ImageIcon("images\\Gifs\\zombie_normal.gif").getImage();
            }
        } else if (zombieType.equals(zombiType.BUCKHEAD)) {
            if (health >= 200) {
                this.image = new ImageIcon("images\\Gifs\\bucketheadzombie2.gif").getImage();
            } else {
                this.image = new ImageIcon("images\\Gifs\\zombie_normal.gif").getImage();
            }
        }else if (zombieType.equals(zombiType.FOOTBALL)) {
            this.image = new ImageIcon("images\\Gifs\\zombie_football.gif").getImage();
        }else throw new Exception(" No type detected !!!!");
    }

    //getter
    public int getAttack() {
        return attack;
    }

    //setter
    public void setAttack(int attack) {
        this.attack = attack;
    }

    //getter
    public double getSpeed() {
        return speed;
    }

    //setter
    public void setSpeed(double speed) {
        this.speed = speed;
    }


    public boolean arrivesHome(){
        return this.getX() < 20;
    }
//    public int getPower() {
//        return power;
//    }
//
//    public void setPower(int power) {
//        this.power = power;
//    }

    public zombiType getZombieType() {
        return zombieType;
    }

    public void setZombieType(zombiType zombieType) throws Exception {
        this.zombieType = zombieType;
    }

    public boolean getFirstFrozenBulletShot() {
        return firstFrozenBulletShot;
    }

    public void setFirstFrozenBulletShot(boolean firstFrozenBulletShot) {
        this.firstFrozenBulletShot = firstFrozenBulletShot;
    }


    //    private void setFields() throws Exception {
//        if(zombieType == zombieType.NORMAL){
//
//            this.power = 200;
//            img = ImageIO.read(new File("images\\zombie1.png"));
//            this.image = new ImageIcon("images\\Gifs\\zombie_normal.gif").getImage();
//
//        }else if(zombieType == zombieType.CONEHEAD){
//
//            this.power = 560;
//            img = ImageIO.read(new File("images\\zombie2.png"));
//            image = new ImageIcon("images\\Gifs\\coneheadzombie.gif").getImage();
//
//        }else if(zombieType == zombieType.BUCKHEAD){
//
//            this.power = 1300;
//            img = ImageIO.read(new File("images\\StrongZombie.png"));
//            this.image = new ImageIcon("images\\Gifs\\bucketheadzombie.gif").getImage();
//
//        }else throw new Exception(" No type detected !!!!");
//    }

//    @Override
//    public void move() {
//
//    }


//    protected void setFields() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
//        this.sound = new Sound("zombie.wav");
//        if (game.equals(gameType.NORMAL)) {
//            if (zombieType.equals(type.NORMAL)) {
//                img = ImageIO.read(new File("images\\zombie1.png"));
//                setHealth(200);
//                this.speed = 4;
//                this.attack = 5;
//            } else if (zombieType.equals(type.CONEHEAD)) {
//                img = ImageIO.read(new File("images\\zombie2.png"));
//                setHealth(560);
//                this.speed = 3.5;
//                this.attack = 10;
//            } else if (zombieType.equals(type.BUCKHEAD)) {
//                img = ImageIO.read(new File("images\\StrongZombie.png"));
//                setHealth(1300);
//                this.speed = 3.5;
//                this.attack = 20;
//            } else if (zombieType.equals(type.FOOTBALL)) {
//                img = ImageIO.read(new File("images\\football.png"));
//                setHealth(1500);
//                this.speed = 3;
//                this.attack = 30;
//            }
//        }
//    }




}

