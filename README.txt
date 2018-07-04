This program creates a blockchain network on the localhost. The network is created with ServerSockets at different port numbers. 
When a message is to be sent to other nodes, the Nodes use the Disseminator to reach out other nodes.
After the nodes receive the first message, the nodes send the message to all other nodes to verify they all received the same message.
The program can perform a treacherous act by sending each node a different message. 
The nodes print out what they received, so the traitor can be detected easily.