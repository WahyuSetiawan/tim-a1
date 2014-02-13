package lib.defines;

import android.R.integer;
import lib.element.ElementTable;

public interface TableDefine 
{
	int TABLE_SCORE = 0;
	
	ElementTable[] CONTAINER =
	{ 
		new ElementTable("Highscore", new String []{"Highscore"}, new String []{"0"}),
	};
}
