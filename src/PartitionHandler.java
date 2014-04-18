import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PartitionHandler {

	// declare hashmaps to maintain connection status, either true or falsa
	private static Map<Integer, Boolean> serverConnection;
	private static Map<Integer, Boolean> clientConnection;

	public static Map<Integer, Boolean> getServerConnection() {
		return serverConnection;
	}

	public static Map<Integer, Boolean> getClientConnection() {
		return clientConnection;
	}

	// sets all server connections to false
	public static void setServerConnectionsToFalse() {

		Iterator<Map.Entry<Integer, Boolean>> it = serverConnection.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Boolean> pairs = it.next();
			pairs.setValue(false);
		}
	}

	// sets all client connections to false
	public static void setClientConnectionsToFalse() {

		Iterator<Map.Entry<Integer, Boolean>> it = clientConnection.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Boolean> pairs = it.next();
			pairs.setValue(false);
		}
	}

	// set server connection - data is received as and when sockets are created
	public static void setServerConnectionStatus(int id, boolean value) {
		if (serverConnection == null) {
			serverConnection = new HashMap<>();
			// System.out.println("#########  Created a new server connection status hashmap ########");
		}
		serverConnection.put(id, value);
	}

	// set client connection - similar to the above one
	public static void setClientConnectionStatus(int id, boolean value) {
		if (clientConnection == null) {
			clientConnection = new HashMap<>();
			// System.out.println("#########  Created a new client connection status hashmap ########");
		}
		clientConnection.put(id, value);
	}

	// input from the network partition config file
	public static void createPartition(int[] serverConnectionData, int[] clientConnectionData) {
		setClientConnectionsToFalse(); // set every client connection to false
		setServerConnectionsToFalse(); // set every server connection to false
		for (int i = 0; i < serverConnectionData.length; i++) {
			serverConnection.put(serverConnectionData[i], true);
		}
		// creating the partition including the client connections too
		for (int i = 0; i < clientConnectionData.length; i++) {
			clientConnection.put(clientConnectionData[i], true);
		}

	}

	public static void displayConnectionStatusMap() {
		Iterator<Map.Entry<Integer, Boolean>> it = serverConnection.entrySet().iterator();
		System.out.println("- Servers -");
		while (it.hasNext()) {
			Map.Entry<Integer, Boolean> pairs = it.next();
			System.out.println(pairs.getKey() + " : " + pairs.getValue());
		}
		Iterator<Map.Entry<Integer, Boolean>> iterator = clientConnection.entrySet().iterator();
		System.out.println("- Clients -");
		while (iterator.hasNext()) {
			Map.Entry<Integer, Boolean> pairs1 = iterator.next();
			System.out.println(pairs1.getKey() + " " + pairs1.getValue());
		}

	}

}
