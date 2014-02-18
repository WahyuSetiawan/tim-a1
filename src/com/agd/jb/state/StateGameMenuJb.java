package com.agd.jb.state;


import com.agd.jb.state.value.Option;
import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValueInterface;
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

public class StateGameMenuJb extends GameState implements ValueCamera, ValuePlayer, StateDefine, ValueMenu, ValueInterface
{	
	private GameSprite bg_belakang;
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite[] bg_depan		= new GameSprite[2];
	
	private Option option;
	
	private GameSprite closing_background;
	private GameSprite closing_yes;
	private GameSprite closing_no;
	
	private GameSprite option_background;
	private GameSprite option_gear;
	private GameSprite option_credit;
	private GameSprite option_off_sfx;
	private GameSprite option_off_bgm;
	private GameSprite option_on_sfx;
	private GameSprite option_on_bgm;
	
	private GameText text_play;
	private GameText text_highscore;
	
	public StateGameMenuJb(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initComponent() 
	{
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
		
		closing_background 	= new GameSprite(CLOSING_BG, engine);
		closing_yes			= new GameSprite(CLOSING_YES, engine);
		closing_no 			= new GameSprite(CLOSING_NO, engine);
		
		option_gear			= new GameSprite(OPTION_GEAR, engine);
		option_background	= new GameSprite(OPTION_BG, engine);
		option_credit		= new GameSprite(OPTION_CREDIT, engine);
		option_off_sfx		= new GameSprite(OPTION_OFF, engine);
		option_off_bgm		= new GameSprite(OPTION_OFF, engine);
		option_on_sfx		= new GameSprite(OPTION_ON, engine);
		option_on_bgm		= new GameSprite(OPTION_ON, engine);
		
		text_play 		= new GameText(PLAY_NOW, PLAY_NOW.length(), engine.getFont(FONT_ANIMEACE2_ITAL),engine);
		
		String strscore = engine.getDatabase().getData(TABLE_SCORE, 0, 1);
		
		text_highscore	= new GameText(SCORE + strscore, strscore.length() + SCORE.length(), engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		option = new Option();
		
		engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
		
		closingDefaultConfig();
		optionDefaultConfig();
		
		closing_background.setVisible(false);
		option_background.setVisible(false);
	}
	
	protected void closingDefaultConfig(){
		closing_background.setHeight(CLOSINGBACKGROUNDHEIGHT);
		closing_background.setWidth(CLOSINGBACKGROUNDWIDTH);
		
		closing_yes.setHeight(CLOSINGYESBUTTONHEIGHT);
		closing_yes.setWidth(CLOSINGYESBUTTONWIDTH);
		
		closing_no.setHeight(CLOSINGNOBUTTONHEIGHT);
		closing_no.setWidth(CLOSINGNOBUTTONWIDTH);
		
		option_gear.setHeight(GEARBUTTONHEIGHT);
		option_gear.setWidth(GEARBUTTONWIDTH);
	}
	
	protected void optionDefaultConfig(){
		option_background.setWidth(OPTIONBACKGROUNDWIDTH);
		option_background.setHeight(OPTIONBACKGROUNDHEIGHT);
		
		option_off_sfx.setHeight(OFFBUTTONHEIGHT);
		option_off_sfx.setWidth(OFFBUTTONWIDTH);
		
		option_off_bgm.setHeight(OFFBUTTONHEIGHT);
		option_off_bgm.setWidth(OFFBUTTONWIDTH);
		
		option_on_sfx.setHeight(OFFBUTTONHEIGHT);
		option_on_sfx.setWidth(OFFBUTTONWIDTH);
		
		option_on_bgm.setHeight(OFFBUTTONHEIGHT);
		option_on_bgm.setWidth(OFFBUTTONWIDTH);
		
		option_credit.setHeight(OPTIONCREDITBUTTONHEIGHT);
		option_credit.setWidth(OPTIONCREDITBUTTONWIDTH);
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
		
		engine.hud.attachChild(text_play);
		engine.hud.attachChild(text_highscore);
		
		engine.hud.attachChild(option_gear);
		
		engine.hud.attachChild(option_background);
		option_background.attachChild(option_off_sfx);
		option_background.attachChild(option_on_sfx);
		option_background.attachChild(option_off_bgm);
		option_background.attachChild(option_on_bgm);
		option_background.attachChild(option_credit);
		
		engine.hud.attachChild(closing_background);
		closing_background.attachChild(closing_yes);
		closing_background.attachChild(closing_no);

	}

	@Override
	protected void detach()
	{
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
		text_highscore.detachSelf();
		
		option_gear.detachSelf();
		option_background.detachSelf();
		option_background.detachChildren();
		
		closing_background.detachSelf();
		closing_no.detachSelf();
		closing_yes.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_belakang.setPosition(0,0);
		
		bg_tengah[0].setX(0);
		bg_tengah[1].setX(bg_tengah[0].getWidth());
		
		bg_depan[0].setX(0);
		bg_depan[1].setX(bg_depan[0].getWidth());
		
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		text_play.setPosition(Anchor.CENTER);
		text_highscore.setPosition(Anchor.TOP_CENTER);
		
		option_gear.setPosition(Anchor.TOP_RIGHT);
		option_background.setPosition(Anchor.CENTER);
		option_off_sfx.setPosition(225, 150);
		option_on_sfx.setPosition(option_off_sfx.getX(), option_off_sfx.getY());
		option_off_bgm.setPosition(option_off_sfx.getX(), option_off_sfx.getY() + 55);
		option_on_bgm.setPosition(option_off_bgm.getX(), option_off_bgm.getY());
		option_credit.setPosition(option_off_bgm.getX() - (option_credit.getWidth()/2), option_off_bgm.getY() + 55);
		
		closing_background.setPosition(Anchor.CENTER);
		closing_yes.setPosition(130, 150);
		closing_no.setPosition(closing_yes.getX() + closing_yes.getWidth() + 19, 150);
	}

	@Override
	protected void registerTouch() 
	{
		engine.hud.registerTouchArea(text_play);
		
		// register touch closing
		engine.hud.registerTouchArea(closing_no);
		engine.hud.registerTouchArea(closing_yes);
		
		//register touch option
		engine.hud.registerTouchArea(option_gear);
		engine.hud.registerTouchArea(option_credit);
		engine.hud.registerTouchArea(option_off_bgm);
		engine.hud.registerTouchArea(option_on_bgm);
		engine.hud.registerTouchArea(option_on_sfx);
		engine.hud.registerTouchArea(option_off_sfx);
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.unregisterHudTouch(text_play);
		
		// unregister touch closing
		engine.unregisterHudTouch(closing_no);
		engine.unregisterHudTouch(closing_yes);
		
		// unregister touch option
		engine.unregisterHudTouch(option_gear);
		engine.unregisterHudTouch(option_credit);
		engine.unregisterHudTouch(option_off_bgm);
		engine.unregisterHudTouch(option_on_bgm);
		engine.unregisterHudTouch(option_on_sfx);
		engine.unregisterHudTouch(option_off_sfx);
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
		if(keyCode == KeyEvent.KEYCODE_BACK  && !closing_background.isVisible()) 
		{	
			if(option_background.isVisible()){
				option_background.setVisible(false);
			}
			closing_background.setVisible(true);
			closingDefaultConfig();
		}
		else if(keyCode == KeyEvent.KEYCODE_BACK && closing_background.isVisible())
		{
			closing_background.setVisible(false);
		}
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) 
	{
		switch (pSceneTouchEvent.getAction()) 
		{
			case TouchEvent.ACTION_DOWN:
				{
					if (pTouchArea == text_play && !closing_background.isVisible() && !option_background.isVisible()) {
						exitState(STATE_PLAY);
					} 
					else if(pTouchArea == closing_no && closing_background.isVisible())
					{
						closing_no.setHeight(CLOSINGNOPUSHHEIGHT);
						closing_no.setWidth(CLOSINGNOPUSHWIDTH);
					}
					else if(pTouchArea == closing_yes && closing_background.isVisible())
					{
						closing_yes.setHeight(CLOSINGYESPUSHHEIGHT);
						closing_yes.setWidth(CLOSINGYESPUSHWIDTH);
					}
					else if (pTouchArea == option_gear) {
						option_gear.setHeight(GEARPUSHHEIGHT);
						option_gear.setWidth(GEARPUSHWIDTH);
					}
					else if (pTouchArea == option_credit  && option_background.isVisible()) {
						option_credit.setHeight(OPTIONCREDITPUSHHEIGHT);
						option_credit.setWidth(OPTIONCREDITPUSHWIDTH);
					}
					else if (pTouchArea == option_off_bgm && option_background.isVisible()) 
					{
						if(option_on_bgm.isVisible()){
							option_on_bgm.setHeight(OFFPUSHHEIGHT);
							option_on_bgm.setWidth(OFFPUSHWIDTH);
						}
						else if(option_off_bgm.isVisible()) 
						{
							option_off_bgm.setHeight(OFFPUSHHEIGHT);
							option_off_bgm.setWidth(OFFPUSHWIDTH);
						}
					}
					else if (pTouchArea == option_off_sfx && option_background.isVisible()) 
					{
						if(option_on_sfx.isVisible()){
							option_on_sfx.setHeight(OFFPUSHHEIGHT);
							option_on_sfx.setWidth(OFFPUSHWIDTH);
						}
						else if(option_off_sfx.isVisible()) 
						{
							option_off_sfx.setHeight(OFFPUSHHEIGHT);
							option_off_sfx.setWidth(OFFPUSHWIDTH);
						}
					}
				}
				break;
			case TouchEvent.ACTION_UP:
				{
					if(pTouchArea == closing_no && closing_background.isVisible())
					{
						closing_no.setHeight(CLOSINGNOBUTTONHEIGHT);
						closing_no.setWidth(CLOSINGNOBUTTONWIDTH);
						
						closing_background.setVisible(false);
					}
					else if(pTouchArea == closing_yes && closing_background.isVisible())
					{
						closing_yes.setHeight(CLOSINGYESBUTTONHEIGHT);
						closing_yes.setWidth(CLOSINGYESBUTTONWIDTH);
						
						engine.finish();
					}
					else if (pTouchArea == option_gear) {
						option_gear.setHeight(GEARBUTTONHEIGHT);
						option_gear.setWidth(GEARBUTTONWIDTH);
						
						
						if(option_background.isVisible())
						{
							option_background.setVisible(false);
						}
						else if(!option_background.isVisible()) 
						{
							option_background.setVisible(true);
							optionDefaultConfig();
						}
					}
					else if (pTouchArea == option_credit  && option_background.isVisible()) {
						option_credit.setHeight(OPTIONCREDITBUTTONHEIGHT);
						option_credit.setWidth(OPTIONCREDITBUTTONWIDTH);
					}
					else if (pTouchArea == option_off_bgm && option_background.isVisible()) 
					{
						if(option_on_bgm.isVisible()){
							option_on_bgm.setHeight(OFFBUTTONHEIGHT);
							option_on_bgm.setWidth(OFFBUTTONWIDTH);
							
							option_on_bgm.setVisible(false);
							option_off_bgm.setVisible(true);
						}
						else if(option_off_bgm.isVisible()) 
						{
							option_off_bgm.setHeight(OFFBUTTONHEIGHT);
							option_off_bgm.setWidth(OFFBUTTONWIDTH);
							
							option_off_bgm.setVisible(false);
							option_on_bgm.setVisible(true);
						}
					}
					else if (pTouchArea == option_off_sfx && option_background.isVisible()) 
					{
						if(option_on_sfx.isVisible()){
							option_on_sfx.setHeight(OFFBUTTONHEIGHT);
							option_on_sfx.setWidth(OFFBUTTONWIDTH);
							
							option_on_sfx.setVisible(false);
							option_off_sfx.setVisible(true);
						}
						else if(option_off_sfx.isVisible()) 
						{
							option_off_sfx.setHeight(OFFBUTTONHEIGHT);
							option_off_sfx.setWidth(OFFBUTTONWIDTH);
							
							option_off_sfx.setVisible(false);
							option_on_sfx.setVisible(true);
						}
					}
				}
				break;
		}
		return false;
	}
}
