package com.agd.jb.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import android.view.KeyEvent;
import lib.elementgame.GameSprite;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateLoading extends GameState {
	
	private GameSprite loading;

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
		loading = new GameSprite(LOADING, engine);

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void attach() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void detach() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setPosition() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
