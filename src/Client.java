import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Client {

	MessageReceiver msgReceive;
	int nodeId;

	public void connectToServers(List<Node> nodeList, int id) {
		this.nodeId = id;
		++id;
		System.out.println("Bring them up in 15 secs...");
		try {
			Thread.sleep(15000);
			for (int i = id; i < nodeList.size(); i++) {
				Socket clientSocket = new Socket(nodeList.get(i).ipaddr, nodeList.get(i).portno);
				SocketConnections.addToServerSocketConnections(nodeList.get(i).id, clientSocket);
//				System.out.println("Total socket connections : " + SocketConnections.getServerSocketConnectionsSize());
//				************
				System.out.println("					Successfully connected to ^Server^ no : " + nodeList.get(i).id + " , HostName : "
						+ clientSocket.getInetAddress().getHostName());
//				************
				
				msgReceive = new MessageReceiver(nodeId, clientSocket);
				msgReceive.start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
