package com.agd.jb.state.value;

public interface ValueFlagObs {
	int FALLPOHON 		= 0;
	int BIGROCK 		= FALLPOHON 	+ 1;
	int LITTLEROCK 		= BIGROCK 		+ 1;
	int HOLE			= LITTLEROCK 	+ 1;
	int DISABLE 		= HOLE 			+ 1;
	
	public static float STARTOBS 	= 900f;
	
	public static int[][] FLAGOBS = {
		{FALLPOHON, DISABLE, BIGROCK},
		//{DISABLE, DISABLE, DISABLE},
		{LITTLEROCK, BIGROCK,FALLPOHON},
		{BIGROCK, DISABLE, FALLPOHON},
		{FALLPOHON,LITTLEROCK,DISABLE},
		};
}
