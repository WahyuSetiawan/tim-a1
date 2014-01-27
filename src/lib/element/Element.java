package lib.element;

public class Element 
{
	int w;
	int h;
	String path;
	
	public Element(int w, int h, String path) 
	{
		this.w = w;
		this.h = h;
		this.path = path;
	}
	
	public int getW() 
	{
		return w;
	}

	public void setW(int w) 
	{
		this.w = w;
	}

	public int getH() 
	{
		return h;
	}

	public void setH(int h) 
	{
		this.h = h;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
}
