package com.company.ball;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

//        EventQueue.invokeLater(() -> {
//            GameFrame frame = null;
//            try {
//                frame = new GameFrame(" Plants vs Zombies ");
//            } catch (UnsupportedAudioFileException | IOException e) {
//                e.printStackTrace();
//            } catch (LineUnavailableException e) {
//                e.printStackTrace();
//            }
//            frame.setLocationRelativeTo(null); // put frame at center of screen
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setVisible(true);
//            frame.initBufferStrategy();
//            // Create and execute the game-loop
//            GameLoop game = new GameLoop(frame);
//            game.init();
//            ThreadPool.execute(game);
//            // and the game starts ...
//        });

        ThreadPool.init();
//        new FirstScreen();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = null;
                try {
                    frame = new GameFrame("Plants vs. Zombies");
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame);
                try {
                    game.init();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });

    }
}
