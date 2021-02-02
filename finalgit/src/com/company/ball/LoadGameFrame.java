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
import java.util.ArrayList;

/**
 * loads games for the player whose name is typed in the textfield
 *
 * @author Melika - Morvarid
 * @since 2021
 */
public class LoadGameFrame {
    private JFrame f;
    private JPanel p;
    GameState gameState ;
    private JList<String> gameList;
    public LoadGameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        super();

        f = new JFrame();
        //sound = new Sound("game_end.wav");

        p = new JPanel(new BorderLayout(10,10));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));

        //f.setLocation(400,400);
        //JPanel newMenuPannel = new JPanel(new GridLayout(3,1, 5, 5));
        //newMenuPannel.setBorder(new EmptyBorder(10, 10, 10, 10));
        //BufferedImage newImg = ImageIO.read(new File("images\\index.png"));
        /*JLabel newGamelabel = new JLabel("Enter username");
        newGamelabel.setBackground(Color.green);
        newGamelabel.setHorizontalAlignment(SwingConstants.CENTER);
        newGamelabel.setOpaque(true);*/

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
        JButton newGameButton = new JButton("load!");
        newGameButton.setBackground(Color.red);
        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    DefaultListModel<String> model = new DefaultListModel<>();
                    GameModel gameModel = GameFile.returngame(name.getText());
                     ArrayList<GameState> mygames = new ArrayList<>();
                    mygames = gameModel.getGameStates();
                    gameList = new JList<>(model);
                    for (GameState gameState:mygames){
                        String g = String.valueOf(mygames.indexOf(gameState));
                        model.addElement(g);
                    }

                    //gameState = new GameState();
                    gameList.setBackground(Color.GRAY);
                    Border border = BorderFactory.createLineBorder(Color.green, 2);
                    gameList.setBorder(border);
                    gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    gameList.setVisibleRowCount(-1);
                    gameList.setMaximumSize(new Dimension(130, 100));
                    ArrayList<GameState> finalMygames = mygames;
                    gameList.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 2) {
                                int index = gameList.locationToIndex(e.getPoint());
                                System.out.println("Item " + index + " is clicked...");
                                GameState gameState = finalMygames.get(index);
                               // openExistingGame(content);
                            }
                        }
                    });


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


        p.add(newUserPannel, BorderLayout.NORTH);
        p.add(newGameButton, BorderLayout.SOUTH);
        p.add(new JScrollPane(gameList), BorderLayout.WEST);

        f.add(p);
        f.setSize(1000,800);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        /*adminTabbedPane.addTab("Tab ", textPanel);
        initStudentList();*/

       /* File[] files = GameFile.getFilesInDirectory();
        gameList = new JList<>(files);
        gameList.setBackground(Color.GRAY);
        Border border = BorderFactory.createLineBorder(Color.green, 2);
        gameList.setBorder(border);
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.setVisibleRowCount(-1);
        gameList.setMaximumSize(new Dimension(130, 100));
        //gameList.setFixedCellWidth(130);
        //gameList.setCellRenderer(new MyCellRenderer());
        gameList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = gameList.locationToIndex(e.getPoint());
                    System.out.println("Item " + index + " is clicked...");
                    GameModel content = GameFile.objectFileReader(files[index]);
                    openExistingGame(content);
                }
            }
        });*/

    }



    /**
     * @param content of game
     */
    public void openExistingGame(GameModel content) {

        //************************************************************************************************************
    }
    public  GameState getState(){
        return gameState;
    }
}
