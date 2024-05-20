//Author: John Ong
//Date May 20th 2024
//Description: The pupose of this class is to play sounds whenever it meets the conditions to play it. Such as the ball hitting a paddle.

package Guipart2.Pong;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

//Plays the sound effects in the game
public class Sfx {
    Sfx() {
    }
    //plays specific sound depending on what happens.
    public static void start(int sfxNum) {
        try {
            String num = Integer.toString(sfxNum);
            InputStream music = Sfx.class.getResourceAsStream("./sfx" + num + ".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
