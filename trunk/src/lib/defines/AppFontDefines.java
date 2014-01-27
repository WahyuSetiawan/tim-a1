package lib.defines;

import lib.element.AppElementFont;
import android.graphics.Typeface;

public interface AppFontDefines 
{
	final static int FONT_SANS_SERIF_S_BLACK = 0;
	final static int FONT_SANS_SERIF_S_BOLD_BLACK = 1;
	final static int FONT_SANS_SERIF_M_BLACK = 2;
	final static int FONT_SANS_SERIF_M_BOLD_BLACK = 3;
	final static int FONT_SANS_SERIF_L_BLACK = 4;
	final static int FONT_SANS_SERIF_L_BOLD_BLACK = 5;
	final static int FONT_SANS_SERIF_S_BLUE = 6;
	final static int FONT_SANS_SERIF_S_BOLD_BLUE = 7;
	final static int FONT_SANS_SERIF_S_BOLD_WHITE = 8;
	final static int FONT_SANS_SERIF_S_BOLD_AQUA = 9;
	final static int FNT_COUNT = 10;
	
	final static AppElementFont FONT[] = {
		new AppElementFont(256, 256, 14, Typeface.SANS_SERIF, Typeface.NORMAL, org.andengine.util.color.Color.BLACK),
		new AppElementFont(256, 256, 14, Typeface.SANS_SERIF, Typeface.BOLD, org.andengine.util.color.Color.BLACK),
		new AppElementFont(256, 256, 16, Typeface.SANS_SERIF, Typeface.NORMAL, org.andengine.util.color.Color.BLACK),
		new AppElementFont(256, 256, 16, Typeface.SANS_SERIF, Typeface.BOLD, org.andengine.util.color.Color.BLACK),
		new AppElementFont(256, 256, 18, Typeface.SANS_SERIF, Typeface.NORMAL, org.andengine.util.color.Color.BLACK),
		new AppElementFont(256, 256, 18, Typeface.SANS_SERIF, Typeface.BOLD, org.andengine.util.color.Color.BLACK),
		new AppElementFont(256, 256, 14, Typeface.SANS_SERIF, Typeface.NORMAL, org.andengine.util.color.Color.BLUE),
		new AppElementFont(256, 256, 14, Typeface.SANS_SERIF, Typeface.BOLD, org.andengine.util.color.Color.BLUE),
		new AppElementFont(256, 256, 14, Typeface.SANS_SERIF, Typeface.BOLD, org.andengine.util.color.Color.WHITE),
		new AppElementFont(256, 256, 14, Typeface.SANS_SERIF, Typeface.BOLD, new org.andengine.util.color.Color(0.42f, 0.77f, 0.98f)),
	};
}
