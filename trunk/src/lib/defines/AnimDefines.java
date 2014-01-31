package lib.defines;

import lib.element.ElementAnim;

public interface AnimDefines 
{	
	int ANIM_PLAYER_LARI 		= 0;
	int ANIM_PLAYER_LOMPAT 		= ANIM_PLAYER_LARI + 1;
	int ANIM_PLAYER_DOUBLEJUMP	= ANIM_PLAYER_LOMPAT + 1;
	int ANIM_PLAYER_LUBANG		= ANIM_PLAYER_DOUBLEJUMP + 1;
	int ANIM_PLAYER_NABRAK		= ANIM_PLAYER_LUBANG + 1;
	
	public final static ElementAnim CONTAINER[] = 
	{
		new ElementAnim(9, 1, "gfx/gameplay/player/player_lari.png"),
		new ElementAnim(3, 1, "gfx/gameplay/player/player_jump.png"),
		new ElementAnim(7, 1, "gfx/gameplay/player/player_double_jump.png"),
		new ElementAnim(2, 1, "gfx/gameplay/player/player_lubang.png"),
		new ElementAnim(6, 1, "gfx/gameplay/player/player_nabrak.png"),
	};
}
