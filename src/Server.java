import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	static int nodeId;
	static int identity; // identifying the node numbers as I get the requests
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
				SocketConnections.addToSocketConnections(identity++, socket);
				System.out.println("Total socket connections : " + SocketConnections.getSocketConnectionsSize());
				MessageReceiver msgReceiver = new MessageReceiver(nodeId, socket);
				msgReceiver.start(); // open the stream for incoming messages
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
