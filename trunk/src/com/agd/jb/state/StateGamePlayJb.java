package com.agd.jb.state;

import java.util.Random;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.ScreenCapture;
import org.andengine.entity.util.ScreenGrabber;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;

import com.agd.jb.state.value.ValueAction;
import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValuePlay;
import com.agd.jb.state.value.ValuePlayer;
import com.agd.jb.state.value.StateDefine;

import android.R.integer;
import android.graphics.Paint.Align;
import android.view.KeyEvent;
import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGamePlayJb extends GameState  implements StateDefine, ValueCamera, ValuePlayer, ValueAction, ValuePlay
{
	private GameSprite bg_belakang;
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite pointer;
	private GameSprite pohon;
	private GameSprite pohon2;
	private GameSprite semak;
	private GameSprite semak2;	
	private GameSprite button_menu;
	private GameSprite menu;
	private GameSprite big_rock;
	private GameSprite small_rock;
	private GameSprite fallen_tree;
	
	private GameText distance;
	private GameText score;
	
	private Random num = new Random();
	
	private GameAnim player_run;
	private GameAnim player_jump;
	private GameAnim player_doublejump;
	private GameAnim player_fall;
	private GameAnim player_accident;
	
	private boolean jump;
	private boolean isstart;
	
	private int jump_player;
	private int move_player;
	private int time;
	private int distance_player;
	private int speed_distance;
	
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
		pohon			= new GameSprite(BG_POHON, engine);
		pohon2			= new GameSprite(BG_POHON, engine);
		semak			= new GameSprite(BG_SEMAK, engine);
		semak2			= new GameSprite(BG_SEMAK, engine);
		
		big_rock		= new GameSprite(BIG_ROCK, engine);
		small_rock		= new GameSprite(SMALL_ROCK, engine);
		fallen_tree		= new GameSprite(TREE, engine);
		
		player_run 			= new GameAnim(ANIM_PLAYER_LARI, engine);
		player_accident		= new GameAnim(ANIM_PLAYER_NABRAK, engine);
		player_jump			= new GameAnim(ANIM_PLAYER_LOMPAT, engine);
		player_doublejump	= new GameAnim(ANIM_PLAYER_DOUBLEJUMP, engine);
		player_fall			= new GameAnim(ANIM_PLAYER_LUBANG, engine);
		
		button_menu 	= new GameSprite(BUTTON_MENU, engine);
		menu			= new GameSprite(MENU, engine);
		
		distance 			= new GameText("0", 15, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
	}

	@Override
	protected void init() 
	{
		new ScreenCapture();
		
		engine.camera.setCenter(CAMERA_CENTER_X, CAMERA_CENTER_Y);
		
		player_run.animate(SPEED_ANIM, true);
		//player_fall.animate(SPEED_ANIM, true);
		//player_accident.animate(SPEED_ANIM, true);
		
		player_run.setVisible(true);
		player_fall.setVisible(false);
		player_jump.setVisible(false);
		player_doublejump.setVisible(false);
		player_accident.setVisible(false);
		
		menu.setVisible(false);
		button_menu.setVisible(false);
		pointer.setAlpha(DISAPPEAR);
		
		isstart			= false;
		jump 			= false;
		jump_player		= NETRAL;
		move_player 	= UP;
		speed_decrease 	= 0;
		speed_jump 		= SPEED_JUMP;
		time			= 0;
		distance_player	= 0;
		
		pohon.setX(engine.camera.getXMin());
		pohon2.setX(engine.camera.getXMin());
		semak.setX(engine.camera.getXMin());
		semak2.setX(engine.camera.getXMin());
		
		big_rock.setX(engine.camera.getXMin());
		small_rock.setX(engine.camera.getXMin());
		fallen_tree.setX(engine.camera.getXMin());
		
	}

	@Override
	protected void attach() 
	{
		engine.scene.attachChild(bg_belakang);
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			engine.scene.attachChild(bg_tengah[loop]);
		}
		
		engine.scene.attachChild(pohon);
		engine.scene.attachChild(semak);
		engine.scene.attachChild(pohon2);
		engine.scene.attachChild(semak2);
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			engine.scene.attachChild(bg_floor_depan[loop]);
		}
		
		engine.scene.attachChild(big_rock);
		engine.scene.attachChild(small_rock);
		engine.scene.attachChild(fallen_tree);
		
		engine.scene.attachChild(pointer);
		pointer.attachChild(player_run);
		pointer.attachChild(player_accident);
		pointer.attachChild(player_fall);
		pointer.attachChild(player_jump);
		pointer.attachChild(player_doublejump);
		
		engine.hud.attachChild(menu);
		menu.attachChild(button_menu);
		
		engine.hud.attachChild(distance);
	}

	@Override
	protected void detach()
	{		
		bg_belakang.detachSelf();
		for (int loop = 0; loop < bg_tengah.length; loop++)
		{
			bg_tengah[loop].detachSelf();
		}
		
		pohon.detachSelf();
		semak.detachSelf();
		pohon2.detachSelf();
		semak2.detachSelf();
		
		for (int loop = 0; loop < bg_floor_depan.length; loop++)
		{
			bg_floor_depan[loop].detachSelf();
		}
		
		big_rock.detachSelf();
		small_rock.detachSelf();
		fallen_tree.detachSelf();
		
		pointer.detachSelf();
		
		player_run.detachSelf();
		player_fall.detachSelf();
		player_jump.detachSelf();
		player_doublejump.detachSelf();
		player_accident.detachSelf();
		
		menu.detachSelf();
		button_menu.detachSelf();
		distance.detachSelf();
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
		
		speed_distance++;
		
		if(speed_distance == SPEED_DISTANCE){
			distance_player++;
			distance.setText(String.valueOf(distance_player));
			speed_distance = 0;
		}
		
		/*if(distance.getX() + distance.getWidth() < engine.camera.getXMax()){
			distance.setX(engine.camera.getXMax() - distance.getWidth());
		}*/
		//distance.setX(engine.camera.getXMax()-distance.getWidth());
		
		engine.camera.setCenter(pointer.getX() + GameEngine.cameraWidth / 2 - 60, engine.camera.getCenterY());
		
		if (bg_tengah[0].getX() + bg_tengah[0].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_tengah[0].setX(bg_tengah[1].getX() + bg_tengah[1].getWidth());
		}else if (bg_tengah[1].getX() + bg_tengah[1].getWidth() < engine.camera.getCenterX() - GameEngine.cameraWidth / 2)
		{
			bg_tengah[1].setX(bg_tengah[0].getX() + bg_tengah[0].getWidth());
		}
		
		
		switch (num.nextInt(50)) {
		case 1:
			if(!(engine.camera.getXMin() - pohon.getWidth()< pohon.getX())&& !(pohon.getX()>engine.camera.getXMax()))
			{
				pohon.setPosition(engine.camera.getXMax(), -25, Anchor.CENTER_LEFT);
			}
			break;
		case 2:
			if(!(engine.camera.getXMin() - semak.getWidth()< semak.getX())&& !(semak.getX()>engine.camera.getXMax()))
			{
				semak.setPosition(engine.camera.getXMax(), -25, Anchor.BOTTOM_LEFT);
			}
			break;
		case 3:
			if(!(engine.camera.getXMin() - pohon2.getWidth()< pohon2.getX())&& !(pohon2.getX()>engine.camera.getXMax()))
			{
				pohon2.setPosition(engine.camera.getXMax(), -25, Anchor.CENTER_RIGHT);
			}
			break;
		case 4:
			if(!(engine.camera.getXMin() - semak2.getWidth()< semak2.getX())&& !(semak2.getX()>engine.camera.getXMax()))
			{
				semak2.setPosition(engine.camera.getXMax(), -25, Anchor.BOTTOM_CENTER);
			}
			break;
		default:
			break;
		}
		
		switch (num.nextInt(50)) {
		case 1:
			if(!(engine.camera.getXMin() - big_rock.getWidth()< big_rock.getX())&& !(big_rock.getX()>engine.camera.getXMax()))
			{
				big_rock.setPosition(engine.camera.getXMax(), -50, Anchor.BOTTOM_RIGHT);
			}
			break;
		case 2:
			if(!(engine.camera.getXMin() - small_rock.getWidth()< small_rock.getX())&& !(small_rock.getX()>engine.camera.getXMax()))
			{
				small_rock.setPosition(engine.camera.getXMax(), -50, Anchor.BOTTOM_LEFT);
			}
			break;
		case 3:
			if(!(engine.camera.getXMin() - fallen_tree.getWidth()< fallen_tree.getX())&& !(fallen_tree.getX()>engine.camera.getXMax()))
			{
				fallen_tree.setPosition(engine.camera.getXMax(), -50, Anchor.BOTTOM_CENTER);
			}
			break;
		/*case 4:
			if(!(engine.camera.getXMin() - semak2.getWidth()< semak2.getX())&& !(semak2.getX()>engine.camera.getXMax()))
			{
				semak2.setPosition(engine.camera.getXMax(), 25, Anchor.BOTTOM_CENTER);
			}
			break;*/
		default:
			break;
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
	}

	@Override
	protected void onPaused() 
	{
		menu.setVisible(true);
		button_menu.setVisible(true);
		player_run.stopAnimation();
	}

	@Override
	protected void onResumed() 
	{
		menu.setVisible(false);
		button_menu.setVisible(false);
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
				if(pTouchArea == bg_belakang && !menu.isVisible() && isstart)
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
				else if(pTouchArea == bg_belakang && !menu.isVisible() && !isstart){
					isstart = true;
				}
				else if(pTouchArea == menu && menu.isVisible())
				{
					isstart = false;
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
