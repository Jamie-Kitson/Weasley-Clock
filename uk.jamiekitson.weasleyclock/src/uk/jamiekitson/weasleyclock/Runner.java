package uk.jamiekitson.weasleyclock;


import org.eclipse.paho.client.mqttv3.MqttException;

import uk.jamiekitson.weasleyclock.clock.*;

public class Runner
{
	public static void main(String[] args)
	{
		String brokerURL = "localhost";
		String clientID = "TheClock";
		
		try
		{
			Clockmaker.craftClock(ClockType.GUI, brokerURL, clientID);
		}
		catch (MqttException e)
		{
			e.printStackTrace();
		}
	}
}
