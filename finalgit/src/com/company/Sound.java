package com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
/**
 * This class holds informations of sound
 *
 * @author Melika & Morvarid
 * @since 2021
 */
public class Sound {
    private static String soundsPath = "sounds";
    private String soundName;
    AudioInputStream audioInputStream;
    Clip clip;

    /**
     * @param soundName name of sound
     * @throws LineUnavailableException
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public Sound(String soundName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this.soundName = soundName;
        audioInputStream = AudioSystem.getAudioInputStream(new File(soundsPath+"\\"+soundName).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    //playe sound
    public void play(){
        clip.start();
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(soundsPath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
