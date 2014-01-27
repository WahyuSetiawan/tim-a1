package lib.element;

import org.andengine.opengl.texture.TextureOptions;

public class ElementAnim extends Element
{
	TextureOptions textureOptions;
	int col;
	int row;
	
	public ElementAnim(int w, int h, int col, int row, String path, TextureOptions textureOptions) 
	{
		super(w, h, path);
		this.col = col;
		this.row = row;
		this.textureOptions = textureOptions;
	}
	
	public ElementAnim(int col, int row, String path, TextureOptions textureOptions) 
	{
		this(0, 0, col, row, path, textureOptions);
	}
	
	public ElementAnim(int w, int h, int col, int row, String path) 
	{
		this(w, h, col, row, path, TextureOptions.BILINEAR);
	}
	
	public ElementAnim(int col, int row, String path) 
	{
		this(0, 0, col, row, path);
	}
	
	public TextureOptions getTextureOptions() {
		return textureOptions;
	}


	public void setTextureOptions(TextureOptions textureOptions) {
		this.textureOptions = textureOptions;
	}

	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}
	
	
}
