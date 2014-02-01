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
	private GameSprite bg_belakang;
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite pointer;
	
	private GameSprite button_menu;
	private GameSprite menu;
	
	private GameText status;
	
	private GameAnim player_run;
	private GameAnim player_jump;
	private GameAnim player_doublejump;
	private GameAnim player_fall;
	private GameAnim player_accident;
	
	private boolean jump;
	private int jump_player;
	private int move_player;
	private int time;
	
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
		bg_belakang		= new GameSprite(BG_BELAKANG, engine);
		
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop] = new GameSprite(BG_TENGAH, engine);
		}
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop] = new GameSprite(BG_FLOOR_DEPAN, engine);
		}
		
		pointer 		= new GameSprite(POINTER, engine);
		
		player_run 			= new GameAnim(ANIM_PLAYER_LARI, engine);
		player_accident		= new GameAnim(ANIM_PLAYER_NABRAK, engine);
		player_jump			= new GameAnim(ANIM_PLAYER_LOMPAT, engine);
		player_doublejump	= new GameAnim(ANIM_PLAYER_DOUBLEJUMP, engine);
		player_fall			= new GameAnim(ANIM_PLAYER_LUBANG, engine);
		
		button_menu 	= new GameSprite(BUTTON_MENU, engine);
		menu			= new GameSprite(MENU, engine);
		
		status 			= new GameText("0", 1, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
		
		player_run.animate(SPEED_ANIM, true);
		player_fall.animate(SPEED_ANIM, true);
		player_accident.animate(SPEED_ANIM, true);
		
		player_run.setVisible(true);
		player_fall.setVisible(false);
		player_jump.setVisible(false);
		player_doublejump.setVisible(false);
		player_accident.setVisible(false);
		
		menu.setVisible(false);
		button_menu.setVisible(false);
		pointer.setAlpha(DISAPPEAR);
		
		jump 			= false;
		jump_player		= NETRAL;
		move_player 	= UP;
		speed_decrease 	= 0;
		speed_jump 		= 4.25f;
		time=0;
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
		
		engine.scene.attachChild(pointer);
		pointer.attachChild(player_run);
		pointer.attachChild(player_accident);
		pointer.attachChild(player_fall);
		pointer.attachChild(player_jump);
		pointer.attachChild(player_doublejump);
		
		engine.hud.attachChild(menu);
		menu.attachChild(button_menu);
		
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
		
		pointer.detachSelf();
		
		player_run.detachSelf();
		player_fall.detachSelf();
		player_jump.detachSelf();
		player_doublejump.detachSelf();
		player_accident.detachSelf();
		
		menu.detachSelf();
		button_menu.detachSelf();
		status.detachSelf();
	}

	@Override
	protected void setPosition() 
	{
		bg_belakang.setPosition(139,0);
		
		bg_tengah[0].setX(0);
		bg_tengah[1].setX(bg_tengah[0].getWidth());
		
		bg_floor_depan[0].setX(0);
		bg_floor_depan[1].setX(bg_floor_depan[0].getWidth());
		
		pointer.setX(PLAYERX);
		pointer.setY(PLAYERY);
		
		player_run.setPosition(-37,0);
		player_fall.setPosition(-37,0);
		player_jump.setPosition(-37,0);
		player_doublejump.setPosition(-37,0);
		player_accident.setPosition(-37,0);
		
		button_menu.setPosition(Anchor.BOTTOM_CENTER);
		status.setPosition(Anchor.TOP_RIGHT);
		menu.setPosition(Anchor.CENTER);
	}

	@Override
	protected void registerTouch() 
	{
		engine.scene.registerTouchArea(bg_belakang);
		engine.hud.registerTouchArea(menu);
	}

	@Override
	protected void unregisterTouch() 
	{
		engine.unregisterSceneTouch(bg_belakang);
		engine.unregisterHudTouch(menu);
	}

	@Override
	protected void onUpdate()
	{	
		bg_belakang.setX(bg_belakang.getX() + SPEED);
		pointer.setX(pointer.getX() + SPEED);
		
		engine.camera.setCenter(pointer.getX() + GameEngine.cameraWidth / 2 - 60, engine.camera.getCenterY());
		
		if (bg_tengah[0].getX() + bg_tengah[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_tengah[0].setX(bg_tengah[1].getX() + bg_tengah[1].getWidth());
		}else if (bg_tengah[1].getX() + bg_tengah[1].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_tengah[1].setX(bg_tengah[0].getX() + bg_tengah[0].getWidth());
		}
		
		if (bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_floor_depan[0].setX(bg_floor_depan[1].getX()+ bg_floor_depan[1].getWidth());
		}else if (bg_floor_depan[1].getX() + bg_floor_depan[1].getWidth() < engine.camera.getCenterX()- GameEngine.cameraWidth / 2)
		{
			bg_floor_depan[1].setX(bg_floor_depan[0].getX() + bg_floor_depan[0].getWidth());
		}
		
		if(jump)
		{
			doubleJump(pointer, PLAYERY, speed_jump, 13);
		}
		status.setText(String.valueOf(move_player));
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
				if(pTouchArea == bg_belakang && !menu.isVisible())
				{
					jump = true;
					++jump_player;
					
					switch (jump_player) {
					case 0:
						break;
					case SINGLE_JUMP:
							player_run.setVisible(false);
							player_jump.animate(DURATION_SINGLE, false);
							player_jump.setVisible(true);
							range_up = GameEngine.cameraHeight - SINGLE_JUMP_RANGE;
						
						break;
					case DOUBLE_JUMP:
							if(player_jump.isVisible() && move_player == UP){
								player_jump.setVisible(false);
								player_doublejump.animate(DURATION_DOUBLE, false);
								player_doublejump.setVisible(true);
							}
							range_up = GameEngine.cameraHeight - DOUBLE_JUMP_RANGE;
						break;
					default:
						jump_player=SINGLE_JUMP;
						break;
					}
				}
				else if(pTouchArea == menu && menu.isVisible())
				{
					exitState(STATE_MENU);
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
				if(time == timeup){
					time=0;
					move_player = DOWN;
				}
				time++;
			}else {
				spritejump.setY((float) (spritejump.getY() - speed));
			}
			break;
		case DOWN:
			speed_decrease++;
			
			if (spritejump.getY() >= rangedown) {
				if(player_jump.isVisible()){
					player_jump.setVisible(false);
				}else{
					player_doublejump.setVisible(false);
				}
				player_run.setVisible(true);
				jump =  false;
				spritejump.setY(PLAYERY);
				jump_player = NETRAL;
				move_player = UP;
			}
				
			spritejump.setY((float) (spritejump.getY() + speed));
			break;
		}
	}
}
