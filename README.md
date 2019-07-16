# ByzantineFaultToleranceBlockchainTCP

This program creates a blockchain network on the localhost. The network is created with ServerSockets at different port numbers, and each of the ServerSocket is considered to be a node in the network. When a message is to be sent to other nodes, the Nodes use the Disseminator to reach out other nodes. After the nodes receive the first message, the nodes send the message to all other nodes to verify they all received the same message. The program can perform a treacherous act by sending each node a different message. The nodes print out what they received, so they can invalidate a message if the feature was to be added. 

In addition, the message does not need to be sent as a JSON string; however, it was done this way to demonstrate the use of JSON.

## Make file will be added soon
