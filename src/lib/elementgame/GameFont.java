package lib.elementgame;

import lib.defines.FontDefines;
import lib.engine.GameEngine;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import android.graphics.Typeface;

public class GameFont extends Font implements FontDefines{

	public GameFont(int index, GameEngine mainActivity) 
	{
		super(mainActivity.getFontManager(), new BitmapTextureAtlas(mainActivity.getTextureManager(), CONTAINER[index].getW(), CONTAINER[index].getW(), CONTAINER[index].getTextureOptions()), Typeface.createFromAsset(mainActivity.getAssets(), CONTAINER[index].getPath()), CONTAINER[index].getSize(), true, CONTAINER[index].getColor());
		this.load();
	}
}
