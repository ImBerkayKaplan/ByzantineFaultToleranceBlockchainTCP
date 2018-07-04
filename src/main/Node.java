package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

public class Node extends Thread{
	
	ArrayList<String> messages = new ArrayList<String>();
    int port;
    ServerSocket server;
    static int nodecount;
    
    /** The constructor to set the server at the specified port and on localhost
	 * 
	 * @param port
	 * 		The port to set the server on
	 */
	Node(int port) throws IOException{
		this.port = port;
		this.server = new ServerSocket(this.port);
	}
	
	 @Override
	public void run() {
	    try {
	    		int count = 0;
	    		while(true) {
	    			
	                  // Socket object to receive incoming client requests
	        		  Socket socket1 = server.accept();
	        		  
	                  // Open input and read the message
	                  BufferedReader in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
	                  String rawMessage = in.readLine();
	                  JSONObject json = new JSONObject(rawMessage);
	                  String message = json.getString("message");
	                  this.messages.add(message);
	                  System.out.println(this.messages.toString());
	                  
	                  // Send a verification wave to other nodes using the Disseminator
	                  if(count%Node.nodecount==0) {
	                	  Thread th1 = new Disseminator(message, this.port);
	                	  th1.start();
	                  }
	                  count++;
	                  
	                  // Close the connection and buffered input
	                  socket1.close();
	                  in.close();        
	    		}                 
	      } catch (Exception e) {
				e.printStackTrace();	
		  }
	 }
}