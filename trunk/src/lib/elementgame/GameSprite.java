package lib.elementgame;

import java.io.IOException;
import java.io.InputStream;

import lib.defines.SpriteDefines;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.Utils;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import android.graphics.BitmapFactory;

public class GameSprite extends Sprite implements SpriteDefines
{
	
	public GameSprite(int index, GameEngine engine) 
	{
		super(0, 0, CONTAINER[index].getW() != 0 ? CONTAINER[index].getW() : getBitmapWidth(engine, index), 
				CONTAINER[index].getH() != 0 ? CONTAINER[index].getH() : getBitmapHeight(engine, index), 
				getTextureRegion(engine, index).deepCopy(), engine.getVertexBufferObjectManager());
		this.detachSelf(engine);
	}

	private static ITextureRegion getTextureRegion(final GameEngine engine, final int index)
	{
		ITexture texture = null;
		try 
		{
			texture = new BitmapTexture(engine.getTextureManager(), new IInputStreamOpener() {
				
				public InputStream open() throws IOException 
				{
					return engine.getAssets().open(SpriteDefines.CONTAINER[index].getPath());
				}
			});
			
			texture.load();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return TextureRegionFactory.extractFromTexture(texture);
	}
	
	private static int getBitmapWidth(GameEngine engine, int index)
	{
		try
		{
			return BitmapFactory.decodeStream(engine.getAssets().open(CONTAINER[index].getPath())).getWidth();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	private static int getBitmapHeight(GameEngine engine, int index)
	{
		try
		{
			return BitmapFactory.decodeStream(engine.getAssets().open(CONTAINER[index].getPath())).getHeight();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public void setPosition(Anchor anchor)
	{
		setPosition(0, 0, anchor);
	}
	
	public void setPosition(float pX, float pY, Anchor anchor) 
	{
		if(this.getParent() == null)
		{
			Utils.debug("please attach first...!");
			try
			{
				throw new Exception();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		float pW = 0f, pH = 0f;
		if(this.getParent().getClass().equals(Scene.class) || this.getParent().getClass().equals(HUD.class))
		{
			pW = Utils.getXAnchor(this, GameEngine.cameraWidth, anchor);
			pH = Utils.getYAnchor(this, GameEngine.cameraHeight, anchor);
		}
		else
		{
			pW = Utils.getXAnchor(this, anchor);
			pH = Utils.getYAnchor(this, anchor);
		}
		
		super.setPosition(pW + pX, pH + pY);
	}
	
	public void detachSelf(final GameEngine engine)
	{
		engine.runOnUpdateThread(new Runnable() 
		{	
			public void run() 
			{
				GameSprite.this.detachSelf();
			}
		});
	}
}
