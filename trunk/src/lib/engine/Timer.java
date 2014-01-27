package lib.engine;

public class Timer 
{
	private long milis = 0;
	private long second = 0;
	private long minutes = 0;
	
	private long currentTimeTicMilis;
	
	private boolean timeStarted = false;
	
	public void startForward()
	{	
		startForward(0, 0, 0);
	}
	
	public void startForward(int min, int sec, int mls)
	{	
		if(isTimeStarted())
		{
			System.out.println("Time is curently started");
			return;
		}
		
		currentTimeTicMilis = System.currentTimeMillis() - mls;
		minutes = min;
		second = sec;
		
		start();
		
		new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				while (isTimeStarted())
				{
					milis = System.currentTimeMillis() - currentTimeTicMilis;
					
					if(milis > 999)
					{
						milis = 0;
						second += 1;
						currentTimeTicMilis = System.currentTimeMillis();
					}
					if(second > 59)
					{
						second = 0;
						minutes += 1;
					}
				}
			}
		}).start();
	}
	
	public void startBackward(int min, int sec, int mls) 
	{
		if(isTimeStarted())
		{
			System.out.println("Time is curently started");
			return;
		}
		
		minutes = min;
		second = sec;
		milis = mls;
		
		currentTimeTicMilis = System.currentTimeMillis() + milis;
		start();
		
		new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				while (isTimeStarted())
				{
					milis = 999 - (System.currentTimeMillis() - currentTimeTicMilis);
					
					if(milis < 0)
					{
						milis = 999;
						second -= 1;
						currentTimeTicMilis = System.currentTimeMillis();
					}
					if(second < 0)
					{
						second = 59;
						minutes -= 1;
					}
				}
			}
		}).start();
	}

	private void start()
	{
		timeStarted = true;
	}
	
	public void stop()
	{
		timeStarted = false;
		minutes = second = milis = 0;
	}
	
	public boolean isTimeStarted() 
	{
		return timeStarted;
	}
	
	public long getMilis() 
	{
		return milis;
	}

	public long getSecond() 
	{
		return second;
	}

	public long getMinutes()
	{
		return minutes;
	}
	
}