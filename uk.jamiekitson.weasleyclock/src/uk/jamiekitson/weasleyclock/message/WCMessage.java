package uk.jamiekitson.weasleyclock.message;

import java.text.ParseException;


public class WCMessage
{
	private String date;
	private String location;
	
	private WCMessage() {}
	
	public static WCMessage parseString(String msg) throws ParseException
	{
		WCMessage ret = new WCMessage();
		
		String[] parts = msg.split("\\|");
		if(parts.length != 2)
			throw new ParseException("Malformed Message, could not parse '"+msg+"'", 0);
		ret.date = parts[0];
		ret.location = parts[1];
		
		return ret;
	}
	

	public String getDate()
	{
		return date;
	}

	public String getLocation()
	{
		return location;
	}
}
