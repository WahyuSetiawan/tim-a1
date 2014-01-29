package com.agd.jb.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;
import android.view.KeyEvent;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGamePlayJb extends GameState
{
	private GameSprite[] bg_depan_static = new GameSprite[2];
	
	private GameAnim player_lari;
	
	public static final int SPEED = 5;
	
	
	public StateGamePlayJb(GameEngine engine) 
	{
		super(engine);
	
	}

	@Override
	public void initComponent() 
	{
		for (int loop = 0; loop < bg_depan_static.length; loop++)
		{
			bg_depan_static[loop] = new GameSprite(BG_DEPAN_STATIC, engine);
		}
		player_lari = new GameAnim(ANIM_PLAYER_LARI, engine);
	}

	@Override
	protected void init() 
	{
		engine.camera.setCenter(GameEngine.cameraWidth / 2, GameEngine.cameraHeight / 2);
		
		player_lari.animate(50, true);
	}

	@Override
	protected void attach() 
	{
		for (int loop = 0; loop < bg_depan_static.length; loop++)
		{
			engine.scene.attachChild(bg_depan_static[loop]);
		}
		
		engine.scene.attachChild(player_lari);
		
	}

	@Override
	protected void detach()
	{
		for (int loop = 0; loop < bg_depan_static.length; loop++)
		{
			bg_depan_static[loop].detachSelf();
		}
		
		player_lari.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_depan_static[0].setX(0);
		bg_depan_static[1].setX(bg_depan_static[0].getWidth());
		
		player_lari.setX(0);
		player_lari.setY(GameEngine.cameraHeight - 180);
	}

	@Override
	protected void registerTouch() 
	{
		
	}

	@Override
	protected void unregisterTouch() 
	{
		
	}

	@Override
	protected void onUpdate() 
	{
		/*
		float bg0_x 	= bg_depan_static[0].getX();
		float bg0_width = bg_depan_static[0].getWidth();
		
		float bg1_x		= bg_depan_static[1].getX();
		float bg1_width = bg_depan_static[1].getWidth();
		
		player_lari.setX(player_lari.getX() + SPEED);
		engine.camera.setCenter(player_lari.getX() + GameEngine.cameraWidth / 2, engine.camera.getCenterY());
		
		
		if (bg0_x + bg0_width < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_depan_static[0].setX(bg1_x + bg1_width);
		}
		
		if (bg1_x + bg1_width < engine.camera.getCenterX()- GameEngine.cameraWidth / 2)
		{
			bg_depan_static[1].setX(bg0_x + bg0_width);
		}
		*/
		
		player_lari.setX(player_lari.getX() + SPEED);
		engine.camera.setCenter(player_lari.getX() + GameEngine.cameraWidth / 2, engine.camera.getCenterY());
		
		
		if (bg_depan_static[0].getX() + bg_depan_static[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_depan_static[0].setX(bg_depan_static[1].getX()+ bg_depan_static[1].getWidth());
		}
		
		if (bg_depan_static[1].getX() + bg_depan_static[1].getWidth() < engine.camera.getCenterX()- GameEngine.cameraWidth / 2)
		{
			bg_depan_static[1].setX(bg_depan_static[0].getX() + bg_depan_static[0].getWidth());
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
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) 
	{
		
		return false;
	}

}
