import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageReceiver extends Thread {

	DataInputStream dis;
	Socket socket;

	public MessageReceiver(int id, Socket socket) {

		this.socket = socket;

	}

	@Override
	public void run() {

		try {
			dis = new DataInputStream(socket.getInputStream());
			System.out.println("Client connection successful with : " + socket.getInetAddress().getHostName());

			while (true) {
				String message = dis.readUTF().toString();
				System.out.println("Client says : " + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
