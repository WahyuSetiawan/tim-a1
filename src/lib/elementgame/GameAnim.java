package lib.elementgame;

import java.io.IOException;

import lib.defines.AnimDefines;
import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.Utils;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GameAnim extends AnimatedSprite implements AnimDefines
{	
	private static Bitmap bitmap = null;
	
	public GameAnim(int index, GameEngine engine) 
	{
		super(0, 0, CONTAINER[index].getW() != 0 ? CONTAINER[index].getW() : getBitmapWidth(engine, index), 
				CONTAINER[index].getH() != 0 ? CONTAINER[index].getH() : getBitmapHeight(engine, index), 
				getTiledTextureRegion(engine, index), engine.getVertexBufferObjectManager());
	}
	
	private static TiledTextureRegion getTiledTextureRegion(GameEngine engine, int index)
	{
		
		BuildableBitmapTextureAtlas buildableBitmapTextureAtlas = null;
		TiledTextureRegion tiledTextureRegion = null;
		
		if(bitmap == null)
		{
			try 
			{
				bitmap = BitmapFactory.decodeStream(engine.getAssets().open(CONTAINER[index].getPath()));
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		buildableBitmapTextureAtlas = new BuildableBitmapTextureAtlas(engine.getTextureManager(), Utils.GetBigTwoInt(bitmap.getWidth()), Utils.GetBigTwoInt(bitmap.getHeight()), CONTAINER[index].getTextureOptions());
		tiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buildableBitmapTextureAtlas, engine, CONTAINER[index].getPath(), CONTAINER[index].getCol(), CONTAINER[index].getRow());
		
		try 
		{
			buildableBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			buildableBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) 
		{
			e.printStackTrace();
		}
		
		return tiledTextureRegion;
	}
	
	private static int getBitmapWidth(GameEngine engine, int index)
	{
		if(bitmap == null)
		{
			try 
			{
				bitmap = BitmapFactory.decodeStream(engine.getAssets().open(CONTAINER[index].getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return bitmap.getWidth() / CONTAINER[index].getCol();
	}
	
	private static int getBitmapHeight(GameEngine engine, int index)
	{
		if(bitmap == null)
		{
			try 
			{
				bitmap = BitmapFactory.decodeStream(engine.getAssets().open(CONTAINER[index].getPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return bitmap.getHeight() / CONTAINER[index].getRow();
	}
	
	public boolean isAttached()
	{
		if(this.getParent() != null)
		{
			return true;
		}
		return false;
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
				GameAnim.this.detachSelf();
			}
		});
	}
}
