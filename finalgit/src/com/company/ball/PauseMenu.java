package com.company.ball;

import com.company.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
/**
 * shows pause menu when player presses the pause button
 *
 * @author Melika - Morvarid
 * @since 2021
 */
public class PauseMenu {
    private JFrame puaseFrame;
    Sound sound;
    GameState gameState;

    public PauseMenu() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        gameState = new GameState();
        puaseFrame = new JFrame();
        //sound = new Sound("menu.wav");
        puaseFrame.setTitle("Pause Menu");
        //f.setContentPane(new JLabel(new ImageIcon("images\\first_screen.jpg")));

        JPanel menuPannel = new JPanel();

        menuPannel.setLayout(new BorderLayout(10, 10));
        menuPannel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel(new ImageIcon("images\\first_screen.jpg"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);

        JPanel userPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        userPanel.setPreferredSize(new Dimension(100, 200));

        JButton resumeButton = new JButton("Resume Game");
        resumeButton.setBackground(Color.green);
        resumeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    gameState.setStopManu(false);
                    gameState.setMute(false);
                    puaseFrame.dispose();
                    //gameState.setStopManu(false);  //************************************************* edit it
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

        JButton saveButton = new JButton("save Game");
        saveButton.setBackground(Color.green);
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String playerName = gameState.getPlayerName();
                    GameModel gameModel = new GameModel(playerName);
                    GameFile.objectFileWriter(gameModel);
                } catch (Exception ex){
                    ex.printStackTrace();}
            }
        });

        JButton exitButton = new JButton("Exit to Main Menu");
        exitButton.setBackground(Color.green);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    FirstScreen firstScreen = new FirstScreen();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                puaseFrame.dispose();
            }
        });


        /*try {
            BufferedImage img = ImageIO.read(new File("images\\NEW GAME.png"));
            newButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }*/
        userPanel.add(resumeButton);
        userPanel.add(saveButton);
        userPanel.add(exitButton);


        menuPannel.add(label, BorderLayout.NORTH);
        menuPannel.add(userPanel, BorderLayout.CENTER);

        puaseFrame.add(menuPannel);
        //f.setJMenuBar(mb);
        puaseFrame.setSize(1000, 800);
        //f.setLayout(null);
        puaseFrame.setVisible(true);
        puaseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameState = gameState;
        setState(gameState);

    }
    public  GameState getState(){
        return gameState;
    }
    public  void setState(GameState gameState){
        this.gameState = gameState;
    }
}
