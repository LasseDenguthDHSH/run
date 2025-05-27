package src.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip clip;
    private FloatControl volumeControl;

    public Music(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Lautstärkekontrolle
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setVolume(double level) {
        if (volumeControl != null) {
            double min = volumeControl.getMinimum();
            double max = volumeControl.getMaximum();
            double volume = min + (max - min) * level;
            volumeControl.setValue( (float)volume);
        }
    }
}
