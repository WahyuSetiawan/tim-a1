package lib.defines;

import lib.element.ElementAnim;

public interface AnimDefines 
{	
	int ANIM_PLAYER_LARI 		= 0;
	int ANIM_PLAYER_LOMPAT 		= ANIM_PLAYER_LARI + 1;
	int ANIM_PLAYER_DOUBLEJUMP	= ANIM_PLAYER_LOMPAT + 1;
	int ANIM_PLAYER_LUBANG		= ANIM_PLAYER_DOUBLEJUMP + 1;
	int ANIM_PLAYER_NABRAK		= ANIM_PLAYER_LUBANG + 1;
	int ANIM_POINT_APPLE		= ANIM_PLAYER_NABRAK +1;
	
	int width  = 128;
	int height = 135;
	
	public final static ElementAnim CONTAINER[] = 
	{
		new ElementAnim(width, height, 8, 1, "gfx/gameplay/player/player_lari.png"),
		new ElementAnim(width, height, 3, 1, "gfx/gameplay/player/player_jump.png"),
		new ElementAnim(width, height, 7, 1, "gfx/gameplay/player/player_double_jump.png"),
		new ElementAnim(width, height, 2, 1, "gfx/gameplay/player/player_lubang.png"),
		new ElementAnim(width, height, 6, 1, "gfx/gameplay/player/player_nabrak.png"),
		new ElementAnim(   80,     55, 8, 1, "gfx/gameplay/point/apple.png"),
	};
}
