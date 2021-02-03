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
import java.util.Objects;

import static sun.swing.MenuItemLayoutHelper.max;

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
    private ArrayList<Machine> machines = new ArrayList<>();
    private ArrayList<Sun> suns = new ArrayList<>();
    private ArrayList<CherryBomb> cherryBombs = new ArrayList<>();
    private ArrayList<Pea> peas = new ArrayList<>();
    private  int waveNum ;
    private String selectedCard ;
    private  long gameStartTime;
    private  long lastPurchaseSunflower = 0;
    private  long lastPurchasePeaShooter = 0;
    private  long lastPurchaseGiantWalnut = 0;
    private  long lastPurchaseCherryBomb = 0 ;
    private  long lastPurchaseFrozenPeaShooter = 0;
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
        type = gameType.NORMAL;  // defualt type is NORMAL
        //
        mouseHandler = new MouseHandler();
        playerName = "test";
        playersSuns = 550;
        gameStartTime = System.currentTimeMillis();
        setMachines();
    }
    /**
     * The method which updates the game state.
     */
    public void update() throws Exception {

        checkGameOver();
        checkFinish();
        updateZombieInRow();
        if(!stopManu){
            produseFallingSun();
            moveBullets();
            moveSuns();
            moveZombies();
            moveMachines();
            SetGif();
            handleShoots();
            catchFallingSuns();
            stopOrNotStopAll(false);
            produceZombies();
            shootPea();
            attack();
            handleplants();
            removeMachine();
            removeDeadZombies();
            removeDeadPlants();
            handlePurchases();
            explosion();

            mouseX = 0;
            mouseY = 0;
        }else {
            stopOrNotStopAll(true);
            removeMachine();
            removeDeadZombies();
            removeDeadPlants();
        }
        numberOfFrame++;
        //setResultOfGame();
       // addToFile();
    }
    private void SetGif(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (elements[i][j] != null) {
                    if (elements[i][j] instanceof GiantWalNut) {
                        ((GiantWalNut)elements[i][j]).setGif();

                    }
                    if (elements[i][j] instanceof Pea) {
                        ((Pea)elements[i][j]).setDyingpea();

                    }
                    if (elements[i][j] instanceof Sunflower) {
                        ((Sunflower)elements[i][j]).setGif();

                    }
                }
            }
        }

    }
    private void setMachines() throws IOException {
        for(int i = 0 ; i < 5 ; i++){
            machines.add(new Machine(i));
        }
    }

    public  void  checkGameOver(){
        for(Zombie z : zombies){
            if(z.arrivesHome() == true && !machineInRow[z.getRow()]){
                gameOver = true;
            }else if(z.arrivesHome() == true && machineInRow[z.getRow()]){
                machineInRow[z.getRow()] = false;
                for(Zombie zombie : zombies){
                    if(zombie.getRow() == z.getRow())zombie.setAlive(false);
                }
                z.setAlive(false);
                for(Machine m : machines){
                    if(m.getRow() == z.getRow()+1){
                        m.setReadyToMove(true);
                    }
                }
                machineInRow[z.getRow()] = false;
            }
        }
    }

    public void checkFinish(){
        if (numberOfFrame >= 14400 && !gameOver) { // 14400 = 8(min)* 60(sec) * 30(fps)
//                removeAllBullet();
//                removeAllZombies();
//                removeAllPlants();
            endOfGame = true;
        }
    }

    private void updateZombieInRow(){
        int[] zombieInRowCounter = new int[5] ;
        for (int i = 0; i < 5; i++) {
            zombieInRowCounter[i] = 0;
        }

        for(Zombie z : zombies ){
            for(int i = 0 ; i <5 ; i++){
                if(i == z.getRow()){
                    zombieInRowCounter[i]++;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            if(zombieInRowCounter[i] == 0){
                zombieInRow[i] = false;
            }else  zombieInRow[i] = true;
        }
    }

    public void moveBullets(){
        for(Pea pea : peas){
            for(Bullet bullet : pea.getBullets()){
                bullet.moveRight(4);
            }
        }
    }
    public void removeDeadZombies(){
        Iterator itr = zombies.iterator();
        while (itr.hasNext()) {
            Zombie z = (Zombie) itr.next();
            if (!z.isAlive() || z.getHealth() <= 5) {
                itr.remove();
            }
            if(z.getHealth() <= 10)z.setDyingGif();
    }
    }
    private void attack(){
        Iterator<Zombie>  itr = zombies.iterator();
        while (itr.hasNext()) {
            Zombie z =  itr.next();
            for(Pea p : peas){
                Iterator<Bullet> bullIter = p.getBullets().iterator();

                while (bullIter.hasNext()) {
                    Bullet b = bullIter.next();
                    if ( b.getRow() == z.getRow() + 1 && b.getY() == z.getY() + 4 && !b.isCollided() && Math.pow(((int) b.getX()-(int) z.getX()) , 2) < 900 ) {

                        z.setHealth(z.getHealth() - b.getDamage());
                        b.setCollided(true);
                        if(b.getType() == Bullet.bulletType.FROZEN && z.getFirstFrozenBulletShot()){
                            z.setSpeed(z.getSpeed()/2);
                            z.setFirstFrozenBulletShot(false);
                        }

                        if (z.getHealth() == 0) {
                            z.setAlive(false);
                            itr.remove();
                        }

                    }
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (elements[i][j] != null) {

                        if(elements[i][j].getRow() == z.getRow() + 1 && Math.pow(((int) elements[i][j].getX()-(int) z.getX()) , 2) < 2500 ){
                           // System.out.println(";)");
                            z.setStoped(true);
                            z.setAttackTime(System.currentTimeMillis());
                            if(numberOfFrame % 30 == 0 ) { // each sec = 30 frame
                                if ( elements[i][j].getHealth() >= 5 ) {
                                    int newH = elements[i][j].getHealth() - z.getAttack();
                                    elements[i][j].setHealth(newH);
                                }
                            }
                        }
                        if (elements[i][j].getHealth() < 5 ) {
                            z.setStoped(false);
                            Iterator<Pea> Iter = peas.iterator();
                            while (Iter.hasNext()) {
                                Pea  p = Iter.next();
                                if(p.getX() == elements[i][j].getX() && p.getY() == elements[i][j].getY() ){
                                    Iter.remove();
                                }
                            }
                            elements[i][j] = null;
                        }

                    }
                }
            }

        }
    }

    public void handleplants() throws IOException {
        long producingSunPeriod = ( type == gameType.NORMAL? 2000 : 2500);// 20 * 30 = 600 , 25 * 30 = 750
        long lastingTimeForSun = 10000;// suppose = 10 sec
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (elements[i][j] != null) {
                    if (elements[i][j] instanceof Sunflower) {
                        if( ((Sunflower)elements[i][j]).getSun() == null){
                            if ((System.currentTimeMillis() - elements[i][j].getStartTime()) %  producingSunPeriod == 0) {

                                    ((Sunflower)elements[i][j]).setSun(new Sun((int)elements[i][j].getX() + 20, elements[i][j].getY() + 40));
                                    elements[i][j].setStartTime(System.currentTimeMillis());
                            }
                        }else {
                            if( ((Sunflower)elements[i][j]).getSun().getStartTime() - System.currentTimeMillis() > lastingTimeForSun){
                                ((Sunflower)elements[i][j]).setSun(null);
                            }

                            if (Math.pow(mouseX - (((Sunflower)elements[i][j]).getSun().getX() ), 2)
                                    + Math.pow(mouseY - (((Sunflower)elements[i][j]).getSun().getY() ), 2) < 8100) {
                                playersSuns += ((Sunflower)elements[i][j]).getSun().getValue();
                                ((Sunflower)elements[i][j]).setSun(null);
                            }
                        }
                    }
                }
            }
        }
        for(Pea p : peas){
            if(zombieInRow[max(p.getRow()-1 ,0)]){
                p.setReadyToShoot(true);
            }else p.setReadyToShoot(false);
        }

    }

    private void  handleShoots(){

        for(Pea p : peas){
            if(p.isReadyToShoot()){
                Iterator<Bullet> Iter = p.getBullets().iterator();
                while (Iter.hasNext()) {
                    Bullet b = Iter.next();
                    b.moveRight(4);
                    if (b.endOfFeild()) {
                        Iter.remove();
                    }
                    if(b.isCollided())Iter.remove();
                }
            }
        }

    }


    private void produseFallingSun() throws IOException {
        long produsePeriod = (type == gameType.NORMAL ? 250 : 300);
        int random = new SecureRandom().nextInt(990) + 10 ;
        if((System.currentTimeMillis() - gameStartTime) % produsePeriod == 0 ){
            suns.add(new Sun(random , 130));
        }
    }

    private void moveSuns(){
        for(Sun s : suns){
            s.movedown(3);
        }
    }
    private void moveMachines(){
        for(Machine m : machines){
            if(m.isReadyToMove())m.moveRight(4);
        }
    }
    private void moveZombies(){
        Iterator itr = zombies.iterator();
        while (itr.hasNext()) {
            Zombie z = (Zombie) itr.next();
            z.moveLeft(z.getSpeed());
        }
    }

    private void catchFallingSuns(){

        Iterator<Sun> sIter =suns.iterator();
        while (sIter.hasNext()) {
            Sun s = sIter.next();
            if (Math.pow(mouseX - (s.getX()), 2) + Math.pow(mouseY - (s.getY()), 2) < 8100) {
                playersSuns += 25;
                sIter.remove();
            }
        }
    }

    public  void  randomZombie() throws Exception {
        int random = new SecureRandom().nextInt(4);
        if(random == 0) {
            Zombie z = new Zombie(Zombie.zombiType.NORMAL,type);
            zombieInRow[z.getRow()] = true;
            zombies.add(z);

        }else  if(random == 1) {
            Zombie z = new Zombie(Zombie.zombiType.CONEHEAD , type);
            zombies.add(z);
            zombieInRow[z.getRow()] = true;
        }else  if(random == 2) {
            Zombie z = new Zombie(Zombie.zombiType.BUCKHEAD , type);
            zombies.add(z);
            zombieInRow[z.getRow()] = true;
        }else  if(random == 3) {
            Zombie z = new Zombie(Zombie.zombiType.FOOTBALL , type);
            zombies.add(z);
            zombieInRow[z.getRow()] = true;
        }
    }

    public void produceZombies() throws Exception {// 8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888 timing
        if(numberOfFrame >= 150 && numberOfFrame < 450 ){ // 1500 = 50 (sec) * 30(fps) ----------2.5(min) = 150(sec) -> 4500 = 150 (sec) * 30(fps)
            if(numberOfFrame % 90 == 0) {// 30(sec) * 30 (fps)
                randomZombie();
            }

        }else if(numberOfFrame >= 450 && numberOfFrame < 990 ) {// 5.5(min) = 330(sec) -> 9900 = 330 (sec) * 30(fps)
            if(numberOfFrame % 90 == 0) {
                randomZombie();
                randomZombie();
            }

        }else if(numberOfFrame >=  9900 && numberOfFrame < 14400 ) {// 8(min) = 480(sec) -> 14400 = 480 (sec) * 30(fp
            if(numberOfFrame % 750 == 0) {// 25(sec) * 30 (fps)
                randomZombie();
                randomZombie();
            }

        }

        for(Zombie z : zombies){
            z.setZombieGif();
        }
    }

    private void removeMachine(){
        Iterator<Machine> Iter0 = machines.iterator();
        while (Iter0.hasNext()) {
            Machine m = Iter0.next();
            if (m.endOfFeild()) {
                Iter0.remove();
            }
        }
    }

    private void shootPea() throws Exception {
        for(Pea p : peas){
            if(zombieInRow[max(p.getRow()-1 ,0)]){
                if (numberOfFrame % 30 == 0 ) {
                    p.shoot();
                }
            }else {
                Iterator<Bullet> bullIter = p.getBullets().iterator();
                while (bullIter.hasNext()) {
                    Bullet b = bullIter.next();
                    bullIter.remove();
                }
            }
        }
    }

    private void removeDeadPlants(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (elements[i][j] != null) {
                    if (elements[i][j].getHealth() == 0) {
                        elements[i][j] = null;
                    }
                }
            }
        }
    }

    private void handlePurchases() throws IOException {

        if(toPlace){
            locX = ( mouseX - 40 )/ 100;
            locY = ( mouseY - 130 )/ 130;
            locX = Math.max(locX, 0);
            locX = Math.min(locX, 8);
            locY = Math.max(locY, 0);
            locY = Math.min(locY, 4);

            if(selectedCard.equals("sunflower")){

                Sunflower sunflower = new Sunflower(40 + locX * 100 , 130  + locY * 120);
                elements[locY][locX] = sunflower;
                playersSuns -= sunflower.getCost();
                selectedCard = "-";

            }else if(selectedCard.equals("giantWallNut")){
                GiantWalNut giantWalNut =  new GiantWalNut(40 + locX * 100 , 130  + locY * 120);
                elements[locY][locX]  = giantWalNut;
                playersSuns -= giantWalNut.getCost();
                selectedCard = "-";

            }else if(selectedCard.equals("peaShooter")){
                Pea peashooter = new Pea(40 + locX * 100 , 130  + locY * 120 , Pea.peaType.PEA);
                elements[locY][locX] = peashooter;
                peas.add(peashooter);
                playersSuns -= peashooter.getCost();
                selectedCard = "-";

            }else if(selectedCard.equals("snowPea")){
                Pea snowPea = new Pea(40 + locX * 100 , 130  + locY * 120 , Pea.peaType.FREEZEPEA);
                elements[locY][locX] = snowPea;
                peas.add(snowPea);
                playersSuns -= snowPea.getCost();
                selectedCard = "-";

            }else if(selectedCard.equals("cherryBomb")){
                CherryBomb cherryBomb = new CherryBomb(40 + locX * 100 , 130  + locY * 120 , numberOfFrame );
                elements[locY][locX] = cherryBomb;
                cherryBombs.add(cherryBomb);
                playersSuns -= cherryBomb.getCost();
                selectedCard = "-";

            }
            elements[locY][locX].setRow(locY);
            elements[locY][locX].setColumn(locX);
            // selectedCard = null;
            toPlace = false;

        }
    }


    /*private void setResultOfGame(){
        if(gameOver){
            numberOfGameOvers +=1;
            scores -=(type == gameType.NORMAL ? 1 : 3);
        }
        else if(endOfGame){
            numberOfWinings +=1;
            scores +=(type == gameType.NORMAL ? 3 : 10);
        }

    }*/

    private void explosion(){
        Iterator<CherryBomb> cIter = cherryBombs.iterator();
        while (cIter.hasNext()) {
            CherryBomb c = cIter.next();
             if ( numberOfFrame - c.getNumberOfFrame() == 30) {
                for(Zombie z : zombies){
                    if (( Math.pow(z.getX() - c.getX(),2 )+ Math.pow(z.getY() - c.getY(),2)) < 40000 ) {
                        z.setAlive(false);
                    }
                }
                elements[c.getRow()][c.getColumn()] = null;
                cIter.remove();
            }

        }

    }

    public void stopOrNotStopAll(boolean tf){
        // when tf is true all of them are stoped and vice versa when tf is false
        for(Zombie z : zombies){
            z.setStoped(tf);
        }
        for(Machine m : machines){
            m.setStoped(tf);
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (elements[i][j] != null  && elements[i][j] instanceof Pea){
                        Iterator<Bullet> Iter =((Pea)elements[i][j]).getBullets().iterator();
                        while (Iter.hasNext()) {
                            Bullet b = Iter.next();
                            b.setStoped(tf);
                        }
                }
            }
        }
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
    class MouseHandler extends MouseAdapter implements Serializable {
        @Override
        public void mouseClicked(MouseEvent e) {

            mouseX = e.getX();
            mouseY = e.getY();
            if(mouseY > 35 && mouseY < 130){
                if(mouseX > 110 && mouseX < 190){

                    selectedCard = "sunflower";
                    lastPurchaseSunflower = System.currentTimeMillis();
                }else if(mouseX > 200 && mouseX < 280){
                    selectedCard = "giantWallNut";
                    lastPurchaseGiantWalnut = System.currentTimeMillis();
                }else if(mouseX > 290 && mouseX < 370){
                    selectedCard = "peaShooter";
                    lastPurchasePeaShooter = System.currentTimeMillis();
                }else if(mouseX > 380 && mouseX < 460){
                    selectedCard = "snowPea";
                    lastPurchaseFrozenPeaShooter = System.currentTimeMillis();
                }else if(mouseX > 470 && mouseX < 550){
                    selectedCard = "cherryBomb";
                    lastPurchaseCherryBomb = System.currentTimeMillis();
                }

            }
            if(mouseY > 27 && mouseY < 56){
                if(mouseX > 860 && mouseX < 1000){

                    if(stopManu)stopManu=false;
                    else {
                        stopManu = true;
                        mute = true;
                    }
                }
            }

            if (! selectedCard.contains("-")) {
                if (mouseX> 40 && mouseX < 940 && mouseY > 130 && mouseY < 725) {
                    toPlace = true;
                }

            }
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
    /*public void addToFile(){
        try(FileOutputStream fileOutputStream = new FileOutputStream("games" + File.separator+this.playerName)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public ArrayList<Zombie> getZombies(){
        return zombies;
    }
    public ArrayList<Pea> getPeas(){
        return peas;
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
        // delete previous version
        File[] list = new File("games").listFiles();
        assert list != null;
        for(File file : list){
            if(Objects.equals(file.getName(), playerName)){
                try {
                    file.delete();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        this.playerName = playerName;
       // addToFile();
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

   /* public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }*/

   /* public int getNumberOfWinings() {
        return numberOfWinings;
    }*/

   /* public void setNumberOfWinings(int numberOfWinings) {
        this.numberOfWinings = numberOfWinings;
    }*/

   /* public int getNumberOfGameOvers() {
        return numberOfGameOvers;
    }*/

  /*  public void setNumberOfGameOvers(int numberOfGameOvers) {
        this.numberOfGameOvers = numberOfGameOvers;
    }*/

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }

    public void setStopManu(boolean stopManu){
        this.stopManu = stopManu;
    }
}

