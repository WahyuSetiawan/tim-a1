package lib.engine;

import java.text.DecimalFormat;

import lib.defines.FontDefines;
import lib.defines.GameEngineConfiguration;
import lib.element.ElementTable;
import lib.elementgame.GameDatabase;
import lib.elementgame.GameFont;
import lib.elementgame.GameText;
import lib.elementgame.Mfx;
import lib.elementgame.Sfx;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSCounter;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;

import android.graphics.Typeface;
import android.os.Handler;

public abstract class GameEngine extends SimpleBaseGameActivity implements IUpdateHandler, IOnSceneTouchListener
{
	// ===========================================================
	// Fields
	// ===========================================================
	public static float cameraWidth, cameraHeight;
	
	public BoundCamera boundCamera;
	public Camera camera;
	public HUD hud;
	
	public Scene scene;
	
	boolean gamePaused;
	
	private boolean gameInitialized;
	
	public Font font;
	
	private GameFont gameFont[] = new GameFont[FontDefines.CONTAINER.length];
	
	private GameState[] states = onCreateState();
	
	private int currState;
	
	public final Handler handler = new Handler();
	
	private FPSCounter fps	= new FPSCounter();
	
	private GameDatabase dbase;
	
	public EngineOptions onCreateEngineOptions() 
	{
		processScreenOrientation();
		
		this.boundCamera = new BoundCamera(0, 0, cameraWidth, cameraHeight);
		this.camera = new Camera(0, 0, cameraWidth, cameraHeight);
		hud = new HUD();
		final EngineOptions engineOptions = new EngineOptions(GameEngineConfiguration.isFullScreen, GameEngineConfiguration.screenOrientation, new FillResolutionPolicy(), GameEngineConfiguration.useUsualCamera?camera:boundCamera);
		
		if(GameEngineConfiguration.useUsualCamera)
		{
			camera.setHUD(hud);
		}
		else
		{
			boundCamera.setHUD(hud);
		}
		
		
		engineOptions.getAudioOptions().setNeedsSound(GameEngineConfiguration.useSound);
		engineOptions.getAudioOptions().setNeedsMusic(GameEngineConfiguration.useMusic);
		onCreateEngine(engineOptions);
		
		return engineOptions;
	}

	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) 
	{
		if(GameEngineConfiguration.useFpsLimiter)
			return new LimitedFPSEngine(pEngineOptions, GameEngineConfiguration.fpsLimit);
		
		return super.onCreateEngine(pEngineOptions);
	}
	
    @Override
    protected synchronized void onCreateGame() 
    {
    	if(GameEngineConfiguration.useVibration)
    		mEngine.enableVibrator(this);
    	
    	super.onCreateGame();
    }
	
	@Override
	protected void onCreateResources() 
	{
		gameInitialized = false;
		
		loadAllFont();
		Sfx.loadAllSfx(this);
		Mfx.loadAllMfx(this);
		
		createSystemFont();
		
		ElementTable[] tables = onCreateTables();
		if(tables != null)
		{
			dbase = new GameDatabase(this, tables);
		}
	}
	
	@Override
	protected Scene onCreateScene() 
	{
		scene = new Scene();
		
		if(GameEngineConfiguration.useGameUpdate)
		{
			scene.registerUpdateHandler(this);
		}
		
		if(GameEngineConfiguration.useTouchScene)
		{
			scene.setOnSceneTouchListener(this);
		}
		
		if(GameEngineConfiguration.showFpsCounter)
		{
			final GameText txtFps = new GameText("", 20, font, this);
			
			mEngine.registerUpdateHandler(fps);
			
			hud.attachChild(txtFps);
			
			hud.registerUpdateHandler(new TimerHandler(0.5f, true, new ITimerCallback() 
			{	
				public void onTimePassed(TimerHandler pTimerHandler) 
				{
					txtFps.setText(new DecimalFormat("##.##").format(fps.getFPS()) + " fps");
				}
			}));
		}
		
		return scene;
	}
	
	@Override
	public synchronized void onPauseGame() 
	{
		gamePaused = true;
		
		super.onPauseGame();
		
	}
	
	@Override
	public synchronized void onResumeGame()
	{
		super.onResumeGame();
		
	}
	
	void processScreenOrientation()
	{
		cameraWidth = GameEngineConfiguration.masterWidth;
		cameraHeight = GameEngineConfiguration.masterHeight;
	}
	
	void createSystemFont()
	{
		font = FontFactory.create(this.getFontManager(), this.getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 28, Color.ARGB_PACKED_GREEN_CLEAR);
		font.load();
	}
	
	public void onUpdate(float pSecondsElapsed) 
	{
		if(!gameInitialized)
		{
			gameInit();
			gameInitialized = true;
			currState = 0;
			
			for (GameState state : states) 
			{
				state.initComponent();
			}
			
			return;
		}
		states[currState].update();
	}
	
	public void reset() {}
	
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
	{
		states[currState].onSceneTouched(pScene, pSceneTouchEvent);
		return false;
	}
	
	public void endGame()
	{
		System.exit(0);
	}
	
	private void loadAllFont()
	{
		for(int i = 0; i < gameFont.length; i++)
		{
			gameFont[i] = new GameFont(i, this);
			gameFont[i].load();
		}
	}
	
	public void unregisterSceneTouch(final ITouchArea touchArea)
	{
		runOnUpdateThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				scene.unregisterTouchArea(touchArea);
			}
		});
	}
	
	public void unregisterHudTouch(final ITouchArea touchArea)
	{
		runOnUpdateThread(new Runnable() 
		{
			@Override
			public void run() 
			{
				hud.unregisterTouchArea(touchArea);
			}
		});
	}
	
	public void vibrate(long milis)
	{
		if(!GameEngineConfiguration.useVibration) return;
			mEngine.vibrate(milis);
	}
	
	public void vibrate(long[] pPattern, int pRepeat) 
	{
		if(!GameEngineConfiguration.useVibration) return;
			mEngine.vibrate(pPattern, pRepeat);
	}

	public void changeState(int nextState) 
	{
		currState = nextState;
	}
	
	public GameFont getFont(int index)
	{
		try 
		{
			return gameFont[index];
		} catch (ArrayIndexOutOfBoundsException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public FPSCounter getFps() 
	{
		return fps;
	}
	
	public GameDatabase getDbase() 
	{
		return dbase;
	}
	
	abstract protected void gameInit();
	
	abstract protected GameState[] onCreateState();
	
	abstract protected ElementTable[] onCreateTables();
}
