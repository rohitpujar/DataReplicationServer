import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SocketConnections {

	private static int totalNodes;
	private static int nodeId;

	// Server connections and Client connections to which it is connected
	static private Map<Integer, Socket> serverSocketConnections = new HashMap<Integer, Socket>();
	static private Map<Integer, Socket> clientSocketConnections = new HashMap<Integer, Socket>();

	// Hashmap to indicate which server is connected to which server and client and vice versa
	// Basically to indicate the network partition configuration information

	static private Map<Integer, Boolean> serverSocketConnectionStatus = new HashMap<Integer, Boolean>();
	static private Map<Integer, Boolean> clientSocketConnectionStatus = new HashMap<Integer, Boolean>();

	public static Map<Integer, Socket> getServerSocketConnections() {
		return serverSocketConnections;
	}

	public static Map<Integer, Socket> getClientSocketConnections() {
		return clientSocketConnections;
	}

	public static Map<Integer, Boolean> getServerSocketConnectionStatus() {
		return serverSocketConnectionStatus;
	}

	public static Map<Integer, Boolean> getClientSocketConnectionStatus() {
		return clientSocketConnectionStatus;
	}

	public static void addToServerSocketConnections(Integer id, Socket socket) {

		serverSocketConnections.put(id, socket);
		// System.out.println("Node " + id + " added to #server socket connections...");
	}

	public static void addToClientSocketConnections(Integer id, Socket socket) {
		clientSocketConnections.put(id, socket);
		// System.out.println("Node " + id + " added to *client socket connections...");
	}

	public static void addToServerSocketConnectionStatus(Integer id, Boolean status) {
		// System.out.println("Connection "+status+" for server no : "+id);
		serverSocketConnectionStatus.put(id, status);
	}

	public static void addToClientSocketConnectionStatus(Integer id, Boolean status) {
		System.out.println("Connection " + status + " for client no : " + id);
		clientSocketConnectionStatus.put(id, status);
	}

	public static void displaySocketConnections() {

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Iterator<Map.Entry<Integer, Socket>> it = serverSocketConnections.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Socket> pairs = it.next();
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

	public static int getNodeId() {
		return nodeId;
	}

	public static void setNodeId(int nodeId) {
		SocketConnections.nodeId = nodeId;
	}

}
