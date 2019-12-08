package util;

import java.applet.*;
import java.awt.Frame;
import java.io.File;
import java.net.*;

public class Music extends Frame{
	@SuppressWarnings("deprecation")
	public Music() {
		try {
			//URL soundFile = new URL("file:data/素颜。mp3");
			File f = new File("D:\\JavaProject\\Els\\data\\素颜。mp3");
			URL cb = f.toURL();
			AudioClip sound;
			sound = Applet.newAudioClip(cb);
			sound.play();
		} catch (MalformedURLException e) {
		}
	}
	
	
	public static void main(String args[]) {
		new Music();
	
		
	}
		
}
