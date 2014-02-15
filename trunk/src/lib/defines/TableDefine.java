package lib.defines;

import android.R.integer;
import lib.element.ElementTable;

public interface TableDefine 
{
	int TABLE_SCORE = 0;
	
	ElementTable[] CONTAINER =
	{ 
		new ElementTable("score", new String[]{"Id_Score", "Score"}, new String[]{"0","0"}),
	};
}
