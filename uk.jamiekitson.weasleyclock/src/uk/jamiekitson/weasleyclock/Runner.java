package uk.jamiekitson.weasleyclock;


import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import uk.jamiekitson.weasleyclock.clock.*;

public class Runner
{
	public static void main(String[] args)
	{
		String brokerURL = "localhost";
		String clientID = "Jamie";
		
		String serverURI = "tcp://"+brokerURL+":"+1883;
		
		Clock theClock = Clockmaker.craftClock(ClockType.Console);
		
		try
		{
			MqttClient client = new MqttClient(serverURI, clientID);
			client.setCallback(theClock);
			client.connect();
			client.subscribe("butts");
			
			System.out.println("Press enter to exit");
			System.in.read();
			
			client.disconnect();
		}
		catch (MqttException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
