package lib.defines;

import lib.element.ElementTable;

public interface TableDefine 
{
	int TABLE_SCORE  = 0;
	int TABLE_POINT  = TABLE_SCORE + 1;
	int TABLE_HIGHSC = TABLE_POINT + 1;
	int TABLE_OPTION_GAME = TABLE_HIGHSC +1;
	
	ElementTable[] CONTAINER =
	{ 
		new ElementTable("score", new String[]{"Id_Score", "Score"}, new String[]{"0","0"}),
		new ElementTable("Point", new String[]{"Id_Point", "Score_Point"}, new String[]{"0","0"}),
		new ElementTable("HighScore", new String[]{"Id_HighScore", "HighScore"}, new String[]{"0","0"}),
		new ElementTable("OptionGame", new String[]{"sound", "music"}, new String[]{"TRUE","TRUE"}),
	};
}
