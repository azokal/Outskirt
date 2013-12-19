package kr.ac.kmu.gameproject.outskirt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;

public class SoundManager {

	public static final String SOUND_FOLDER = "." + File.separator + "data"
			+ File.separator + "sound" + File.separator;
	private static final String BACKGROUND_FOLDER = SOUND_FOLDER + "background" + File.separator;
	private static final int NB_BACKGROUND = 4;
	
	private static final String SHOOT = SOUND_FOLDER + "shoot.wav";
	private static final String IMPACT = SOUND_FOLDER + "impact.wav";
	private static final String EXPLOSION = SOUND_FOLDER + "explosion.wav";

	private Minim minim;
	private AudioSample shoot;
	private AudioSample explosion;
	private AudioSample impact;
	private List<AudioPlayer> background = new ArrayList<AudioPlayer>();
	private int current;
	
	public SoundManager(App app) {
		minim = new Minim(app);
		shoot = minim.loadSample(SHOOT);
		explosion = minim.loadSample(EXPLOSION);
		impact = minim.loadSample(IMPACT);
		for (int i = 0; i < NB_BACKGROUND; i++) {
			background.add(minim.loadFile(BACKGROUND_FOLDER + "background" + i + ".mp3"));
		}
		background.get(0).play();
		current = 0;
	}

	public AudioPlayer getCurrent() {
		return background.get(current);
	}
	
	public void playNext() {
		if (!getCurrent().isPlaying()) {
			current = ++current % NB_BACKGROUND;
			getCurrent().play();
		}
	}
	
	/**
	 * @return the shoot
	 */
	public AudioSample getShoot() {
		return shoot;
	}

	/**
	 * @return the explosion
	 */
	public AudioSample getExplosion() {
		return explosion;
	}

	/**
	 * @return the impact
	 */
	public AudioSample getImpact() {
		return impact;
	}

}
