package lib.engine;

import lib.defines.AnimDefines;
import lib.defines.FontDefines;
import lib.defines.GameDefines;
import lib.defines.MfxDefines;
import lib.defines.SfxDefines;
import lib.defines.SpriteDefines;
import lib.defines.TableDefine;

import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import android.view.KeyEvent;

public abstract class GameState implements IOnAreaTouchListener, SpriteDefines, AnimDefines, 
	FontDefines, SfxDefines, MfxDefines, GameDefines, TableDefine
{
	private boolean isInitialized;
	private boolean isPaused;
	
	protected GameEngine engine;
	
	public GameState(GameEngine engine)
	{
		this.engine 	= engine;
		isInitialized 	= false;
		isPaused		= false;
	}
	
	public void update() 
	{
		if(!isInitialized)
		{
			init();
			engine.scene.setOnAreaTouchListener(this);
			engine.hud.setOnAreaTouchListener(this);
			attach();
			setPosition();
			registerTouch();
			isInitialized = true;
		}
		
		if(isInitialized && !isPaused)
		{
			onUpdate();
		}
	}
	
	protected void pause()
	{
		onPaused();
		isPaused = true;
	}
	
	protected void resume()
	{
		onResumed();
		isPaused = false;
	}
	
	protected boolean isPaused() 
	{
		return isPaused;
	}
	
	protected void exitState(int nextState)
	{
		unregisterTouch();
		engine.scene.setOnAreaTouchListener(null);
		engine.hud.setOnAreaTouchListener(null);
		detach();
		isInitialized 	= false;
		isPaused 		= false;
		engine.changeState(nextState);
	}
	
	protected void reset()
	{
		detach();
		unregisterTouch();
		isInitialized 	= false;
		isPaused 		= false;
	}
	
	public void onSceneTouched(Scene pScene, TouchEvent pSceneTouchEvent) 
	{
		
	}
	
	public abstract void initComponent();
	protected abstract void init();
	protected abstract void attach();
	protected abstract void detach();
	protected abstract void setPosition();
	protected abstract void registerTouch();
	protected abstract void unregisterTouch();
	protected abstract void onUpdate();
	protected abstract void onPaused();
	protected abstract void onResumed();
	public abstract void onKeyUp(int keyCode, KeyEvent event);
}
