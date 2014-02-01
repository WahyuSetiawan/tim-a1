package com.agd.jb.state;

import org.andengine.entity.modifier.JumpModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;

import com.agd.jb.state.value.ValueAction;
import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValuePlayer;
import com.agd.jb.state.value.StateDefine;

import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGamePlayJb extends GameState  implements StateDefine, ValueCamera, ValuePlayer, ValueAction
{
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite bg_belakang;
	
	private GameSprite button;
	
	private GameText status;
	
	private GameAnim player_run;
	//private GameAnim player_eva;
	//private GameAnim player_eva1;
	
	private int jump;
	private int move_player;
	
	private float speed_jump;
	private float speed_decrease;
	private float rangeup;
	
	
	public StateGamePlayJb(GameEngine engine) 
	{
		super(engine);
	}

	@Override
	public void initComponent() 
	{
		bg_belakang 	= new GameSprite(BG_BELAKANG, engine);
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop] = new GameSprite(BG_TENGAH, engine);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop] = new GameSprite(BG_FLOOR_DEPAN, engine);
		}
		
		player_run 				= new GameAnim(ANIM_PLAYER_LARI, engine);
		//player_eva				= new GameAnim(ANIM_PLAYER_NABRAK, engine);
		//player_eva1				= new GameAnim(ANIM_PLAYER_DOUBLEJUMP, engine);
		
		button = new GameSprite(BUTTON, engine);
		
		status = new GameText("0", 1, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		engine.camera.setCenter(GameEngine.cameraWidth / 2, GameEngine.cameraHeight / 2);
		
		player_run.animate(SPEED_ANIM, true);
		//player_eva.animate(SPEED_ANIM, true);
		//player_eva1.animate(SPEED_ANIM, true);
		button.setAlpha(0f);
		
		speed_decrease 	= SPEED_DECREASE_INIT;
		jump 			= NETRAL;
		move_player 	= UP;
		speed_jump 		= SPEED_JUMP;
	}

	@Override
	protected void attach() 
	{
		engine.scene.attachChild(bg_belakang);
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_tengah[loop]);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			engine.scene.attachChild(bg_floor_depan[loop]);
		}
		
		engine.scene.attachChild(player_run);
		//engine.scene.attachChild(player_eva);
		//engine.scene.attachChild(player_eva1);
		
		engine.hud.attachChild(button);	
		engine.hud.attachChild(status);
	}

	@Override
	protected void detach()
	{
		bg_belakang.detachSelf();
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop].detachSelf();
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop].detachSelf();
		}
		
		player_run.detachSelf();
		//player_eva.detachSelf();
		//player_eva1.detachSelf();
		
		button.detachSelf();
		status.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_belakang.setPosition(0,0);
		
		bg_tengah[0].setX(0);
		bg_tengah[1].setX(bg_tengah[0].getWidth());
		
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		player_run.setX(50);
		player_run.setY(lowy);
		
		//player_eva.setPosition(player_run.getX() + player_run.getWidth(),GameEngine.cameraHeight - 180);
		//player_eva1.setPosition(player_eva.getX() + player_eva.getWidth(), GameEngine.cameraHeight - 180);
		
		
		button.setPosition(Anchor.BOTTOM_RIGHT);
		status.setPosition(Anchor.TOP_RIGHT);
	}

	@Override
	protected void registerTouch() 
	{
		engine.hud.registerTouchArea(button);
		
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.unregisterHudTouch(button);
	}

	@Override
	protected void onUpdate() 
	{	
		bg_belakang.setX(bg_belakang.getX() + SPEED);
		player_run.setX(player_run.getX() + SPEED);
		
		engine.camera.setCenter(player_run.getX() + GameEngine.cameraWidth / 2, engine.camera.getCenterY());
		
		if (bg_tengah[0].getX() + bg_tengah[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_tengah[0].setX(bg_tengah[1].getX() + bg_tengah[1].getWidth());
		}
		if (bg_tengah[1].getX() + bg_tengah[1].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_tengah[1].setX(bg_tengah[0].getX() + bg_tengah[0].getWidth());
		}
		
		if (bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_floor_depan[0].setX(bg_floor_depan[1].getX()+ bg_floor_depan[1].getWidth());
		}
		if (bg_floor_depan[1].getX() + bg_floor_depan[1].getWidth() < engine.camera.getCenterX()- GameEngine.cameraWidth / 2)
		{
			bg_floor_depan[1].setX(bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth());
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
		if(keyCode == KeyEvent.KEYCODE_BACK) 
		{
			engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
			exitState(STATE_MENU);
		}
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) 
	{
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN:
			
			break;
		case TouchEvent.ACTION_UP:
			{
				if(pTouchArea == button)
				{
					++jump;
					switch (jump) {
					case 0:
						break;
					case SINGLE_JUMP:
						player_run.registerEntityModifier(new JumpModifier(0.47f, player_run.getX(), player_run.getX() + 100, player_run.getY(), lowy, 100));
						jump = 0;
						break;
					case DOUBLE_JUMP:
						player_run.registerEntityModifier(new JumpModifier(0.47f, player_run.getX(), player_run.getX() + 100, player_run.getY(), lowy, 200));
						break;
					default:
						jump = DOUBLE_JUMP;
						break;	
					}
				}
			}
			break;
		}
		
		return false;
	}
}
