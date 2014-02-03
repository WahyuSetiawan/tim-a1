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
	int POINTER			= BG_MENU +1;
	int BIG_ROCK		= POINTER + 1;
	int SMALL_ROCK		= BIG_ROCK + 1;
	int HOLE			= SMALL_ROCK + 1;
	int TREE			= HOLE + 1;
	int SEMAK			= TREE + 1;
	int MENU			= SEMAK + 1;
	int BUTTON_MENU		= MENU +1;
	int LOADING			= BUTTON_MENU +1;
	
	int backgroundHeight 	= GameEngineConfiguration.masterHeight;
	int backgroundWidth 	= GameEngineConfiguration.masterWidth;	
	
	public final static ElementSprite CONTAINER[] = 
	{
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/bg_belakang.png"),
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/3. bg_tengah.png"),
		new ElementSprite("gfx/gameplay/background/bg_floor_depan.png"),
		//new ElementSprite(420, 620, "gfx/gameplay/object/bg_pohon.png"),
		new ElementSprite(250, 400, "gfx/gameplay/object/bg_pohon.png"),
		new ElementSprite(300, 200, "gfx/gameplay/object/bg_semak.png"),
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/menu/background/bg_fill.png"),
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/menu/background/bg_menu.png"),
		new ElementSprite( 60, 106, "gfx/gameplay/player/pointer.png"),
		new ElementSprite(150, 200, "gfx/gameplay/object/BATU BESAR.png"),
		new ElementSprite(75, 50, "gfx/gameplay/object/BATU KECIL.png"),
		new ElementSprite("gfx/gameplay/object/LOBANG.png"),
		new ElementSprite(300, 250, "gfx/gameplay/object/POHON AMBRUK.png"),
		new ElementSprite("gfx/gameplay/object/SEMAK.png"),
		new ElementSprite("gfx/gameplay/menu/menu.png"),
		new ElementSprite( 55, 40,"gfx/gameplay/menu/button_menu.png"),
		new ElementSprite("gfx/loading/loading.jpg"),
	};
}