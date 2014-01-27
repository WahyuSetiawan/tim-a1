package lib.element;

public class ElementSound 
{
	String path;
	boolean isLooping;
	
	public ElementSound(String path, boolean isLooping) 
	{
		this.path = path;
		this.isLooping = isLooping;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isLooping() {
		return isLooping;
	}

	public void setLooping(boolean isLooping) {
		this.isLooping = isLooping;
	}
}
