package lib.elementgame;

import java.io.IOException;

import lib.defines.GameEngineConfiguration;
import lib.defines.MfxDefines;
import lib.engine.GameEngine;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;

public class Mfx implements MfxDefines
{
	GameEngine mainActivity;
	static Music player[] = new Music[CONTAINER.length];
	static boolean isPaused[] = new boolean[CONTAINER.length];
	static boolean paused;
	
	public static void loadAllMfx(GameEngine mainActivity)
	{
		paused = false;
		for(int i = 0; i < player.length; i++)
		{
			try {
				player[i] = MusicFactory.createMusicFromAsset(mainActivity.getMusicManager(), mainActivity, CONTAINER[i].getPath());
				player[i].setLooping(CONTAINER[i].isLooping());
				isPaused[i] = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void Play(int index)
	{
		if(!GameEngineConfiguration.useMusic)return;
		paused = false;
		player[index].play();
		isPaused[index] = false;
	}
	
	public static void Pause(int index)
	{
		paused = true;
		if(player[index].isPlaying())
		{
			player[index].pause();
			isPaused[index] = true;
		}
	}
	
	public static void PauseAll()
	{
		for(int i = 0; i < player.length; i++)
		{
			if(!Mfx.isPaused[i])
			{
				Mfx.Pause(i);
				isPaused[i] = true;
			}
		}
	}
	
	public static boolean isPaused(int index)
	{
		return isPaused[index];
	}
	
	public static void Stop(int index)
	{
		if(!GameEngineConfiguration.useMusic)return;
		paused = false;
		player[index].stop();
	}
	
	public static void Resume(int index)
	{
		if(!GameEngineConfiguration.useMusic)return;
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
	
	public static void setVolume(int index, float lVolume, float rVolume)
	{
		player[index].setVolume(lVolume, rVolume);
	}
}
