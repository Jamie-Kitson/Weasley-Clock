package uk.jamiekitson.weasleyclock.laburnum;

import java.text.ParseException;

public enum Location
{
	SCIENCESITE("Science Site"), 
	TOWN("Town"), 
	HOME("Home"), 
	COLLINGWOOD("Collingwood"),
	UNKNOWN("Unknown!");
	
	private String toS;
	Location(String toS)
	{
		this.toS = toS;
	}
	
	public String toString()
	{
		return toS;
	}
	
	public static Location parseLocaction(String loc) throws ParseException
	{
		for(Location l : Location.values())
		{
			if(l.toString().equals(loc))
				return l;
		}
		
		throw new ParseException("Did not recognise location '"+loc+"'", 0);
	}
}
