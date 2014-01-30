package com.agd.jb.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;
import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValuePlay;
import com.agd.jb.state.value.ValueState;

import android.R.integer;
import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGamePlayJb extends GameState implements ValueState, ValueCamera, ValuePlay
{
	private GameSprite[] bg_depan_static = new GameSprite[2];
	private GameSprite button;
	
	private GameText status;
	
	private GameAnim player_lari;
	
	private int jump;
	private int move_player;
	
	private static final int netral = 0;
	private static final int up 	= 1;
	private static final int down 	= 2;
	private static final int singlejump = 1;
	private static final int doublejump = 2;
	
	private float jumpy;
	private int rangeup;
	
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
		button = new GameSprite(BUTTON, engine);
		
		button.setAlpha(0f);
		
		status = new GameText(String.valueOf(rangeup), 1, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		engine.camera.setCenter(GameEngine.cameraWidth / 2, GameEngine.cameraHeight / 2);
		player_lari.animate(50, true);
		
		jump = 0;
		move_player = up;
		jumpy = (float) 10;
	}

	@Override
	protected void attach() 
	{
		for (int loop = 0; loop < bg_depan_static.length; loop++)
		{
			engine.scene.attachChild(bg_depan_static[loop]);
		}
		
		engine.scene.attachChild(player_lari);
		engine.hud.attachChild(button);	
		engine.hud.attachChild(status);
	}

	@Override
	protected void detach()
	{
		for (int loop = 0; loop < bg_depan_static.length; loop++)
		{
			bg_depan_static[loop].detachSelf();
		}
		
		player_lari.detachSelf();
		button.detachSelf();
		status.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_depan_static[0].setX(0);
		bg_depan_static[1].setX(bg_depan_static[0].getWidth());
		
		player_lari.setX(0);
		player_lari.setY(GameEngine.cameraHeight - 180);
		
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
		
		if(jump == singlejump)
		{
			doubleJump(true, player_lari, 150, GameEngine.cameraHeight - player_lari.getHeight());
		}else if (jump == doublejump) {
			doubleJump(false, player_lari, 150, GameEngine.cameraHeight - player_lari.getHeight());
		}else if(jump > doublejump){
			jump = doublejump;
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
				if(pTouchArea == button){
					++jump;
				}
			}
			break;
		}
		
		return false;
	}
	
	private void doubleJump(Boolean status, GameAnim animjump, float rangeup, float rangedown) {
		this.rangeup = (int) (this.rangeup - rangeup);
		
		if(!status)
		{
			switch (move_player) {
			case up:
				if(animjump.getY() <= rangeup)
				{
					move_player = down;
				}
				animjump.setY((float) (animjump.getY() - jumpy));
				break;
			case down:
				if (animjump.getY() >= rangedown) {
					jump =  netral;
					move_player = up;
					rangeup = GameEngineConfiguration.masterWidth;
				}
				
				animjump.setY((float) (animjump.getY() + jumpy));
				break;
			}
		} 
		else 
		{
			this.rangeup = (int) (this.rangeup - rangeup);
			
			switch (move_player) {
			case up:
				if(animjump.getY() <= rangeup)
				{
					move_player = down;
				}
				animjump.setY((float) (animjump.getY() - jumpy));
				break;
			case down:
				if (animjump.getY() >= rangedown) {
					jump = netral;
					move_player = up;
					this.status.setText(String.valueOf(jump));
				}
				
				animjump.setY((float) (animjump.getY() + jumpy));
				break;
			}
		}
	}

	/*private void singleJump(GameAnim animjump, float rangeup, float rangedown) {	
		this.rangeup = rangeup;
		
		switch (move_player) {
		case up:
			if(animjump.getY() <= rangeup)
			{
				move_player = down;
			}
			animjump.setY((float) (animjump.getY() - jumpy));
			break;
		case down:
			if (animjump.getY() >= rangedown) {
				jump =  netral;
				move_player = up;
			}
			
			animjump.setY((float) (animjump.getY() + jumpy));
			break;
		}		
	}*/

}
