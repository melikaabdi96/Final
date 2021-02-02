package com.company.ball;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * shows setting options when player presses the setting button
 *
 *
 *
 * @author Melika - Morvarid
 * @since 2021
 */
public class SettingFrame {

    private JFrame settingFrame;
    private JPanel settingPanel;
    private  GameState gameState;

    public  SettingFrame(GameState gameState) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        settingFrame = new JFrame();
        settingFrame.setBackground(Color.white);
        //sound = new Sound("game_end.wav");

        settingPanel = new JPanel(new BorderLayout(10, 10));
        settingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        settingPanel.setBackground(Color.black);

        JPanel soundPannel = new JPanel(new GridLayout(1, 3, 5, 5));
        soundPannel.setPreferredSize(new Dimension(100, 200));
        //f.setLocation(400,400);
        //JPanel newMenuPannel = new JPanel(new GridLayout(3,1, 5, 5));
        //newMenuPannel.setBorder(new EmptyBorder(10, 10, 10, 10));
        BufferedImage settingImg = ImageIO.read(new File("images\\unnamed.png"));
        JLabel settingLable = new JLabel(new ImageIcon(settingImg));
        settingLable.setHorizontalAlignment(SwingConstants.CENTER);
        settingLable.setOpaque(true);

        BufferedImage newImg = ImageIO.read(new File("images\\mute.png"));
        JLabel souundlabel = new JLabel(new ImageIcon(newImg));
        souundlabel.setHorizontalAlignment(SwingConstants.CENTER);
        souundlabel.setOpaque(true);

        JButton muteButton = new JButton("Mute");
        muteButton.setBackground(Color.red);
        muteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    gameState.setMute(true);

                } catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        });

        JButton unmuteButton = new JButton("Unmute");
        unmuteButton.setBackground(Color.green);
        muteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    gameState.setMute(false);

                } catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        });

        soundPannel.add(souundlabel);
        soundPannel.add(muteButton);
        soundPannel.add(unmuteButton);

        JPanel levelPannel = new JPanel(new GridLayout(1, 3, 5, 5));
        levelPannel.setPreferredSize(new Dimension(100, 200));
        // newUserPannel.setBorder(new EmptyBorder(10, 10, 10, 10));

        BufferedImage levelImg = ImageIO.read(new File("images\\level.png"));
        JLabel levelLable = new JLabel(new ImageIcon(levelImg));
        //levelLable.setHorizontalAlignment(SwingConstants.CENTER);
        levelLable.setOpaque(true);

        JButton hardButton = new JButton("Hard");
        hardButton.setBackground(Color.red);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    gameState.setType(GameState.gameType.HARD);

                } catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        });

        JButton normalButton = new JButton("Normal");
        normalButton.setBackground(Color.green);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    gameState.setType(GameState.gameType.NORMAL);

                } catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        });

        levelPannel.add(levelLable);
        levelPannel.add(hardButton);
        levelPannel.add(normalButton);

        settingPanel.add(settingLable, BorderLayout.NORTH);
        settingPanel.add(soundPannel, BorderLayout.CENTER);
        settingPanel.add(levelPannel, BorderLayout.SOUTH);

        settingFrame.add(settingPanel);
        settingFrame.setSize(1000, 800);
        settingFrame.setVisible(true);
        settingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // return gameState;
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
