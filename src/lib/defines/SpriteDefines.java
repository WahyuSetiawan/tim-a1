package lib.defines;

import lib.element.ElementSprite;

public interface SpriteDefines
{
	//index backgound
	int BG_BELAKANG		= 0;
	int BG_TENGAH		= BG_BELAKANG + 1;
	int BG_DEPAN		= BG_TENGAH +1;
	int BG_FLOOR_DEPAN	= BG_DEPAN +1;
	
	//index object
	int BG_POHON		= BG_FLOOR_DEPAN + 1;
	int BG_SEMAK		= BG_POHON + 1;
	
	//index pointer
	int POINTER			= BG_SEMAK +1;
	
	//index obstacle
	int BIG_ROCK		= POINTER + 1;
	int SMALL_ROCK		= BIG_ROCK + 1;
	int HOLE			= SMALL_ROCK + 1;
	int TREE			= HOLE + 1;
	
	//index menu utama
	int MENU			= TREE + 1;
	int BUTTON_MENU		= MENU +1;
	
	//index splash
	int SPLASH			= BUTTON_MENU +1;
	int LOADINGBAR		= SPLASH +1;
	
	// index report
	int REPORT_BG		= LOADINGBAR +1;
	int PLAY_BUTTON		= REPORT_BG +1;
	int POCKET_BUTTON	= PLAY_BUTTON +1;
	int QUIT_BUTTON		= POCKET_BUTTON+1;
	
	//index closing
	int CLOSING_BG		= QUIT_BUTTON +1;
	int CLOSING_YES		= CLOSING_BG +1;
	int CLOSING_NO		= CLOSING_YES +1;
	
	//index option
	int OPTION_BG 		= CLOSING_NO +1;
	int OPTION_GEAR		= OPTION_BG +1;
	int OPTION_CREDIT 	= OPTION_GEAR +1;
	int OPTION_OFF		= OPTION_CREDIT +1;
	int OPTION_ON 		= OPTION_OFF+1;
	
	//index pause
	int PAUSE_BG 		= OPTION_ON +1;
	int PAUSE_EXIT		= PAUSE_BG+1;
	int PAUSE_OPTION	= PAUSE_EXIT+1;
	int PAUSE_POCKET	= PAUSE_OPTION +1;
	
	/*
	 * Batu besar : 115x161
	 * Batu kecil : 80x55
	 * Pohon jatuh : 207x81
	 * Lebar lubang : 107
	 * */
	
	int backgroundHeight 	= GameEngineConfiguration.masterHeight;
	int backgroundWidth 	= GameEngineConfiguration.masterWidth;	
	
	public final static ElementSprite CONTAINER[] = 
	{
		//background define
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/bg_belakang.png"),
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/bg_tengah.png"),
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/gameplay/background/bg_depan.png"),
		new ElementSprite("gfx/gameplay/background/bg_floor_depan.png"),
		//object define
		new ElementSprite(250, 400, "gfx/gameplay/object/bg_pohon.png"),
		new ElementSprite(300, 200, "gfx/gameplay/object/bg_semak.png"),
		//define pointer
		new ElementSprite( 60, 106, "gfx/gameplay/player/pointer.png"),
		//obstacle define
		new ElementSprite(115, 161, "gfx/gameplay/obstacle/BATU BESAR.png"),
		new ElementSprite(80, 55, "gfx/gameplay/obstacle/BATU KECIL.png"),
		new ElementSprite("gfx/gameplay/obstacle/LOBANG.png"),
		new ElementSprite(207, 81, "gfx/gameplay/obstacle/POHON AMBRUK.png"),
		//menu define
		new ElementSprite("gfx/gameplay/menu/menu.png"),
		new ElementSprite( 55, 40,"gfx/gameplay/menu/button_menu.png"),
		//splash define
		new ElementSprite(backgroundWidth, backgroundHeight, "gfx/loading/splash.png"),
		new ElementSprite("gfx/loading/loading_bar.png"),
		//report define
		new ElementSprite(600, 300, "gfx/gameplay/report/bg_report.png"),
		new ElementSprite("gfx/gameplay/report/play_button.png"),
		new ElementSprite("gfx/gameplay/report/pocket_button.png"),
		new ElementSprite("gfx/gameplay/report/quit_button.png"),
		//handling closing
		new ElementSprite("gfx/gameplay/closing/bg.png"),
		new ElementSprite("gfx/gameplay/closing/yes.png"),
		new ElementSprite("gfx/gameplay/closing/no.png"),
		//option define
		new ElementSprite("gfx/gameplay/option/bg.png"),
		new ElementSprite("gfx/gameplay/option/gear.png"),
		new ElementSprite("gfx/gameplay/option/credit.png"),
		new ElementSprite("gfx/gameplay/option/off.png"),
		new ElementSprite("gfx/gameplay/option/on.png"),
		//pause Define
		new ElementSprite(600,300,"gfx/gameplay/pause/bg.png"),
		new ElementSprite("gfx/gameplay/pause/exit.png"),
		new ElementSprite("gfx/gameplay/pause/option.png"),
		new ElementSprite("gfx/gameplay/pause/pocket.png"),
	};
}