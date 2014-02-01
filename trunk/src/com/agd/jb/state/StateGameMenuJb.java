package com.agd.jb.state;


import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValueMenu;
import com.agd.jb.state.value.ValuePlayer;
import com.agd.jb.state.value.StateDefine;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import android.view.KeyEvent;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGameMenuJb extends GameState implements ValueCamera, ValuePlayer, StateDefine, ValueMenu
{		
	private GameSprite bg_menu;
	private GameSprite bg_fill;
	private GameSprite bg_back;
	private GameSprite bg_front;
	
	private GameText text_play;
	
	public StateGameMenuJb(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initComponent() 
	{
		bg_fill 	= new GameSprite(BG_MENU_FILL, engine);
		bg_menu 	= new GameSprite(BG_MENU, engine);
		bg_front 	= new GameSprite(BG_TENGAH, engine);
		bg_back 	= new GameSprite(BG_BELAKANG, engine);
		
		text_play 	= new GameText(PLAY_NOW, PLAY_NOW.length(), engine.getFont(FONT_ANIMEACE2_ITAL),engine);
	}

	@Override
	protected void init() 
	{
		engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
		bg_fill.setAlpha(0f);
		bg_menu.setAlpha(0f);
	}

	@Override
	protected void attach() 
	{		
		engine.scene.attachChild(bg_back);
		engine.scene.attachChild(bg_front);
		engine.scene.attachChild(bg_fill);
		
		bg_fill.attachChild(bg_menu);
		bg_fill.attachChild(text_play);
	}

	@Override
	protected void detach()
	{
		bg_fill.detachSelf();
		bg_menu.detachSelf();
		bg_back.detachSelf();
		bg_front.detachSelf();
		text_play.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_fill.setPosition(0,0);
		bg_menu.setPosition(0,0);
		bg_back.setPosition(0,0);
		bg_front.setPosition(0,0);
		
		bg_fill.setPosition(0,0);
		bg_menu.setPosition(0,0);
		text_play.setPosition(Anchor.CENTER);
	}

	@Override
	protected void registerTouch() 
	{
		engine.hud.registerTouchArea(text_play);
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.unregisterHudTouch(text_play);
	}

	@Override
	protected void onUpdate() 
	{

	}

	@Override
	protected void onPaused() 
	{
		
	}

	@Override
	protected void onResumed() 
	{
		
	}

	@Override
	public void onKeyUp(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK) engine.finish();
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) 
	{
		switch (pSceneTouchEvent.getAction()) 
		{
			case TouchEvent.ACTION_DOWN:
				{
					if (pTouchArea == text_play) {
						exitState(STATE_PLAY);
					}
				}
				break;
			case TouchEvent.ACTION_UP:
				{

				}
				break;
		}
		return false;
	}
}
