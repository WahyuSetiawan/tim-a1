package com.agd.jb.state.value;

public interface ValueAction {
	static final int NETRAL 		= 0;
	static final int UP 			= 1;
	static final int DOWN 			= 2;
	
	static final int SINGLE_JUMP 	= 1;
	static final int DOUBLE_JUMP 	= 2;
	
	static final float DISAPPEAR 	= 0f;
	static final float ARISE		= 1f; 
	
	static final float SINGLE_JUMP_RANGE	= 300;
	static final float DOUBLE_JUMP_RANGE 	= 400;
	
	static final float SPEED_JUMP	= 0.33f;
	
	static final float SPEED_DECREASE_INIT = 0f;
}
