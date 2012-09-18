package uk.jamiekitson.weasleyclock.laburnum;

public enum Housemate
{
	AMY("Amy"), 
	JAMIE("Jamie"), 
	DAN("Dan"), 
	JESS("Jess");
	
	private String name;
	private Location lastLoc;
	private String date;
	
	Housemate(String name)
	{
		this.setName(name);
		this.setLastLoc(Location.UNKNOWN);
		this.setDate("--");
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setLastLoc(Location lastLoc)
	{
		this.lastLoc = lastLoc;
	}
	public Location getLastLoc()
	{
		return lastLoc;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getDate()
	{
		return date;
	}
}
