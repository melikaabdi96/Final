package com.company.ball;

import com.company.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class FirstScreen1 {
    JFrame f;
    JButton startButton;
    JLabel nameL;
    JTextField name;
    Sound sound;
    public FirstScreen1() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        f  = new JFrame();
        f.setVisible(true);
        sound = new Sound("background.wav");
        f.setTitle("First Screen");
        nameL = new JLabel("Please enter your name : ");f.add(nameL);// player ba username password
        name = new JTextField();f.add(name);
        startButton = new JButton("Let's play ");f.add(startButton);
        f.setLayout(new BorderLayout());
        //f.setContentPane(new JLabel(new ImageIcon("first_screen.jpg")));
        //f.setLayout(new FlowLayout());
        startButton.addActionListener(e -> {
            //88888888888888888888888888888888888888888888888
            f.setVisible(false);
        });
    }

}