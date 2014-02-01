package com.agd.jb.state;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.input.touch.TouchEvent;
import com.agd.jb.state.value.ValueAction;
import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValuePlayer;
import com.agd.jb.state.value.StateDefine;

import android.view.KeyEvent;
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
	private GameSprite pointer;
	
	private GameSprite button;
	private GameSprite button_menu;
	private GameSprite menu;
	
	private GameText status;
	
	private GameAnim player_run;
	//private GameAnim player_eva;
	//private GameAnim player_eva1;
	
	private boolean jump;
	private int jump_player;
	private int move_player;
	private int i;
	
	private float speed_jump;
	private int speed_decrease;
	private float range_up;
	
	
	public StateGamePlayJb(GameEngine engine) 
	{
		super(engine);
	}

	@Override
	public void initComponent() 
	{
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop] = new GameSprite(BG_TENGAH, engine);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop] = new GameSprite(BG_FLOOR_DEPAN, engine);
		}
		
		pointer 		= new GameSprite(POINTER, engine);
		
		player_run 		= new GameAnim(ANIM_PLAYER_LARI, engine);
		//player_eva				= new GameAnim(ANIM_PLAYER_NABRAK, engine);
		//player_eva1				= new GameAnim(ANIM_PLAYER_DOUBLEJUMP, engine);
		
		button			= new GameSprite(BUTTON, engine);
		button_menu 	= new GameSprite(BUTTON_MENU, engine);
		menu			= new GameSprite(MENU, engine);
		
		status 			= new GameText("0", 1, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		engine.camera.setCenter(GameEngine.cameraWidth / 2, GameEngine.cameraHeight / 2);
		
		player_run.animate(SPEED_ANIM, true);
		//player_eva.animate(SPEED_ANIM, true);
		//player_eva1.animate(SPEED_ANIM, true);
		
		button.setAlpha(0f);
		menu.setVisible(false);
		button_menu.setVisible(false);
		pointer.setAlpha(DISAPPEAR);
		
		jump 			= false;
		jump_player		= NETRAL;
		move_player 	= UP;
		speed_decrease 	= 0;
		speed_jump 			= 4.25f;
		i=0;
	}

	@Override
	protected void attach() 
	{
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_tengah[loop]);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			engine.scene.attachChild(bg_floor_depan[loop]);
		}
		
		engine.scene.attachChild(pointer);
		pointer.attachChild(player_run);
		//engine.scene.attachChild(player_eva);
		//engine.scene.attachChild(player_eva1);
		
		engine.hud.attachChild(menu);
		engine.hud.attachChild(button);	
		menu.attachChild(button_menu);
		engine.hud.attachChild(status);
	}

	@Override
	protected void detach()
	{		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop].detachSelf();
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop].detachSelf();
		}
		
		pointer.detachSelf();
		
		player_run.detachSelf();
		//player_eva.detachSelf();
		//player_eva1.detachSelf();
		
		menu.detachSelf();
		button_menu.detachSelf();
		button.detachSelf();
		status.detachSelf();
	}

	@Override
	protected void setPosition() 
	{		
		bg_tengah[0].setX(0);
		bg_tengah[1].setX(bg_tengah[0].getWidth());
		
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		pointer.setX(PLAYERX);
		pointer.setY(PLAYERY);
		player_run.setPosition(-37,0);
		//player_eva.setPosition(player_run.getX() + player_run.getWidth(),GameEngine.cameraHeight - 180);
		//player_eva1.setPosition(player_eva.getX() + player_eva.getWidth(), GameEngine.cameraHeight - 180);
		
		button_menu.setPosition(Anchor.BOTTOM_CENTER);
		button.setPosition(Anchor.BOTTOM_RIGHT);
		status.setPosition(Anchor.TOP_RIGHT);
		menu.setPosition(Anchor.CENTER);
	}

	@Override
	protected void registerTouch() 
	{
		engine.hud.registerTouchArea(button);
		engine.hud.registerTouchArea(menu);
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.unregisterHudTouch(button);
		engine.unregisterHudTouch(menu);
	}

	@Override
	protected void onUpdate()
	{	
		pointer.setX(pointer.getX() + SPEED);
		
		engine.camera.setCenter(pointer.getX() + GameEngine.cameraWidth / 2, engine.camera.getCenterY());
		
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
		if(jump)
		{
			doubleJump(pointer, PLAYERY, speed_jump, 13);
		}
		
	}

	@Override
	protected void onPaused() 
	{
		menu.setVisible(true);
		player_run.stopAnimation();
	}

	@Override
	protected void onResumed() 
	{
		menu.setVisible(false);
		player_run.animate(SPEED_ANIM, true);
	}

	@Override
	public void onKeyUp(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK) 
		{
			if(menu.isVisible())
			{
				resume();
			}
			else
			{ 	
				pause();
			}
			
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
					jump = true;
					++jump_player;
					status.setText(String.valueOf(jump));
					
					switch (jump_player) {
					case 0:
						break;
					case SINGLE_JUMP:
						if(!menu.isVisible())
						{
							range_up = GameEngine.cameraHeight - 300;
							status.setText(String.valueOf(jump_player));
						}
						break;
					case DOUBLE_JUMP:
						if(!menu.isVisible()){
							range_up = GameEngine.cameraHeight - 400;
							status.setText(String.valueOf(jump_player));
						}
						break;
					default:
					jump_player=NETRAL;
						break;
					}
				}
				else if(pTouchArea == menu)
				{
					if(menu.isVisible()) 
					{
						exitState(STATE_MENU);
					}
				}
			}
			break;
		}
		
		return false;
	}
	
	private void doubleJump(GameSprite spritejump, float rangedown, float speed, int timeup)
	{		
		
		
		switch (move_player) {
		case UP:
			if (speed_decrease == 0){
				speed_decrease = (int) speed;
				speed_decrease--;
			}
			else
			{
				speed_decrease--;
			}
			if(spritejump.getY() <= range_up)
			{
				if(i == timeup){
					i=0;
					move_player = DOWN;
				}
				i++;
			}else {
				spritejump.setY((float) (spritejump.getY() - speed));
			}
			break;
		case DOWN:
			speed_decrease++;
			
			if (spritejump.getY() >= rangedown) {
				jump =  false;
				pointer.setY(PLAYERY);
				jump_player = NETRAL;
				move_player = UP;
			}
				
			spritejump.setY((float) (spritejump.getY() + speed));
			break;
		}
	}
}
