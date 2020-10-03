package pong;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	private Clip effect;
	
	public void setFile(String fileName) {
		
		File file = new File(fileName);
		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			effect = AudioSystem.getClip();
			effect.open(sound);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void play() {
		effect.setFramePosition(0);
		effect.start();
	}
	
}
