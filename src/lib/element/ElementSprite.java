package lib.element;

public class ElementSprite extends Element 
{
	
	public ElementSprite(int w, int h, String path) 
	{
		super(w, h, path);
	}
	
	public ElementSprite(String path)
	{
		this(0, 0, path);
	}
}
