package lib.elementgame;

import lib.engine.Anchor;
import lib.engine.GameEngine;
import lib.engine.Utils;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;



public class GameText extends Text 
{	
	public GameText(CharSequence text, int textLength, Font font, GameEngine mainActivity)
	{
		this( -1, text, textLength, new TextOptions(), mainActivity, true, font);
	}
	
	public GameText(CharSequence text, int textLength, int fontIndex, GameEngine mainActivity)
	{
		this(fontIndex, text, textLength, new TextOptions(), mainActivity, true, null);
	}
	
	public GameText(CharSequence text, int fontIndex, GameEngine mainActivity)
	{
		this(fontIndex, text, text.length(), new TextOptions(), mainActivity, true, null);
	}
	
	public GameText(int fontIndex, CharSequence pText, int maxLength, TextOptions pTextOptions, GameEngine mainActivity, boolean useRatio, Font font) {
		super( 0, 0, font==null?getFont(fontIndex, mainActivity):font, pText, maxLength, pTextOptions, mainActivity.getVertexBufferObjectManager());
	}

	static Font getFont(int index, GameEngine mainActivity)
	{
		if(index < 0)
		{
			return mainActivity.getFont(index);
		}
		return null;
	}
	
	public void setPosition(float pX, float pY)
	{
		super.setPosition(pX, pY);
	}
	
	public void setPosition(Anchor anchor)
	{
		if(this.getParent() == null)
		{
			Utils.debug("not attached to any entity yet, attach it first");
			return;
		}
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
}
