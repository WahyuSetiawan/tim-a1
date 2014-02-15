package com.agd.jb.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import com.agd.jb.state.value.StateDefine;

import android.R.string;
import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateLoading extends GameState implements StateDefine {
	
	private GameSprite splash;
	private GameSprite loadingbar;
	
	public StateLoading(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initComponent() {
		splash 		= new GameSprite(SPLASH, engine);
		loadingbar 	= new GameSprite(LOADINGBAR, engine);
	}

	@Override
	protected void init() {
		loadingbar.setHeight(30);
		loadingbar.setWidth(0);
	}

	@Override
	protected void attach() {
		engine.scene.attachChild(splash);
		engine.scene.attachChild(loadingbar);
	}

	@Override
	protected void detach() {
		splash.detachSelf();
		loadingbar.detachSelf();
	}

	@Override
	protected void setPosition() {
		splash.setPosition(0,0);
		loadingbar.setPosition(GameEngineConfiguration.masterWidth / 8 , 400);
	}

	@Override
	protected void registerTouch() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void unregisterTouch() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUpdate() {
		if(loadingbar.getX() + loadingbar.getWidth() > engine.camera.getWidth() - (engine.camera.getWidth() / 8))
		{
			exitState(STATE_MENU);
		}
		else
		{
			loadingbar.setWidth(loadingbar.getWidth() + 2);
		}
	}

	@Override
	protected void onPaused() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResumed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

	}

}
