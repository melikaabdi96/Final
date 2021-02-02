package com.company.ball;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * shows ranking list when player presses the ranking button
 *
 * @author Melika - Morvarid
 * @since 2021
 */
public class RankingFrame {
    private JFrame rankingFrame;
    private JPanel rankingPanel;
    private GameState gameState;
    private JList<File> gameList;
    private JTabbedPane gameTabbedPane;

    public RankingFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

       /* JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gameTabbedPane.addTab("Tab ", textPanel);
        initGameList();*/
       /* rankingFrame.setBackground(Color.white);
        //sound = new Sound("game_end.wav");

        rankingPanel = new JPanel(new BorderLayout(10, 10));
        rankingPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        rankingPanel.setBackground(Color.black);

        JPanel rPannel = new JPanel(new GridLayout(2, 3, 5, 5));
        rPannel.setPreferredSize(new Dimension(100, 200));
        //f.setLocation(400,400);
        //JPanel newMenuPannel = new JPanel(new GridLayout(3,1, 5, 5));
        //newMenuPannel.setBorder(new EmptyBorder(10, 10, 10, 10));
        BufferedImage rankImg = ImageIO.read(new File("images\\images.jpg"));
        JLabel ranklable = new JLabel(new ImageIcon(rankImg));
        ranklable.setHorizontalAlignment(SwingConstants.CENTER);
        ranklable.setOpaque(true);

        JButton winButton = new JButton("Wins");
        winButton.setBackground(Color.yellow);

        JButton lossButton = new JButton("Losses");
        lossButton.setBackground(Color.lightGray);

        JButton typeButton = new JButton("Game Type");
        typeButton.setBackground(Color.orange);

        JTextField winField = new JTextField();
        winField.setEditable(false);

        JTextField lossField = new JTextField();
        lossField.setEditable(false);

        JTextField typeField = new JTextField();
        typeField.setEditable(false);
       /* //BufferedImage newImg = ImageIO.read(new File("number of wins"));
        JLabel winlable = new JLabel("number of wins");
        winlable.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel winlable = new JLabel("number of wins");
        winlable.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel winlable = new JLabel("number of wins");
        winlable.setHorizontalAlignment(SwingConstants.CENTER);*/


       /* rPannel.add(winButton);
        rPannel.add(lossButton);
        rPannel.add(typeButton);
        rPannel.add(typeButton);
        rPannel.add(winField);
        rPannel.add(lossField);
        rPannel.add(typeField);

        rankingPanel.add(ranklable, BorderLayout.NORTH);
        rankingPanel.add(rPannel, BorderLayout.CENTER);
        //settingPanel.add(levelPannel, BorderLayout.SOUTH);
*/
        rankingFrame = new JFrame();
        rankingPanel = new JPanel(new BorderLayout());
        //gameTabbedPane = new JTabbedPane();

        //rankingPanel.add(gameTabbedPane);

        // rankingFrame.add(gameTabbedPane, BorderLayout.CENTER);



        initTabbedPane();
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gameTabbedPane.addTab("Tab ", textPanel);
        initGameList();
        //initPage();





        rankingFrame.add(rankingPanel);
        rankingFrame.setSize(1000, 800);
        rankingFrame.setVisible(true);
        rankingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }

    /**
     * create a list of classes
     */
    private void initGameList(){
        File[] files = GameFile.getFilesInDirectory();
        gameList = new JList<>(files);
        gameList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.green, 5);
        gameList.setBorder(border);
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.setVisibleRowCount(-1);
        gameList.setMaximumSize(new Dimension(130, 100));
        gameList.setFixedCellWidth(130);
        //gameList.setCellRenderer(new MyCellRenderer());
        gameList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = gameList.locationToIndex(e.getPoint());
                    System.out.println("Item " + index + " is clicked...");
                    String content = GameFile.objectFileReader(files[index]).toString();
                    System.out.println(content);
                    openExistingClass(content);
                }
            }
        });
        rankingPanel.add(new JScrollPane(gameList), BorderLayout.WEST);
    }
    private void initTabbedPane() {
        gameTabbedPane = new JTabbedPane();
        rankingPanel.add(gameTabbedPane, BorderLayout.CENTER);
    }

    /**
     * @param content information of class
     */
    public void openExistingClass(String content) {
        JTextArea existPanel = new JTextArea();
        existPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        existPanel.setText(content);
        int tabIndex = gameTabbedPane.getTabCount() + 1;
        gameTabbedPane.addTab("player" + tabIndex, existPanel);
        gameTabbedPane.setSelectedIndex(tabIndex - 1);
    }
    public  GameState getState(){
        return gameState;
    }

}
