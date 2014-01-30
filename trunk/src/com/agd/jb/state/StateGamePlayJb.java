package com.agd.jb.state;

import org.andengine.audio.sound.Sound;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;
import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValuePlay;
import com.agd.jb.state.value.ValueState;

import android.view.KeyEvent;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGamePlayJb extends GameState implements ValueState, ValueCamera, ValuePlay
{
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite bg_belakang1;
	private GameSprite bg_belakang2;
	private GameSprite bg_tengah;
	
	private GameSprite button;
	
	private GameText status;
	
	private GameAnim player;
	private GameAnim player_run;
	private GameAnim player_single_jump;
	private GameAnim player_double_jump;
	private GameAnim player_fall;
	private GameAnim player_attach;
	private GameAnim player_accident;
	
	private int jump;
	private int move_player;
	
	private static final int netral 	= 0;
	private static final int up 		= 1;
	private static final int down 		= 2;
	private static final int singlejump = 1;
	private static final int doublejump = 2;
	
	private float jumpy;
	private float speed_decrease;
	private float rangeup;
	
	public static final int SPEED = 3;
	
	
	public StateGamePlayJb(GameEngine engine) 
	{
		super(engine);
	
	}

	@Override
	public void initComponent() 
	{
		bg_belakang1 	= new GameSprite(BG_BELAKANG1, engine);
		bg_belakang2 	= new GameSprite(BG_BELAKANG2, engine);
		bg_tengah		= new GameSprite(BG_TENGAH, engine);
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop] = new GameSprite(BG_FLOOR_DEPAN, engine);
		}
		
		player_run 				= new GameAnim(ANIM_PLAYER_LARI, engine);
		
		//player_single_jump 	= new GameAnim(ANIM_PLAYER_LOMPAT, engine);
		//player_double_jump 	= new GameAnim(ANIM_PLAYER_DOUBLEJUMP, engine);
		//player_accident 	= new GameAnim(ANIM_PLAYER_LOMPAT, engine);
		//player_fall			= new GameAnim(ANIM_PLAYER_LUBANG, engine);
		
		button = new GameSprite(BUTTON, engine);
		
		status = new GameText("0", 1, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		engine.camera.setCenter(GameEngine.cameraWidth / 2, GameEngine.cameraHeight / 2);
		
		player_run.animate(43, true);
		button.setAlpha(0f);
		
		speed_decrease 	= 0f;
		jump 			= 0;
		move_player 	= up;
		jumpy 			= 9f;
	}

	@Override
	protected void attach() 
	{
		engine.scene.attachChild(bg_belakang1);
		engine.scene.attachChild(bg_belakang2);
		engine.scene.attachChild(bg_tengah);
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			engine.scene.attachChild(bg_floor_depan[loop]);
		}
		
		engine.scene.attachChild(player_run);
		engine.hud.attachChild(button);	
		engine.hud.attachChild(status);
	}

	@Override
	protected void detach()
	{
		bg_belakang1.detachSelf();
		bg_belakang2.detachSelf();
		bg_tengah.detachSelf();
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop].detachSelf();
		}
		
		player_run.detachSelf();
		button.detachSelf();
		status.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		player_run.setX(50);
		player_run.setY(GameEngine.cameraHeight - 180);
		
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
		bg_belakang1.setX(bg_belakang1.getX() + SPEED);
		bg_belakang2.setX(bg_belakang2.getX() + SPEED);
		bg_tengah.setX(bg_tengah.getX() + SPEED);
		
		player_run.setX(player_run.getX() + SPEED);
		
		engine.camera.setCenter(player_run.getX() + GameEngine.cameraWidth / 2, engine.camera.getCenterY());
		
		
		if (bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_floor_depan[0].setX(bg_floor_depan[1].getX()+ bg_floor_depan[1].getWidth());
		}
		
		if (bg_floor_depan[1].getX() + bg_floor_depan[1].getWidth() < engine.camera.getCenterX()- GameEngine.cameraWidth / 2)
		{
			bg_floor_depan[1].setX(bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth());
		}
		
		if(jump > netral)
		{
			doubleJump(player_run,  GameEngine.cameraHeight - 180, jumpy);
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
					case singlejump:
						rangeup = GameEngine.cameraHeight - 260;
						break;
					case doublejump:
						rangeup = player_run.getY() - 74;
						break;
					default:
						jump = doublejump;
						break;	
					}
				}
			}
			break;
		}
		
		return false;
	}
	
	private void doubleJump(GameAnim animjump, float rangedown, float speed) {		
		if (speed_decrease == 0f){
			speed_decrease = speed;
			speed_decrease =- 2f;
		}
		else
		{
			speed_decrease =- 2f;
		}
		
		switch (move_player) {
		case up:
			if(animjump.getY() <= rangeup)
			{
				move_player = down;
			}
			animjump.setY((float) (animjump.getY() - speed));
			break;
		case down:
			if (animjump.getY() >= rangedown) {
				jump =  netral;
				move_player = up;
				rangeup = 0;
			}
				
			animjump.setY((float) (animjump.getY() + speed));
			break;
		}
	}
}
