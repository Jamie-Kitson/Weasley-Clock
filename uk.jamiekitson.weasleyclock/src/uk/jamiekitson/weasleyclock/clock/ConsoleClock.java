package uk.jamiekitson.weasleyclock.clock;

import java.io.IOException;
import java.text.ParseException;

import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import uk.jamiekitson.weasleyclock.message.WCMessage;

public class ConsoleClock extends Clock
{
	public ConsoleClock(String broker, String clientID) throws MqttException
	{
		super(broker, clientID);
		
		client.connect();
		client.subscribe("weasleyclock/+"); // Subscribe to all topics under "weasleyclock"
		
		System.out.println("Press enter to exit");
		try
		{
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			client.disconnect();
		}
	}
	
	
	public void listen()
	{
		
	}
	
	
	/*****************************************************
	 * 
	 * 				MQTT Callback Methods
	 * 
	 *****************************************************/
	
	@Override
	public void connectionLost(Throwable cause)
	{
		System.out.println("Connection lost!" + cause);
		System.exit(1);
	}

	@Override
	public void deliveryComplete(MqttDeliveryToken token)
	{
	}

	@Override
	public void messageArrived(MqttTopic topic, MqttMessage message)
	{
		try
		{
			String[] topics = topic.getName().split("/");
			String person = topics[topics.length-1];
			
			WCMessage msg = WCMessage.parseString(new String(message.getPayload()));
			System.out.println(person + " was at "+msg.getLocation()+" at "+ msg.getDate());
		}
		catch (MqttException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
