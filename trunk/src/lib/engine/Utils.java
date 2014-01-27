package lib.engine;

import java.util.Random;

import lib.defines.GameEngineConfiguration;

import org.andengine.entity.shape.RectangularShape;

import android.util.Log;
import android.widget.Toast;

public class Utils 
{	
	@SuppressWarnings("incomplete-switch")
	public static float getXAnchor(RectangularShape child, float widthParent, Anchor anchor)
	{
		switch(anchor)
		{
			case TOP_LEFT:
			case CENTER_LEFT:
			case BOTTOM_LEFT:
			{
				return 0;
			}
			case TOP_CENTER:
			case CENTER:
			case BOTTOM_CENTER:
			{
				return widthParent/2 - child.getWidth()/2;
			}
			case TOP_RIGHT:
			case CENTER_RIGHT:
			case BOTTOM_RIGHT:
			{
				return widthParent - child.getWidth();
			}
		}
		
		return 0;
	}
	
	@SuppressWarnings("incomplete-switch")
	public static float getXAnchor(RectangularShape child, Anchor anchor)
	{
		switch(anchor)
		{
			case TOP_LEFT:
			case CENTER_LEFT:
			case BOTTOM_LEFT:
			{
				return 0;
			}
			case TOP_CENTER:
			case CENTER:
			case BOTTOM_CENTER:
			{
				return ((RectangularShape) child.getParent()).getWidth()/2 - child.getWidth()/2;
			}
			case TOP_RIGHT:
			case CENTER_RIGHT:
			case BOTTOM_RIGHT:
			{
				return ((RectangularShape) child.getParent()).getWidth() - child.getWidth();
			}
		}
		
		return 0;
	}
	
	@SuppressWarnings("incomplete-switch")
	public static float getYAnchor(RectangularShape child, float heightParent, Anchor anchor)
	{
		switch(anchor)
		{
			case TOP_LEFT:
			case TOP_CENTER:
			case TOP_RIGHT:
			{
				return 0;
			}
			case CENTER_LEFT:
			case CENTER:
			case CENTER_RIGHT:
			{
				return heightParent/2 - child.getHeight()/2;
			}
			case BOTTOM_LEFT:
			case BOTTOM_CENTER:
			case BOTTOM_RIGHT:
			{
				return heightParent - child.getHeight();
			}
		}
		
		return 0;
	}
	
	@SuppressWarnings("incomplete-switch")
	public static float getYAnchor(RectangularShape child, Anchor anchor)
	{
		switch(anchor)
		{
			case TOP_LEFT:
			case TOP_CENTER:
			case TOP_RIGHT:
			{
				return 0;
			}
			case CENTER_LEFT:
			case CENTER:
			case CENTER_RIGHT:
			{
				return ((RectangularShape) child.getParent()).getHeight()/2 - child.getHeight()/2;
			}
			case BOTTOM_LEFT:
			case BOTTOM_CENTER:
			case BOTTOM_RIGHT:
			{
				return ((RectangularShape) child.getParent()).getHeight() - child.getHeight();
			}
		}
		
		return 0;
	}
	
	public static void debug(String something)
	{
		Log.d(GameEngineConfiguration.debugTagName, something);
	}
	
	public static int GetBigTwoInt(float value)
	{
		int _value = (int) value;
		int returnvalue = 0;
		for(int i = 1; i <= 12; i++)
		{
			int number = 1;
			for(int j=1; j <= i; j++)
			{
				number*=2;
			}
			if(_value < number)
			{
				returnvalue = number;
				break;
			}
		}
		return returnvalue;
	}
		
	public static int randomInRange(int begin, int end) 
	{
		if (begin > end)
		{
			System.out.println("end number shouldn't bigger that the beginning number");
			return -1;
		}
		
		Random randomGenerator = new Random();
		int range = end - begin;
		return begin + (randomGenerator.nextInt(range + 1));
	}

	public static void toast(final GameEngine engine, final String pMessage, final int pDuration)
	{
		engine.runOnUiThread(new Runnable()
		{
			public void run()
			{
				Toast.makeText(engine, pMessage, pDuration).show();
			}
		});
	}
}
