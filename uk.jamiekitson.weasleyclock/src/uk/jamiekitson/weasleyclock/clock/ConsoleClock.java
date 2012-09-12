package uk.jamiekitson.weasleyclock.clock;

import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ConsoleClock implements Clock
{
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
	public void messageArrived(MqttTopic topic, MqttMessage message) throws Exception
	{
		System.out.println("Message arrived on topic "+topic.getName()+": "+new String(message.getPayload()));		
	}
}
