package uk.jamiekitson.weasleyclock;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;


public class MessageHandler implements MqttCallback
{
	// Private instance variables
	private MqttClient client;
	private String brokerUrl;
	private MqttConnectOptions conOpt;
	
	/**
	 * @param brokerUrl the url to connect to
	 * @param clientId the client id to connect with
	 * @param quietMode whether debug should be printed to standard out
	 * @throws MqttException
	 */
    public MessageHandler(String brokerUrl, String clientId) throws MqttException {
    	this.brokerUrl = brokerUrl;
    	
    	//This sample stores files in a temporary directory...
    	//..a real application ought to store them somewhere 
    	//where they are not likely to get deleted or tampered with
    	String tmpDir = System.getProperty("java.io.tmpdir");
    	MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir); 
    	
    	try {
    		// Construct the object that contains connection parameters 
    		// such as cleansession and LWAT
	    	conOpt = new MqttConnectOptions();
	    	conOpt.setCleanSession(false);

    		// Construct the MqttClient instance
			client = new MqttClient(this.brokerUrl,clientId, dataStore);
			
			// Set this wrapper as the callback handler
	    	client.setCallback(this);
	    	
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
    }

	@Override
	public void connectionLost(Throwable cause)
	{
		System.out.println("Connection to " + brokerUrl + " lost!" + cause);
		System.exit(1);
	}

	@Override
	public void deliveryComplete(MqttDeliveryToken token){}
	
	@Override
	public void messageArrived(MqttTopic topic, MqttMessage message) throws Exception
	{
		System.out.println("Message arrived on topic "+topic.getName()+": "+new String(message.getPayload()));
	}

}
