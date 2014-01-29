package com.agd.jb;

import com.agd.jb.state.StateGameMenuJb;
import com.agd.jb.state.StateGamePlayJb;

import lib.engine.GameEngine;
import lib.engine.GameState;

public class MainActivity extends GameEngine
{

	@Override
	protected void gameInit() 
	{
		
	}

	@Override
	protected GameState[] onCreateState() 
	{
		int state_menu = 0;
		int state_play = state_menu + 1;
		
		return new GameState[] 
				{
					new StateGameMenuJb(this),
					new StateGamePlayJb(this),
				};
	}
}
