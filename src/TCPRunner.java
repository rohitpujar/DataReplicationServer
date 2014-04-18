import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TCPRunner {

	Map<Integer, Integer> serverPorts;
	static List<Node> nodeList;

	public static void main(String[] args) {

		TCPRunner tcpRunner = new TCPRunner();
		nodeList = readConfigFile();
		tcpRunner.listenForConnections(nodeList, SocketConnections.getNodeId());
		tcpRunner.connectToServers(nodeList, SocketConnections.getNodeId());

		System.out.println("Select the following :");
		System.out.println("1 to create network partition");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		if (input == 1) {
			createPartition();
		}

	}

	public void listenForConnections(List<Node> nodeList, int id) {
		serverPorts = getPortNumbers(nodeList);
		Server server = new Server(serverPorts.get(id));
		server.start();
	}

	public void connectToServers(List<Node> nodeList, int id) {
		Client client = new Client();
		client.connectToServers(nodeList, SocketConnections.getNodeId());
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
		SocketConnections.setNodeId(in.nextInt());
		List<Node> nodeList = ParseFile.parseConfigFile(SocketConnections.getNodeId());
		return nodeList;

	}

	private static void createPartition() {
		ParseFile.parsePartitionConfigFile(SocketConnections.getNodeId());
	}

}
