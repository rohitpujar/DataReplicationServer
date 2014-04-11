import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Client {

	MessageReceiver msgReceive;
	int nodeId;

	public void connectToServers(List<Node> nodeList, int id) {
		this.nodeId = id;
		System.out.println("My node id is " + id);
		++id;
		System.out.println("Sending requests to node id onwards... " + id);
		System.out.println("Bring them up in 15 secs...");
		try {
			Thread.sleep(15000);
			for (int i = id; i < nodeList.size(); i++) {
				Socket clientSocket = new Socket(nodeList.get(i).ipaddr, nodeList.get(i).portno);
				SocketConnections.addToSocketConnections(nodeList.get(i).id, clientSocket);
				System.out.println("Total socket connections : " + SocketConnections.getSocketConnectionsSize());
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
