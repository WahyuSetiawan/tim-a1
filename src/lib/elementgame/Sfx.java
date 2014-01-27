package lib.elementgame;

import java.io.IOException;

import lib.defines.GameEngineConfiguration;
import lib.defines.SfxDefines;
import lib.engine.GameEngine;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;

public class Sfx implements SfxDefines
{
	GameEngine mainActivity;
	static Sound player[] = new Sound[CONTAINER.length];
	static boolean paused;
	
	public static void loadAllSfx(GameEngine mainActivity)
	{
		paused = false;
		for(int i = 0; i < player.length; i++)
		{
			try {
				player[i] = SoundFactory.createSoundFromAsset(mainActivity.getSoundManager(), mainActivity, CONTAINER[i].getPath());
				player[i].setLooping(CONTAINER[i].isLooping());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void Play(int index)
	{
		if(!GameEngineConfiguration.useSound)return;
		paused = false;
		player[index].play();
	}
	
	public static void Pause(int index)
	{
		if(!GameEngineConfiguration.useSound)return;
		paused = true;
		player[index].pause();
	}
	
	public static void PauseAll()
	{
		for(int i = 0; i < player.length; i++)
		{
			Sfx.Pause(i);
		}
	}
	
	public static void Stop(int index)
	{
		if(!GameEngineConfiguration.useSound)return;
		paused = false;
		player[index].stop();
	}
	
	public static void Resume(int index)
	{
		if(!GameEngineConfiguration.useSound)return;
		if(paused)
		{
			paused = false;
			player[index].play();
		}
		else
		{
			System.out.println("cannot resume music...");
		}
	}
	
	public static void setVolume(int index, float volume)
	{
		player[index].setVolume(volume);
	}
	
	public static void setVolume(int index, float leftVolume, float rightVolume)
	{
		player[index].setVolume(leftVolume, rightVolume);
	}
	
	public static float getVolume(int index)
	{
		return player[index].getVolume();
	}
	
	public static float getRightVolume(int index)
	{
		return player[index].getRightVolume();
	}
	
	public static float getLeftVolume(int index)
	{
		return player[index].getLeftVolume();
	}
	
	public static void setRate(int index, float rate)
	{
		player[index].setRate(rate);
	}
	
}
