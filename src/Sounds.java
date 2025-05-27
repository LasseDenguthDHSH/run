package src;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    private Clip clip;

    public Sounds(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
            clip.setFramePosition(0);
        }
    }

}

