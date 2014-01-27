package lib.element;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.util.color.Color;

public class ElementFont 
{
	int w;
	int h;
	TextureOptions textureOptions;
	String path;
	int size;
	Color color;

	public ElementFont(TextureOptions textureOptions, String path, int size, Color color) {
		super();
		this.w = 256;
		this.h = 256;
		this.textureOptions = textureOptions;
		this.path = path;
		this.size = size;
		this.color = color;
	}

	public ElementFont(String path, int size, Color color) {
		this(TextureOptions.BILINEAR, path, size, color);
	}
	
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public TextureOptions getTextureOptions() {
		return textureOptions;
	}

	public void setTextureOptions(TextureOptions textureOptions) {
		this.textureOptions = textureOptions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
