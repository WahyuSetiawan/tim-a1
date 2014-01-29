package lib.defines;


import lib.element.ElementSprite;

public interface SpriteDefines 
{
	int BG_DEPAN_STATIC = 0;
	int BG_MENU_FILL 	= BG_DEPAN_STATIC + 1;
	int BG_MENU 		= BG_MENU_FILL + 1;
	
	public final static ElementSprite CONTAINER[] = 
	{
		new ElementSprite("gfx/gameplay/bg_depan_static.png"),
		new ElementSprite("gfx/menu/bg_fill.png"),
		new ElementSprite("gfx/menu/bg_menu.png"),
	};
}