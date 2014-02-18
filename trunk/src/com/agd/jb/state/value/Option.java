package com.agd.jb.state.value;

public class Option {
	private boolean sound = true;
	private boolean playstart = false;

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	protected boolean isPlaystart() {
		return playstart;
	}

	protected void setPlaystart(boolean playstart) {
		this.playstart = playstart;
	}
	
	
}
