package kr.ac.kmu.gameproject.outskirt;
import java.io.File;

import ddf.minim.AudioSample;
import ddf.minim.Minim;


public class SoundManager {
	
	public static final String SOUND_FOLDER = "." + File.separator + "data" + File.separator + "sound" + File.separator;
	
	private static final String SHOOT = SOUND_FOLDER + "shoot.wav";
	private static final String IMPACT = SOUND_FOLDER + "impact.wav";
	private static final String EXPLOSION = SOUND_FOLDER + "explosion.wav";
	
	private Minim minim;	
	private AudioSample shoot;
	private AudioSample explosion;
	private AudioSample impact;
	
	public SoundManager(App app) {
		minim = new Minim(app);
		shoot = minim.loadSample(SHOOT);
		explosion = minim.loadSample(EXPLOSION);
		impact = minim.loadSample(IMPACT);
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
