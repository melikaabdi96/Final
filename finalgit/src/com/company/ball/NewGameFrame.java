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
import java.util.Arrays;
import java.util.List;

public class NewGameFrame  {
    GameState gameState;
    private JFrame newGameFrame;
    private JPanel newGamePannel;
    //private Sound sound;
    public NewGameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //gameState = gameState1;
        newGameFrame = new JFrame();
        //sound = new Sound("game_end.wav");

        newGamePannel = new JPanel(new BorderLayout(10,10));
        newGamePannel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //f.setLocation(400,400);
        //JPanel newMenuPannel = new JPanel(new GridLayout(3,1, 5, 5));
        //newMenuPannel.setBorder(new EmptyBorder(10, 10, 10, 10));
        BufferedImage newImg = ImageIO.read(new File("images\\index.png"));
        JLabel newGamelabel = new JLabel(new ImageIcon(newImg));
        newGamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        newGamelabel.setOpaque(true);

        JPanel newUserPannel = new JPanel(new GridLayout(1,2, 5, 5));
        newUserPannel.setPreferredSize(new Dimension(100, 200));
        // newUserPannel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel nameL = new JLabel("Please enter your name : ");
        nameL.setBackground(Color.YELLOW);
        newUserPannel.add(nameL);// player ba username password

        JTextField name = new JTextField();
        name.setEditable(true);
        newUserPannel.add(name);

        //BufferedImage startImg = ImageIO.read(new File("images\\startbutton.png"));
        JButton newGameButton = new JButton("start!");
        newGameButton.setBackground(Color.red);
        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    GameModel gameModel = new GameModel(name.getText());
                    gameState = new GameState();
                    gameModel.addGame(gameState);
                    gameState.setPlayerName(name.getText());
                    GameFile.objectFileWriter(gameModel);

                   /* File f = new File("games");
                    List<String> list = Arrays.asList( f.list());

                    if(! list.contains(name.getText())){
                        GameModel gameModel = new GameModel(name.getText());
                        gameState = new GameState();
                        gameModel.addGame(gameState);
                        gameState.setPlayerName(name.getText());
                        GameFile.objectFileWriter(gameModel);
                    } else {
                        JOptionPane.showMessageDialog(newGameFrame, "A user with this username is available!", "Error", JOptionPane.ERROR_MESSAGE);
                    }*/
                } catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        });





        newGamePannel.add(newGamelabel, BorderLayout.NORTH);
        newGamePannel.add(newUserPannel, BorderLayout.CENTER);
        newGamePannel.add(newGameButton, BorderLayout.SOUTH);

        newGameFrame.add(newGamePannel);
        newGameFrame.setSize(400,400);
        newGameFrame.setVisible(true);
        newGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        newGameButton.addActionListener(e -> {
//            gameState.setPlayerName(name.getText());
//
//        });

    }
    public  GameState getState(){
        return gameState;
    }
}

