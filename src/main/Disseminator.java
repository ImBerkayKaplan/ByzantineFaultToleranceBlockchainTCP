package main;

import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

public class Disseminator extends Thread{
	
	static int[] ports;
	String message;
	int noBroadCast;
	
	/** The constructor to set the message parameter to be distributed among all nodes except the noBroadCase
	 * 
	 * @param message
	 * 		The message to be send to all nodes except noBroadCast
	 * @param noBroadCast
	 * 		The only port number that will not receive the message.
	 */
	Disseminator(String message, int noBroadCast){
		this.message = message;
		this.noBroadCast = noBroadCast;
	}
	
	@Override
	public void run(){
		
		int size = ports.length;
		
		//Iterate through all nodes
		for(int i1 = 0;i1<size;i1++) {
			if(ports[i1] != this.noBroadCast) {
				try {
					
					// Prepare the JSON object and insert the data
					JSONObject json = new JSONObject();
					json.put("ID", this.noBroadCast);
					json.put("message", message);
					
					// Send the message to the server
					Socket socket = new Socket("127.0.0.1", Disseminator.ports[i1]);
					PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
					out.println(json.toString());
					socket.close();
					out.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
}