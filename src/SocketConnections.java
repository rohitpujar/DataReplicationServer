import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SocketConnections {

	private static int totalNodes;
	static private Map<Integer, Socket> serverSocketConnections = new HashMap<Integer, Socket>();
	static private Map<Integer, Socket> clientSocketConnections = new HashMap<Integer, Socket>();

	public static Map<Integer, Socket> getServerSocketConnections() {
		return serverSocketConnections;
	}
	
	public static Map<Integer, Socket> getClientSocketConnections() {
		return clientSocketConnections;
	}

	public static void addToServerSocketConnections(Integer id, Socket socket) {

		serverSocketConnections.put(id, socket);
//		System.out.println("Node " + id + " added to #server socket connections...");
	}


	public static void addToClientSocketConnections(Integer id, Socket socket) {
		clientSocketConnections.put(id, socket);
//		System.out.println("Node " + id + " added to *client socket connections...");
	}

	public static void displaySocketConnections() {

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Iterator it = serverSocketConnections.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}

	}

	public static int getServerSocketConnectionsSize() {

		return serverSocketConnections.size();
	}
	
	public static int getClientSocketConnectionsSize() {

		return clientSocketConnections.size();
	}

	public static int getTotalNodes() {
		return totalNodes;
	}

	public static void setTotalNodes(int totalNodes) {
		SocketConnections.totalNodes = totalNodes;
	}

	
}
