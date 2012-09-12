package uk.jamiekitson.weasleyclock.clock;

import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class GUIClock implements Clock
{
	
	@Override
	public void connectionLost(Throwable arg0)
	{

	}

	@Override
	public void deliveryComplete(MqttDeliveryToken arg0)
	{
		
	}

	@Override
	public void messageArrived(MqttTopic arg0, MqttMessage arg1) throws Exception
	{
		// Handle new messages
	}

}
