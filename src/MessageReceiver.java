import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MessageReceiver extends Thread {

	DataInputStream dis;
	Socket socket;
	int nodeId;

	public MessageReceiver(int id, Socket socket) {
		this.nodeId = id;
		this.socket = socket;

	}

	@Override
	public void run() {

		try {

			dis = new DataInputStream(socket.getInputStream());

			while (true) {
				String message = dis.readUTF().toString();
				System.out.println("																Client says : " + message);

				List<String> recvdMsgTokens = new ArrayList<String>();
				StringTokenizer msgTokens = new StringTokenizer(message, ",");

				while (msgTokens.hasMoreTokens()) {
					recvdMsgTokens.add(msgTokens.nextToken());
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
