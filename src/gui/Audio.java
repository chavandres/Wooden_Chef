package gui;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Audio {
AudioInputStream audioStream; 
    Clip clip;
    static String thePath = "Overcooked music.wav" ;
    public static void main(String[] args) {
     
    }
    public void sonido() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioStream = AudioSystem.getAudioInputStream(getClass().getResource(thePath));
        clip = AudioSystem.getClip();
        clip.open(audioStream); 
        clip.loop(Clip.LOOP_CONTINUOUSLY); 

    }
    
    
}
