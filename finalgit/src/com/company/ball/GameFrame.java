package com.company.ball;
import com.company.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 730;                  // 720p game resolution
    public static final int GAME_WIDTH = 1005;  // wide aspect ratio

    //uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
	/*private BufferedImage image;*/
    private BufferedImage backGround;
    private BufferedImage sunflowerON;
    private BufferedImage sunflowerOff;
    private BufferedImage peaShooterOn;
    private BufferedImage peaShooterOff;
    private BufferedImage freezeShooterOn;
    private BufferedImage freezeShooterOff;
    private BufferedImage wallnutOn;
    private BufferedImage wallnutOff;
    private BufferedImage cherryOn;
    private BufferedImage cherryOff;

    private long lastRender;
    private ArrayList<Float> fpsHistory;

    private BufferStrategy bufferStrategy;
    CherryBomb cherryBomb;
    public GameFrame(String title) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super(title);
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);

	/*	try{
			image = ImageIO.read(new File("Icon.png"));
		}
		catch(IOException e){
			System.out.println(e);
		}*/
	putCards();
    }

    public void putCards(){
        try {
            backGround = ImageIO.read(new File("images\\mainBG.png"));
            sunflowerON = ImageIO.read(new File("images\\sunflowerBut.png"));
            sunflowerOff = ImageIO.read(new File("images\\sunflowerbutD.png"));

            peaShooterOn = ImageIO.read(new File("images\\peaB.png"));
            peaShooterOff = ImageIO.read(new File("images\\peaBd.png"));

            freezeShooterOn = ImageIO.read(new File("images\\icePeaB.png"));
            freezeShooterOff = ImageIO.read(new File("images\\SnowButD.png"));

            wallnutOn = ImageIO.read(new File("images\\wal.png"));
            wallnutOff = ImageIO.read(new File("images\\walD.png"));

            cherryOn = ImageIO.read(new File("images\\cherryBut.png"));
            cherryOff = ImageIO.read(new File("images\\cherryButD.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This must be called once after the JFrame is shown:
     *    frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) throws Exception {

		g2d.drawImage(backGround,null, 0, 27);
		//g2d.drawImage(image,500,500,null);
        g2d.drawImage(sunflowerON, null, 112, 37);

        if(  state.getLastPurchaseGiantWalnut() ==0 ||  state.getLastPurchasePeaShooter() ==0
                ||  state.getLastPurchaseFrozenPeaShooter() ==0||  state.getLastPurchaseCherryBomb() ==0
                ||  state.getLastPurchaseSunflower() ==0) {
            g2d.drawImage(sunflowerON, null, 112, 37);
            g2d.drawImage(wallnutOn, null, 202, 37);
            g2d.drawImage(peaShooterOn, null, 292, 37);
            g2d.drawImage(freezeShooterOn, null, 382, 37);
            g2d.drawImage(cherryOn, null, 472, 37);
        }
        if (state.getPlayersSuns() >= 50 &&
                ( System.currentTimeMillis() - state.getLastPurchaseSunflower() > 7500 )) {
            g2d.drawImage(sunflowerON, null, 112, 37);
        } else {
            g2d.drawImage(sunflowerOff, null, 112, 37);
        }

        if (state.getPlayersSuns() >= 50 &&
                ( System.currentTimeMillis() - state.getLastPurchaseGiantWalnut() > 30000 )) {
            g2d.drawImage(wallnutOn, null, 202, 37);
        } else {
            g2d.drawImage(wallnutOff, null, 202, 37);
        }

        if (state.getPlayersSuns() >= 100 &&
                ( System.currentTimeMillis() - state.getLastPurchasePeaShooter() > 7500 )) {
            g2d.drawImage(peaShooterOn, null, 292, 37);
        } else {
            g2d.drawImage(peaShooterOff, null, 292, 37);
        }
        if(state.getType() == GameState.gameType.NORMAL){
            if (state.getPlayersSuns() >= 175 &&
                    ( System.currentTimeMillis() - state.getLastPurchaseFrozenPeaShooter() > 7500 )) {
                g2d.drawImage(freezeShooterOn, null, 382, 37);
            } else {
                g2d.drawImage(freezeShooterOff, null, 382, 37);
            }

            if (state.getPlayersSuns() >= 150 &&
                    ( System.currentTimeMillis() - state.getLastPurchaseCherryBomb() > 30000 )) {
                g2d.drawImage(cherryOn, null, 472, 37);
            } else {
                g2d.drawImage(cherryOff, null, 472, 37);
            }
        }else if(state.getType() == GameState.gameType.HARD){
            if (state.getPlayersSuns() >= 175 &&
                    ( System.currentTimeMillis() - state.getLastPurchaseFrozenPeaShooter() > 30000 )) {
                g2d.drawImage(freezeShooterOn, null, 382, 37);
            } else {
                g2d.drawImage(freezeShooterOff, null, 382, 37);
            }

            if (state.getPlayersSuns() >= 150 &&
                    ( System.currentTimeMillis() - state.getLastPurchaseCherryBomb() > 45000 )) {
                g2d.drawImage(cherryOn, null, 472, 37);
            } else {
                g2d.drawImage(cherryOff, null, 472, 37);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (state.getElements()[i][j] != null && !(state.getElements()[i][j] instanceof CherryBomb) ) {
                    g2d.drawImage(state.getElements()[i][j].getImage(), (int)state.getElements()[i][j].getX(),
                            state.getElements()[i][j].getY() , null);

                    if (state.getElements()[i][j] instanceof Sunflower ) {
                        if(((Sunflower)state.getElements()[i][j]).getSun() != null){
                            g2d.drawImage(((Sunflower)state.getElements()[i][j]).getSun().getImg(),(int) state.getElements()[i][j].getX() + 20,
                                    state.getElements()[i][j].getY() + 80 , null);
                        }
                    }
                }
            }
        }

        for(Zombie z : state.getZombies()){
            g2d.drawImage(z.getImage(),(int) z.getX(), z.getY(), null);
        }
        for(Sun s : state.getSuns()){
            g2d.drawImage(s.getImg(),(int) s.getX(), s.getY(), null);
        }

        for(Machine m : state.getMachines()){
            g2d.drawImage(m.getImage(),(int) m.getX(), m.getY(), null);
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (state.getElements()[i][j] != null && state.getElements()[i][j] instanceof Pea) {
                    g2d.drawImage(((Pea)state.getElements()[i][j]).getpImage(),(int) state.getElements()[i][j].getX(), state.getElements()[i][j].getY(), null);
                    for (Bullet bullet :((Pea)state.getElements()[i][j]).getBullets()) {
                        g2d.drawImage(bullet.getbImage(),(int)  bullet.getX(), bullet.getY() ,null);
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (state.getElements()[i][j] != null && state.getElements()[i][j] instanceof CherryBomb) {
                    g2d.drawImage((state.getElements()[i][j]).getImage(),(int) state.getElements()[i][j].getX() - 100, state.getElements()[i][j].getY() - 30, null);

                }
            }
        }

        g2d.setFont(g2d.getFont().deriveFont(18.0f));
        g2d.setColor(Color.BLACK);
        String totalSun = Integer.toString(state.getPlayersSuns());
        int strWidth0 = g2d.getFontMetrics().stringWidth(totalSun);
        int strHeight = g2d.getFontMetrics().getHeight();
        g2d.drawString(totalSun, (20 + ((70 - strWidth0) / 2)), 110 + strHeight/2);


        if (state.gameOver) {
            String str = "GAME OVER";
            System.out.print("GAME OVER");
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
        }
        if (state.start) {
            this.setVisible(true);
            System.out.print("start");
           //88888888888888888888888888888888888888888888888888888888888888888888
        }
        if(state.endOfGame){
            String str = "END OF GAME .... YOU WON ;)";
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
        }
    }

}
