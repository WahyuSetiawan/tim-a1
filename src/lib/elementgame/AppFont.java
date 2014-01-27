package lib.elementgame;

import lib.defines.AppFontDefines;
import lib.engine.GameEngine;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;

import android.graphics.Typeface;


public class AppFont extends Font  
{
	public AppFont(int index, GameEngine mainActivity) 
	{
		super(mainActivity.getFontManager(), new BitmapTextureAtlas(mainActivity.getTextureManager(), AppFontDefines.FONT[index].getW(), AppFontDefines.FONT[index].getH(), BitmapTextureFormat.RGBA_8888), Typeface.create(AppFontDefines.FONT[index].getFamily(), AppFontDefines.FONT[index].getStyle()), AppFontDefines.FONT[index].getSize(), true, AppFontDefines.FONT[index].getColor());
	}
}
