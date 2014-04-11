import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TCPRunner {

	static int nodeId;
	Map<Integer, Integer> serverPorts;
	static List<Node> nodeList;
	static int totalNodes;

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {

		TCPRunner tcpRunner = new TCPRunner();
		nodeList = readConfigFile();
		tcpRunner.listenForConnections(nodeList, nodeId);
		tcpRunner.connectToServers(nodeList, nodeId);

	}

	public void listenForConnections(List<Node> nodeList, int id) {
		serverPorts = getPortNumbers(nodeList);
		Server server = new Server(serverPorts.get(id));
		server.start();
	}

	public void connectToServers(List<Node> nodeList, int id) throws UnknownHostException, IOException, InterruptedException {
		Client client = new Client();
		client.connectToServers(nodeList, nodeId);
	}

	private static Map<Integer, Integer> getPortNumbers(List<Node> nodeList) {
		Map<Integer, Integer> serverPorts = new HashMap<>();
		for (Node node : nodeList) {
			serverPorts.put(node.id, node.portno);
		}
		return serverPorts;
	}

	private static List<Node> readConfigFile() {

		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Node id");
		nodeId = in.nextInt();
		List<Node> nodeList = ParseFile.parseConfigFile(nodeId);
		return nodeList;

	}

}
