import java.net.Socket;
import java.util.Iterator;
import java.util.Map;


public class SocketConnections {


	static private Map<Integer, Socket> socketConnections;

	public static Map<Integer, Socket> getSocketConnections() {
		return socketConnections;
	}

	public static void addToSocketConnections(Integer id,Socket socket) {

		socketConnections.put(id, socket);
	}
	
	public static void displaySocketConnections(){

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		Iterator it = socketConnections.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}
	
	}
	
	
}
