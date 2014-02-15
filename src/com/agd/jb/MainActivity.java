package com.agd.jb;

import com.agd.jb.state.StateGameMenuJb;
import com.agd.jb.state.StateGamePlayJb;
import com.agd.jb.state.StateLoading;

import lib.element.ElementTable;
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
		return new GameState[] 
				{
					new StateLoading(this),
					new StateGameMenuJb(this),
					new StateGamePlayJb(this),
				};
	}
}
