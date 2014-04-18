import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	// static int nodeId;
	// identifying the node numbers as I get the requests between the servers
	static int serverConnectionCount;
	static int clientConnectionCount;
	int portNo;
	static int serverNodeNumber; // only to identify the node numbers when you get connection requests, not to be confused
	static int clientNodeNumber;

	public Server(int portNo) {
		this.portNo = portNo;
	}

	public void run() {
		try {
			ServerSocket socketlistener = new ServerSocket(portNo);
			System.out.println("Listening for connections on port no : " + portNo);
			while (true) {
				Socket socket = socketlistener.accept();
				if (!(SocketConnections.getServerSocketConnectionsSize() == SocketConnections.getTotalNodes() - 1)) {
					System.out.println("					Successfully connected to ^Server^ no : " + serverConnectionCount + " , HostName : "
							+ socket.getInetAddress().getHostName());
					serverNodeNumber = serverConnectionCount++;
					SocketConnections.addToServerSocketConnections(serverNodeNumber, socket);
					// SocketConnections.addToServerSocketConnectionStatus(serverNodeNumber,true);
					PartitionHandler.setServerConnectionStatus(serverNodeNumber, true);
					MessageReceiver msgReceiver = new MessageReceiver(SocketConnections.getNodeId(), socket);
					msgReceiver.start(); // open the stream for incoming messages
				} else {
					System.out.println("Successfully connected to ^Client^ no : " + clientConnectionCount + " , HostName : "
							+ socket.getInetAddress().getHostName());
					// System.out.println("					~~~~ Adding socket to **** Client connections...");
					clientNodeNumber = clientConnectionCount++;
					SocketConnections.addToClientSocketConnections(clientNodeNumber, socket);
					// SocketConnections.addToClientSocketConnectionStatus(clientNodeNumber, true);
					PartitionHandler.setClientConnectionStatus(clientNodeNumber, true);
					// System.out.println("					Total socket connections to clients: " + SocketConnections.getClientSocketConnectionsSize());
					MessageReceiver msgReceiver = new MessageReceiver(SocketConnections.getNodeId(), socket);
					msgReceiver.start();

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
