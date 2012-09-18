package uk.jamiekitson.weasleyclock.clock;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;

import javax.swing.JFrame;

import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import uk.jamiekitson.weasleyclock.gfx.GfxPanel;
import uk.jamiekitson.weasleyclock.laburnum.Housemate;
import uk.jamiekitson.weasleyclock.laburnum.Location;
import uk.jamiekitson.weasleyclock.message.WCMessage;

public class GUIClock extends Clock implements WindowListener
{
	private JFrame frame;
    private GfxPanel panel;
	
	
	public GUIClock(String broker, String clientID, int width, int height) throws MqttException
	{		
		super(broker, clientID);
		
		frame = new JFrame();
        panel = new GfxPanel();
        
        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(this);
        
        panel.setPreferredSize(new Dimension(width, height));
        frame.pack();
        
        panel.makeGraphics();
        frame.setVisible(true);
        
        client.connect();
        client.subscribe("weasleyclock/+");
	}
	
	private void updateHousemate(Housemate hm, Location loc, String date)
	{
		hm.setLastLoc(loc);
		hm.setDate(date);
	}
	
	private void drawClock()
	{
		panel.erase();
		
		for(Housemate hm : Housemate.values())
		{
			String text = hm.getName() + " was in " + hm.getLastLoc() + " on " + hm.getDate();
			
			switch(hm)
			{
				case AMY:
					panel.drawText(text, 300, 100);
					break;
				case DAN:
					panel.drawText(text, 300, 200);
					break;
				case JAMIE:
					panel.drawText(text, 300, 300);
					break;
				case JESS:
					panel.drawText(text, 300, 400);
					break;
			}			
		}		
		
		panel.repaint();
	}
	
	
	/*****************************************************
	 * 
	 * 				MQTT Callback Methods
	 * 
	 *****************************************************/
	
	@Override
	public void connectionLost(Throwable cause)
	{
	}

	@Override
	public void deliveryComplete(MqttDeliveryToken token)
	{
		
	}

	@Override
	public void messageArrived(MqttTopic topic, MqttMessage message)
	{
		String[] topics = topic.getName().split("/");
		String subtopic = topics[topics.length-1];
		Housemate person = null;
		WCMessage msg = null;
		
		for(Housemate hm : Housemate.values())
		{
			if (hm.getName().equals(subtopic))
			{
				person = hm;
			}
		}
		if(person == null)
			return;
		
		try
		{
			msg = WCMessage.parseString(new String(message.getPayload()));
			Location l = Location.parseLocaction(msg.getLocation());
			String date = msg.getDate();
			updateHousemate(person, l, date);			
		}
		catch (MqttException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			System.out.println(e.getMessage());
		}
				
		drawClock();		
	}

	

	/*****************************************************
	 * 
	 * 				Window Listener Methods
	 * 
	 *****************************************************/
	public void windowOpened(WindowEvent e){}
	
	public void windowClosing(WindowEvent e)
	{
		try
		{
			client.disconnect();
		}
		catch (MqttException e1)
		{
			e1.printStackTrace();
		}
		frame.dispose();		
	}
	
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}

}
