package lib.defines;


import lib.element.ElementSprite;

public interface SpriteDefines 
{
	int BG_BELAKANG		= 0;
	int BG_TENGAH		= BG_BELAKANG + 1;
	int BG_FLOOR_DEPAN	= BG_TENGAH +1;
	int BG_POHON		= BG_FLOOR_DEPAN + 1;
	int BG_SEMAK		= BG_POHON + 1;
	int BG_MENU_FILL 	= BG_SEMAK + 1;
	int BG_MENU 		= BG_MENU_FILL + 1;
	int BUTTON			= BG_MENU + 1;
	
	public final static ElementSprite CONTAINER[] = 
	{
		new ElementSprite("gfx/gameplay/bg_belakang.png"),
		new ElementSprite("gfx/gameplay/3. bg_tengah.png"),
		new ElementSprite("gfx/gameplay/bg_floor_depan.png"),
		new ElementSprite("gfx/gameplay/bg_pohon"),
		new ElementSprite("gfx/gameplay/bg_semak"),
		new ElementSprite("gfx/menu/bg_fill.png"),
		new ElementSprite("gfx/menu/bg_menu.png"),
		new ElementSprite("gfx/gameplay/button.png"),
	};
}