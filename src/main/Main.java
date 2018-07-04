package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws IOException, JSONException{
		
		//Define necessary variables
		Scanner inint = new Scanner(System.in);
		Scanner inString = new Scanner(System.in);
		ArrayList<Thread> nodes = new ArrayList<Thread>();
		boolean dontexit = true;
		String alphabet = "abcdefghiklmnopqrstvxyz";
		
		// Prompt the user for the number of nodes in the network
		System.out.println("Enter the number of nodes you want on your network");
		int number = inint.nextInt();
		int[] ports = new int[number];
		
		// Add the nodes to the network
		for(int i1 = 0; i1< number; i1++) {
			ports[i1] = 8080 + i1;
			int addport = ports[i1];
			Thread node = new Node(addport);
			node.start();
			nodes.add(node);
			System.out.println("Node with port number " + addport + " has been added");
		}
		
		// Set static variables in the Node and the Disseminator
		Node.nodecount = ports.length;
		Disseminator.ports = ports;
		
		// Iterate until user decides to exit
		while(dontexit) {

			// Present the menu
			System.out.println("1 - Broadcast a message to all nodes.");
			System.out.println("2 - See all your nodes with their ports.");
			System.out.println("3 - Perform a treacherous act.");
			System.out.println("Anything else to exit");
			int parameter = inint.nextInt();
			
			// Execute the option the user selected
			switch(parameter) {
				case 1:
					System.out.println("Enter the message to broadcast to all nodes.");
					String message = inString.nextLine();
					Thread th1 = new Disseminator(message, 0);
					th1.start();
					break;
				case 2:
					for(int i2 = 0; i2 < nodes.size(); i2++) {
						System.out.println("node number " + i2 + " with port number " + ports[i2] + " is in this list");
					}
					break;
				case 3:
					for(int i3 = 0; i3 < ports.length; i3++) {
						Socket socket = new Socket("127.0.0.1",ports[i3]);
						PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
						JSONObject json = new JSONObject();
						json.put("message", "test"+ alphabet.charAt(i3%alphabet.length()));
						json.put("ID", 0);
						out.println(json.toString());
						socket.close();
					}
					break;
				default:
					dontexit = false;
					System.out.println("Exit!");
					break;
			}
		}
		
		// Close the scanners
		inint.close();
		inString.close();
	}
}