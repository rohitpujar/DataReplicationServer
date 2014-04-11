import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Server extends Thread{

	int id;
	static int identity; //identifying the node numbers as I get the request to connect
	int portNo;
	Map<Integer,Integer> serverPorts;
	
	public Server(int portNo) {
		this.portNo = portNo;
	}
	
	public void run() {
		try {
			ServerSocket socketlistener = new ServerSocket(portNo);
			System.out.println("Listening for connections on port no : "+portNo);
			while(true){
				Socket socket = socketlistener.accept();
				SocketConnections.addToSocketConnections(identity++, socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void listenForConnections(List<Node> nodeList,int id ){
		this.id = id;
		serverPorts = getPortNumbers(nodeList);
		Server server = new Server(serverPorts.get(id));
		server.start();
	}
	
	
	private Map<Integer,Integer> getPortNumbers(List<Node> nodeList){
		Map<Integer,Integer> serverPorts = new HashMap<>();
		for(Node node : nodeList){
			serverPorts.put(node.id, node.portno);
		}
		return serverPorts;
	}

}
