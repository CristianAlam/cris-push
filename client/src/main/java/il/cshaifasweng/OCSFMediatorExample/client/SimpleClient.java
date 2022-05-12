package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Product;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.util.ArrayList;
import java.util.List;

public class SimpleClient extends AbstractClient {

	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println("handle start");

		if(msg instanceof List){
			System.out.println("msg insof list");
			List<Product> listt = (List<Product>) msg;
			for(int i=0 ; i<5;i++) System.out.println(listt.get(i).getID());

		}
		if(msg instanceof String){
			String recievedStr = (String)msg ;
			if(recievedStr.equals("found")){
				System.out.println("found a table in the database !"); // we found the table so we dont need to create 6 items
				// now tell the primary controller that the table is found
			}
			else if(recievedStr.equals("not found")){
				System.out.println("didnt find a table"); // we didnt find the table , so we need to create 6 items at the beggings
				// now tell the primary controller that the table isnt found
				InitDatabaseEvent event = new InitDatabaseEvent(true);
			}


		}
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
