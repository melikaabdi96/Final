package com.company.ball;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
/**
 * This class holds flie handling methods
 *
 * @author Melika - Morvarid
 * @since 2021
 */
public class GameFile {
    private static final String GAME_PATH = "./games/";

    //It's a static initializer. It's executed when the class is loaded.
    static {
        boolean isSuccessful = new File(GAME_PATH).mkdirs();
        System.out.println("Creating " + GAME_PATH + " directory is successful: " + isSuccessful);

    }

    public static void deleteGame(String gameName){
        try {
            Path file = Paths.get(GAME_PATH + gameName);
            Files.deleteIfExists(file);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * @return list of files
     */
    public static File[] getFilesInDirectory() {
        return new File(GAME_PATH).listFiles();
    }

    public static void objectFileWriter(GameModel gameModel){
        String fileName = gameModel.getUserName();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(gameModel);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameModel objectFileReader(File file){
        GameModel gameModel = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            gameModel = (GameModel) objectInputStream.readObject();
            objectInputStream.close();
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameModel;
    }

    public static GameModel returngame(String gameName){
        try(FileInputStream fileInputStream = new FileInputStream(gameName)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (GameModel) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
