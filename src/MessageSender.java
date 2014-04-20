import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageSender {

	DataOutputStream dos;

	public void sendMessageToServer(int nodeId, String message) {

		try {
			Socket socket = SocketConnections.getServerSocketConnections().get(nodeId);
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMesageToClient(int nodeId,String message){
		try{
			dos = new DataOutputStream(SocketConnections.getClientSocketConnections().get(nodeId).getOutputStream());
			dos.writeUTF(message);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void sendToAll(String message) {

		try {
			for (Socket values : SocketConnections.getServerSocketConnections().values()) {
				dos = new DataOutputStream(values.getOutputStream());
				dos.writeUTF(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
