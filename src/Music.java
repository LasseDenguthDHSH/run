package src;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip clip;

    public Music(String filePath) {
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
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Wiederholt die Musik
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}