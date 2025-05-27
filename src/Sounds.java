package src;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    private Clip clip;
    private FloatControl volumeControl;

    public Sounds(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            //Lautstärkekontrolle
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
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
    public void setVolume(double level) {
        if (volumeControl != null) {
            double min = volumeControl.getMinimum();
            double max = volumeControl.getMaximum();
            double volume = min + (max - min) * level;
            volumeControl.setValue((float)volume);
        }
    }

}

