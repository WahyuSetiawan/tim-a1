package lib.engine;

public class TimeCapturer 
{
	private long timeCaptured;
	
	public TimeCapturer() 
	{
		timeCaptured = 0;
	}
	
	public void captureTime()
	{
		timeCaptured = System.currentTimeMillis();
	}
	
	public boolean isTimePassed(long waitTimeMilis) 
	{
		return (System.currentTimeMillis() - timeCaptured) > waitTimeMilis;
	}
	
	public long getTimeElapsed()
	{
		return System.currentTimeMillis() - timeCaptured;
	}
}
