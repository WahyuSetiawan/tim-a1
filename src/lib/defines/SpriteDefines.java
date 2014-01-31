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
	int POINTER			= BUTTON +1;
	int BIG_ROCK		= POINTER + 1;
	int HOLE			= BIG_ROCK + 1;
	int TREE			= HOLE + 1;
	int SEMAK			= TREE + 1;
	
	public final static ElementSprite CONTAINER[] = 
	{
		new ElementSprite("gfx/gameplay/background/bg_belakang.png"),
		new ElementSprite("gfx/gameplay/background/3. bg_tengah.png"),
		new ElementSprite("gfx/gameplay/background/bg_floor_depan.png"),
		new ElementSprite("gfx/gameplay/object/bg_pohon.png"),
		new ElementSprite("gfx/gameplay/object/bg_semak.png"),
		new ElementSprite("gfx/menu/background/bg_fill.png"),
		new ElementSprite("gfx/menu/background/bg_menu.png"),
		new ElementSprite("gfx/gameplay/object/button.png"),
		new ElementSprite("gfx/gameplay/object/pointer.png"),
		new ElementSprite("gfx/gameplay/object/BATU BESAR.png"),
		new ElementSprite("gfx/gameplay/object/LOBANG.png"),
		new ElementSprite("gfx/gameplay/object/POHON AMBRUK.png"),
		new ElementSprite("gfx/gameplay/object/SEMAK.png"),
	};
}