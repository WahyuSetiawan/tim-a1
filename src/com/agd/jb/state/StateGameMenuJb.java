package com.agd.jb.state;


import com.agd.jb.state.StateGamePlayJb;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;
import android.view.KeyEvent;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGameMenuJb extends GameState
{
	private GameSprite bg_menu;
	private GameSprite bg_fill;
	
	private GameText text_play;
	
	public StateGameMenuJb(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initComponent() 
	{
		bg_fill = new GameSprite(BG_MENU_FILL, engine);
		bg_menu = new GameSprite(BG_MENU, engine);
		text_play = new GameText("Play Now", 8, engine.getFont(FONT_ANIMEACE2_ITAL),engine);
	}

	@Override
	protected void init() 
	{
		
	}

	@Override
	protected void attach() 
	{
		engine.scene.attachChild(bg_fill);
		
		bg_fill.attachChild(bg_menu);
		bg_fill.attachChild(text_play);
	}

	@Override
	protected void detach()
	{
		bg_fill.detachSelf();
		bg_menu.detachSelf();
		text_play.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
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
				break;
			case TouchEvent.ACTION_UP:
				{
					if (pTouchArea == text_play) {
						engine.changeState(1);
					}
				}
				break;
		}
		return false;
	}

}
