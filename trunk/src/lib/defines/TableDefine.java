package lib.defines;

import android.R.integer;
import lib.element.ElementTable;

public interface TableDefine 
{
	int TABLE_SCORE  = 0;
	int TABLE_POINT  = TABLE_SCORE + 1;
	int TABLE_HIGHSC = TABLE_POINT + 1;
	
	ElementTable[] CONTAINER =
	{ 
		new ElementTable("score", new String[]{"Id_Score", "Score"}, new String[]{"0","0"}),
		new ElementTable("Point", new String[]{"Id_Point", "Score_Point"}, new String[]{"0","0"}),
		new ElementTable("HighScore", new String[]{"Id_HighScore", "HighScore"}, new String[]{"0","0"}),
	};
}
