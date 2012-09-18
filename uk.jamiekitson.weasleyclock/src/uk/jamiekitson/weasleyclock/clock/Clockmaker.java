package uk.jamiekitson.weasleyclock.clock;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Clockmaker
{	
	public static Clock craftClock(ClockType type, String brokerURL, String clientID) throws MqttException
	{
		switch(type)
		{
			case GUI:
				return new GUIClock(brokerURL, clientID, 800, 600);
			case Console:
			default:
				return new ConsoleClock(brokerURL, clientID);			
		}
	}
}
