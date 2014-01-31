package lib.defines;

import lib.element.ElementFont;

import org.andengine.util.color.Color;

public interface FontDefines 
{	
	int FONT_ANIMEACE2_ITAL = 0;
	int FONT_ANIMEACE2_ITAL2 = FONT_ANIMEACE2_ITAL + 1;

	final static ElementFont CONTAINER[] = 
	{	
		new ElementFont("font/animeace2_ital.ttf", 61, Color.RED),
		new ElementFont("font/animeace2_ital.ttf", 10, Color.GREEN),
	};
}
