package com.agd.jb.state;


import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValueMenu;
import com.agd.jb.state.value.ValuePlayer;
import com.agd.jb.state.value.StateDefine;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.elementgame.Mfx;
import lib.elementgame.Sfx;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGameMenuJb extends GameState implements ValueCamera, ValuePlayer, StateDefine, ValueMenu
{	
	private GameSprite bg_belakang;
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite[] bg_depan		= new GameSprite[2];
	
	//private GameSprite bg_menu;
	
	private GameText text_play;
	//private GameText text_highscore;
	
	public StateGameMenuJb(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initComponent() 
	{
		//bg_menu 		= new GameSprite(BG_MENU, engine);
		bg_belakang		= new GameSprite(BG_BELAKANG, engine);
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop] 	= new GameSprite(BG_TENGAH, engine);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop] = new GameSprite(BG_FLOOR_DEPAN, engine);
		}
		
		for (int loop = 0; loop < bg_depan.length; loop++)
		{
			bg_depan[loop] 		= new GameSprite(BG_DEPAN, engine);
		}
		
		text_play 		= new GameText(PLAY_NOW, PLAY_NOW.length(), engine.getFont(FONT_ANIMEACE2_ITAL),engine);
		
		//String strscore = engine.getDatabase().getData(TABLE_SCORE, 0, 1);
		
		//text_highscore	= new GameText(SCORE + strscore, strscore.length() + SCORE.length(), engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		
		
		engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
		//bg_menu.setAlpha(0f);
	}

	@Override
	protected void attach() 
	{		
		engine.scene.attachChild(bg_belakang);
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_tengah[loop]);
		}
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_depan[loop]);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			engine.scene.attachChild(bg_floor_depan[loop]);
		}
		
		//engine.hud.attachChild(bg_menu);
		engine.hud.attachChild(text_play);
		//engine.hud.attachChild(text_highscore);
	}

	@Override
	protected void detach()
	{
		//bg_fill.detachSelf();
		//bg_menu.detachSelf();
		bg_belakang.detachSelf();
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop].detachSelf();
		}
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_depan[loop].detachSelf();
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop].detachSelf();
		}
		
		text_play.detachSelf();
		//text_highscore.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		//bg_menu.setPosition(0,0);
		bg_belakang.setPosition(0,0);
		
		bg_tengah[0].setX(0);
		bg_tengah[1].setX(bg_tengah[0].getWidth());
		
		bg_depan[0].setX(0);
		bg_depan[1].setX(bg_depan[0].getWidth());
		
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		text_play.setPosition(Anchor.CENTER);
		//text_highscore.setPosition(Anchor.TOP_CENTER);
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
		bg_tengah[0].setX(bg_tengah[0].getX()- SPEEDBGBELAKANG);
		bg_tengah[1].setX(bg_tengah[1].getX()- SPEEDBGBELAKANG);
		
		bg_depan[0].setX(bg_depan[0].getX()- SPEEDBGDEPAN);
		bg_depan[1].setX(bg_depan[1].getX()- SPEEDBGDEPAN);
		
		bg_floor_depan[0].setX(bg_floor_depan[0].getX() + SPEEDBFLOOR);
		bg_floor_depan[1].setX(bg_floor_depan[1].getX() + SPEEDBFLOOR);
		
		if(bg_tengah[0].getX() < engine.camera.getXMin()){
			bg_tengah[1].setX(bg_tengah[0].getX() + bg_tengah[0].getWidth());
		}
		
		if(bg_tengah[1].getX() < engine.camera.getXMin()){
			bg_tengah[0].setX(bg_tengah[1].getX() + bg_tengah[1].getWidth());
		}
		
		if(bg_depan[0].getX() < engine.camera.getXMin()){
			bg_depan[1].setX(bg_depan[0].getX() + bg_depan[0].getWidth());
		}
		
		if(bg_depan[1].getX() < engine.camera.getXMin()){
			bg_depan[0].setX(bg_depan[1].getX() + bg_depan[1].getWidth());
		}
		
		if(bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth()> engine.camera.getXMax()){
			bg_floor_depan[1].setX(bg_floor_depan[0].getX() - bg_floor_depan[0].getWidth());
		}
		
		if(bg_floor_depan[1].getX() + bg_floor_depan[1].getWidth() > engine.camera.getXMax()){
			bg_floor_depan[0].setX(bg_floor_depan[1].getX() - bg_floor_depan[1].getWidth());
		}
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
