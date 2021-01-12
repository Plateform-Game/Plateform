package fr.julman.platform.player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import fr.julman.platform.Launcher;

public class Audio {
	public static synchronized void playSound(final String url, final int loop) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream ais;
					ais = AudioSystem.getAudioInputStream(Launcher.accessFile("sounds/" + url));
					clip.open(ais);
					clip.loop(loop);
					clip.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public static synchronized void stopAllSounds() {
		try {
			AudioSystem.getClip().stop();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
