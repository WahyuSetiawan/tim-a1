package lib.element;

import org.andengine.util.color.Color;

import android.graphics.Typeface;

public class AppElementFont 
{
	int w, h, size;
	Typeface family;
	int style;
	Color color;
	public AppElementFont(int w, int h, int size, Typeface family, int style,
			Color color) {
		super();
		this.w = w;
		this.h = h;
		this.size = size;
		this.family = family;
		this.style = style;
		this.color = color;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Typeface getFamily() {
		return family;
	}
	public void setFamily(Typeface family) {
		this.family = family;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
