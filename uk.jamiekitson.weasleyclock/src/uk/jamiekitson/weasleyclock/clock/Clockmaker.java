package uk.jamiekitson.weasleyclock.clock;

public class Clockmaker
{	
	public static Clock craftClock(ClockType type)
	{
		switch(type)
		{
			case GUI:
				return new GUIClock();
			case Console:
			default:
				return new ConsoleClock();			
		}
	}
}
