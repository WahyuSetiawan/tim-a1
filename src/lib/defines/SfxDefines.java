package lib.defines;

import lib.element.ElementSound;

public interface SfxDefines 
{ 
	int SOUND_RUN_GRASS = 0;
	int SOUND_JUMP		= SOUND_RUN_GRASS + 1;
	int SOUND_DBL_JUMP	= SOUND_JUMP + 1;
	
	public final static ElementSound CONTAINER[] = 
	{
		new ElementSound("sfx/running grass wet feet sfx loop2_Master.wav", true),
		new ElementSound("sfx/lompat hap_hap.wav", false),
		new ElementSound("sfx/lompat hup_hup.wav", false),
	};
}
