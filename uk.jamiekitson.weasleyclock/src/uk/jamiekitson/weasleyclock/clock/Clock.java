package uk.jamiekitson.weasleyclock.clock;

import org.eclipse.paho.client.mqttv3.*;

public abstract class Clock implements MqttCallback
{
	protected MqttClient client;
	
	protected Clock(String broker, String clientID) throws MqttException
	{
		client = new MqttClient("tcp://"+broker+":"+1883, clientID);
		client.setCallback(this);
	}
	
	public abstract void connectionLost(Throwable cause);
	public abstract void deliveryComplete(MqttDeliveryToken token);
	public abstract void messageArrived(MqttTopic arg0, MqttMessage arg1);
	
}
