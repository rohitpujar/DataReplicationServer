import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	static int nodeId;
	// identifying the node numbers as I get the requests between the servers
	static int serverConnectionCount;
	static int clientConnectionCount;
	int portNo;

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
					// System.out.println("~~~~ Adding socket to #### Server connections...");
					SocketConnections.addToServerSocketConnections(serverConnectionCount++, socket);
					// System.out.println("Total socket connections between servers: " + SocketConnections.getServerSocketConnectionsSize());
					MessageReceiver msgReceiver = new MessageReceiver(nodeId, socket);
					msgReceiver.start(); // open the stream for incoming messages
				} else {
					System.out.println("Successfully connected to ^Client^ no : " + clientConnectionCount + " , HostName : "
							+ socket.getInetAddress().getHostName());
					// System.out.println("					~~~~ Adding socket to **** Client connections...");
					SocketConnections.addToClientSocketConnections(clientConnectionCount++, socket);
					// System.out.println("					Total socket connections to clients: " + SocketConnections.getClientSocketConnectionsSize());
					MessageReceiver msgReceiver = new MessageReceiver(nodeId, socket);
					msgReceiver.start();

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
