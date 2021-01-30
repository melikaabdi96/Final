package com.company.ball;
// for using fps :  for attack   --->  health - (attack)/fps     ,   move ---> width/(fps * numberOfSec)

import com.company.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState implements Serializable{

    public int locX, locY, diam;
    private int scores , numberOfWinings , numberOfGameOvers;
    public boolean gameOver , start , stopManu , endOfGame;
    private String playerName;
    private  int playersSuns;
    public enum gameType{HARD , NORMAL}
    private  gameType type;
    private boolean mute;
    private Element[][] elements;
    private boolean[] machineInRow ;
    private boolean[] zombieInRow ;
    private boolean mousePress;
    private int mouseX, mouseY;
    private MouseHandler mouseHandler;
    private ArrayList<Zombie> zombies = new ArrayList<>();
//    private CopyOnWriteArrayList<Zombie> zombies = new CopyOnWriteArrayList<>();
    private ArrayList<Machine> machines = new ArrayList<>();
    private ArrayList<Sun> suns = new ArrayList<>();
    private  int waveNum ;
    private String selectedCard ;
    private  long gameStartTime;
    private  long lastPurchaseSunflower = 0;
    private  long lastPurchasePeaShooter = 0;
    private  long lastPurchaseGiantWalnut = 0;
    private  long lastPurchaseCherryBomb = 0 ;
    private  long lastPurchaseFrozenPeaShooter = 0;
//    private  Iterator<Zombie> Iter = zombies.iterator();
    private  boolean toPlace = false;   // if we should place purchased plant some where
    private int numberOfFrame ;

    public GameState() throws IOException {
        elements = new Element[5][9];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                elements[i][j] = null;
            }
        }
        zombieInRow = new boolean[5];
        machineInRow = new boolean[5];
        for(int i = 0 ; i < 5 ; i++ ){
            machineInRow[i] = true;
            zombieInRow[i] = false;
        }
        numberOfGameOvers =0;
        numberOfWinings = 0;
        numberOfFrame = 1;
        scores = 0;
        locX = 100;
        locY = 100;
        diam = 32;
        gameOver = false;
        stopManu = false;
        start = false;
        mute = false;
        selectedCard = "-";
        //
        //
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        type = gameType.NORMAL;  // defualt type is normal
        //
        mouseHandler = new MouseHandler();

        playersSuns = 550;
        gameStartTime = System.currentTimeMillis();
        setMachines();
    }
    /**
     * The method which updates the game state.
     */
    public void update() throws Exception {

    }
   
   



    public MouseListener getMouseListener() {
        return mouseHandler;
    }
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }


    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            mouseX = e.getX();
            mouseY = e.getY();
           
        }
        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            mousePress = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }
    public void addToFile(){
       
    }

    public ArrayList<Zombie> getZombies(){
        return zombies;
    }

    public long getLastPurchaseSunflower() {
        return lastPurchaseSunflower;
    }

    public void setLastPurchaseSunflower(long lastPurchaseSunflower) {
        this.lastPurchaseSunflower = lastPurchaseSunflower;
    }

    public long getLastPurchasePeaShooter() {
        return lastPurchasePeaShooter;
    }

    public void setLastPurchasePeaShooter(long lastPurchasePeaShooter) {
        this.lastPurchasePeaShooter = lastPurchasePeaShooter;
    }

    public long getLastPurchaseGiantWalnut() {
        return lastPurchaseGiantWalnut;
    }

    public void setLastPurchaseGiantWalnut(long lastPurchaseGiantWalnut) {
        this.lastPurchaseGiantWalnut = lastPurchaseGiantWalnut;
    }

    public long getLastPurchaseCherryBomb() {
        return lastPurchaseCherryBomb;
    }

    public void setLastPurchaseCherryBomb(long lastPurchaseCherryBomb) {
        this.lastPurchaseCherryBomb = lastPurchaseCherryBomb;
    }

    public long getLastPurchaseFrozenPeaShooter() {
        return lastPurchaseFrozenPeaShooter;
    }

    public void setLastPurchaseFrozenPeaShooter(long lastPurchaseFrozenPeaShooter) {
        this.lastPurchaseFrozenPeaShooter = lastPurchaseFrozenPeaShooter;
    }

    public int getPlayersSuns() {
        return playersSuns;
    }

    public void setPlayersSuns(int playersSuns) {
        this.playersSuns = playersSuns;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        addToFile();
    }
    public GameState.gameType getType() {
        return type;
    }

    public void setType(GameState.gameType type) {
        this.type = type;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public Element[][] getElements() {
        return elements;
    }

    public void setElements(Element[][] elements) {
        this.elements = elements;
    }

    public ArrayList<Machine> getMachines(){
        return machines;
    }
    public ArrayList<Sun> getSuns(){
        return suns;
    }

    public void addElement(int row , int column, Element element){
        elements[row][column] = element;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getNumberOfWinings() {
        return numberOfWinings;
    }

    public void setNumberOfWinings(int numberOfWinings) {
        this.numberOfWinings = numberOfWinings;
    }

    public int getNumberOfGameOvers() {
        return numberOfGameOvers;
    }

    public void setNumberOfGameOvers(int numberOfGameOvers) {
        this.numberOfGameOvers = numberOfGameOvers;
    }



}

