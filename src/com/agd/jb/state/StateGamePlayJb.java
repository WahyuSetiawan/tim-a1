package com.agd.jb.state;

import java.util.Random;

import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.util.ScreenCapture;
import org.andengine.input.touch.TouchEvent;

import com.agd.jb.state.value.ValueActionJump;
import com.agd.jb.state.value.ValueCamera;
import com.agd.jb.state.value.ValuePlay;
import com.agd.jb.state.value.ValuePlayer;
import com.agd.jb.state.value.StateDefine;
import com.agd.jb.state.value.ValueFlagObs;

import android.R.id;
import android.view.KeyEvent;

import lib.defines.GameEngineConfiguration;
import lib.elementgame.GameAnim;
import lib.elementgame.GameSprite;
import lib.elementgame.GameText;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.GameState;

public class StateGamePlayJb extends GameState  implements StateDefine, ValueCamera, ValuePlayer, ValueActionJump, ValuePlay, ValueFlagObs
{
	private GameSprite bg_belakang;
	private GameSprite[] bg_floor_depan = new GameSprite[2];
	private GameSprite[] bg_tengah		= new GameSprite[2];
	private GameSprite[][] obstacle 	= new GameSprite[2][3];
	private GameSprite pointer;
	private GameSprite pohon;
	private GameSprite pohon2;
	private GameSprite semak;
	private GameSprite semak2;	
	private GameSprite button_menu;
	private GameSprite menu;
	
	private GameText distance;
	private GameText score;
	private GameText status;
	
	private Random num = new Random();
	
	private GameAnim player_run;
	private GameAnim player_jump;
	private GameAnim player_doublejump;
	private GameAnim player_fall;
	private GameAnim player_accident;
	
	private boolean jump;
	private boolean isstart;
	private int selectobs;
	
	private int jump_player;
	private int move_player;
	private int obsfirst;
	private int obssecound;
	private int time;
	private int distance_player;
	private int speed_distance;
	
	private float speed_jump;
	private int speed_decrease;
	private float range_up;
	private float flagout;
	
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
		
		for(int loop = 0; loop < obstacle.length;loop++ ){
			obstacle[loop][0]		= new GameSprite(BIG_ROCK, engine);
			obstacle[loop][1]		= new GameSprite(SMALL_ROCK, engine);
			obstacle[loop][2]		= new GameSprite(TREE, engine);
		}
		
		player_run 			= new GameAnim(ANIM_PLAYER_LARI, engine);
		player_accident		= new GameAnim(ANIM_PLAYER_NABRAK, engine);
		player_jump			= new GameAnim(ANIM_PLAYER_LOMPAT, engine);
		player_doublejump	= new GameAnim(ANIM_PLAYER_DOUBLEJUMP, engine);
		player_fall			= new GameAnim(ANIM_PLAYER_LUBANG, engine);
		
		button_menu 	= new GameSprite(BUTTON_MENU, engine);
		menu			= new GameSprite(MENU, engine);
		
		distance 	= new GameText("0", 15, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
		status		= new GameText("0", 10, engine.getFont(FONT_ANIMEACE2_ITAL2), engine);
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
		
		flagout			= STARTOBS;
		selectobs		= 0;
		isstart			= false;
		jump 			= false;
		jump_player		= NETRAL;
		move_player 	= UP;
		speed_decrease 	= 0;
		speed_jump 		= SPEED_JUMP;
		time			= 0;
		distance_player	= 0;			
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
		
		for(int loop = 0; loop < obstacle.length;loop++ ){
			engine.scene.attachChild(obstacle[loop][0]);
			engine.scene.attachChild(obstacle[loop][1]);
			engine.scene.attachChild(obstacle[loop][2]);
		}
		
		engine.scene.attachChild(pointer);
		pointer.attachChild(player_run);
		pointer.attachChild(player_accident);
		pointer.attachChild(player_fall);
		pointer.attachChild(player_jump);
		pointer.attachChild(player_doublejump);
		
		engine.hud.attachChild(menu);
		menu.attachChild(button_menu);
		
		engine.hud.attachChild(distance);
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
		
		pohon.detachSelf();
		semak.detachSelf();
		pohon2.detachSelf();
		semak2.detachSelf();
		
		for(int loop = 0; loop < obstacle.length;loop++ ){
			obstacle[loop][0].detachSelf();
			obstacle[loop][1].detachSelf();
			obstacle[loop][2].detachSelf();
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
		distance.detachSelf();
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
		
		float flagbottompointer = pointer.getY() + (pointer.getHeight() /2);
		
		for(int loop = 0; loop < obstacle.length;loop++ ){
			obstacle[loop][0].setPosition(engine.camera.getXMin() - GameEngineConfiguration.masterWidth, flagbottompointer - (obstacle[loop][0].getHeight()/2));
			obstacle[loop][1].setPosition(engine.camera.getXMin() - GameEngineConfiguration.masterWidth, flagbottompointer - (obstacle[loop][1].getHeight()/2));
			obstacle[loop][2].setPosition(engine.camera.getXMin() - GameEngineConfiguration.masterWidth, flagbottompointer - (obstacle[loop][2].getHeight()/2));
		}
		
		pointer.setPosition(PLAYERX, PLAYERY);
		
		player_run.setPosition(-37, -18);
		player_fall.setPosition(-37, -18);
		player_jump.setPosition(-37, -18);
		player_doublejump.setPosition(-37, -18);
		player_accident.setPosition(-37, -18);
		
		button_menu.setPosition(Anchor.BOTTOM_CENTER);
		menu.setPosition(Anchor.CENTER);
		
		pohon.setX(engine.camera.getXMin());
		pohon2.setX(engine.camera.getXMin());
		semak.setX(engine.camera.getXMin());
		semak2.setX(engine.camera.getXMin());
		
		status.setPosition(Anchor.TOP_RIGHT);
		
		
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
		
		
		switch (num.nextInt(75)) {
		case 1:
			if(!(engine.camera.getXMin() - pohon.getWidth()< pohon.getX())&& !(pohon.getX()>engine.camera.getXMax()))
			{
				pohon.setPosition(engine.camera.getXMax(), 0, Anchor.CENTER_LEFT);
			}
			break;
		case 2:
			if(!(engine.camera.getXMin() - semak.getWidth()< semak.getX())&& !(semak.getX()>engine.camera.getXMax()))
			{
				semak.setPosition(engine.camera.getXMax(), 25, Anchor.BOTTOM_LEFT);
			}
			break;
		case 3:
			if(!(engine.camera.getXMin() - pohon2.getWidth()< pohon2.getX())&& !(pohon2.getX()>engine.camera.getXMax()))
			{
				pohon2.setPosition(engine.camera.getXMax(), 0, Anchor.CENTER_RIGHT);
			}
			break;
		case 4:
			if(!(engine.camera.getXMin() - semak2.getWidth()< semak2.getX())&& !(semak2.getX()>engine.camera.getXMax()))
			{
				semak2.setPosition(engine.camera.getXMax(), 25, Anchor.BOTTOM_CENTER);
			}
			break;
		}
		
		if(engine.camera.getXMax() > flagout)
		{
			flagout = flagout + setFlag(obstacle, num.nextInt(FLAGOBS[0].length - 1), FLAGOBS);
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
			if(isPaused())
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
			{
				
			}
			break;
		case TouchEvent.ACTION_UP:
			{
				if(pTouchArea == bg_belakang && !isPaused() && isstart)
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
				else if(pTouchArea == bg_belakang && !isPaused() && !isstart){
					isstart = true;
				}
				else if(pTouchArea == menu && isPaused())
				{
					isstart = false;
					exitState(STATE_MENU);
				}
			}
			
			break;
		}
		return false;
	}
	
	private float setFlag(GameSprite[][] sprites, int flag, int[][] flagobs)
  	{
		int i = 0;float j = 0, r = 0;
		
		if(obsfirst == flagobs[0].length){
			flag--;
		}
		else 
		if(obsfirst == flag){
				
			flag++;
		}
		
		obsfirst = flag;
		
		status.setText(String.valueOf(obstacle.length));
		
		while(i < flagobs[flag].length)
		{
			r = (300 * (i + 1)) + num.nextInt(75);
			
			if(!(flagobs[flag][i] == DISABLE))
			{
				sprites[selectobs][flagobs[flag][i]].setX(flagout + r);
			}
			j =+ r;
			i++;
		}
		++selectobs;
		
		if(selectobs >= obstacle.length)selectobs=0;
		return j;
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
			
			spritejump.setY((float) (spritejump.getY() + speed));
			
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
				
			
			break;
		}
	}
}
