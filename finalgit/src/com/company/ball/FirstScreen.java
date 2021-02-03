package com.company.ball;

import com.company.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
/**
 * shows main menu when the program runs
 *
 * @author Melika - Morvarid
 * @since 2021
 */

public class FirstScreen {
    private JFrame f;
    private GameState gameState = new GameState();
    SettingFrame settingFrame;
    JButton startButton;
    private Sound sound;
    public FirstScreen() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        f = new JFrame();
        sound = new Sound("menu.wav");
        f.setTitle("Main Menu");
        //f.setContentPane(new JLabel(new ImageIcon("images\\first_screen.jpg")));
        //f.setLayout(new FlowLayout());
        //f.setLayout(new BorderLayout());

        JPanel menuPannel = new JPanel();

        menuPannel.setLayout(new BorderLayout(10,10));
        menuPannel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel(new ImageIcon("images\\first_screen.jpg"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);

        JPanel userPanel = new JPanel(new GridLayout(1,5, 5, 5));
        userPanel.setPreferredSize(new Dimension(100, 200));

        JButton newButton = new JButton("New Game");
        newButton .setBackground(Color.green);
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                   // f.dispose();
                    NewGameFrame newGameFrame = new NewGameFrame();
                    gameState = newGameFrame.getState();

                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });

        JButton loadButton = new JButton("Load Game");
        loadButton.setBackground(Color.green);
        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // f.dispose();
                    LoadGameFrame loadGameFrame = new LoadGameFrame();
                    gameState = loadGameFrame.getState();

                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
            }
        });

        JButton rankButton = new JButton("Ranking");
        rankButton.setBackground(Color.green);
        rankButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    //  f.dispose();
                    RankingFrame rankingFrame = new RankingFrame();

                } catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        });


        JButton settingButton = new JButton("Setting");
        settingButton.setBackground(Color.green);
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // f.dispose();
                    settingFrame = new SettingFrame(gameState);
                    gameState = settingFrame.getState();
                    System.out.print(gameState.getType() +","+ gameState.isMute());

                } catch (Exception exp){
                    exp.printStackTrace();
                }
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setBackground(Color.green);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    System.exit(0);
                }catch (Exception exp){
                    exp.printStackTrace();
                }

            }
        });

        JButton playButton = new JButton("Go");

        playButton.setBackground(Color.green);
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    ThreadPool.init();
                    EventQueue.invokeLater(() -> {
                        GameFrame frame = null;
                        try {
                            gameState = settingFrame.getState();
                            System.out.print(gameState.getType() +","+ gameState.isMute());
                            frame = new GameFrame("Plants vs. Zombies");
                        } catch (UnsupportedAudioFileException e3) {
                            e3.printStackTrace();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        } catch (LineUnavailableException e2) {
                            e2.printStackTrace();
                        }
                        frame.setLocationRelativeTo(null); // put frame at center of screen
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                        frame.initBufferStrategy();
                        // Create and execute the game-loop
                        GameLoop game = new GameLoop(frame , gameState);
                        try {
                            game.init();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        ThreadPool.execute(game);
                        // and the game starts ...
                    });
                }catch (Exception exp){
                    exp.printStackTrace();
                }

            }
        });
        /*try {
            BufferedImage img = ImageIO.read(new File("images\\NEW GAME.png"));
            newButton.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }*/
        userPanel.add(newButton);
        userPanel.add(loadButton);
        userPanel.add(rankButton);
        userPanel.add(settingButton);
        userPanel.add(quitButton);
        userPanel.add(playButton);

        menuPannel.add(label, BorderLayout.NORTH);
        menuPannel.add(userPanel, BorderLayout.CENTER);

        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem loadGame = new JMenuItem("Load Game");
        JMenuItem ranking = new JMenuItem("Ranking"); //8888888888888888888888888888888888888888888888
        JMenuItem setting = new JMenuItem("Setting");
        JMenuItem exitItem = new JMenuItem("Quit");
        fileMenu.setBounds(0, 0, 100, 150);
        fileMenu.add(newGame);
        fileMenu.add(loadGame);
        fileMenu.add(ranking);
        fileMenu.add(setting);
        fileMenu.add(exitItem);
        mb.add(fileMenu);
       /* newGame.addActionListener(e -> {
            try {
                new NewGameFrame(gameState);
//                    NewGameFrame newGameFrame = new NewGameFrame(gameState);
//                    this.gameState = newGameFrame.getState();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        });*/
        /*loadGame.addActionListener(e -> {
            LoadGameFrame loadGameFrame = null;
            try {
                loadGameFrame = new LoadGameFrame();
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
            this.gameState = loadGameFrame.getState();
        });*/
       /* setting.addActionListener(e -> {
            SettingFrame settingFrame = null;
            try {
                settingFrame = new SettingFrame(gameState);
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
            this.gameState = settingFrame.getState();

        });*/
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
        exitItem.addActionListener(e -> {
            f.dispose();
            // System.exit(0);//8888888888888888888888888888888888888888888888888888888888888888888 save game
        });
        f.add(menuPannel);
        f.setJMenuBar(mb);
        f.setSize(1000, 800);
        //f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //-------------------------------------------------------------------------------------------------------------------------------
        //f.setFont(new Font("Arial", Font.PLAIN, 18));
    }

    /*public GameState getGameState() {
        return gameState;
    }*/

    /*public  void setGameState(GameState gameState) {
        this.gameState = gameState;
    }*/
}

