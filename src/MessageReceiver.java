import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class MessageReceiver extends Thread{

	DataInputStream dis;

	public MessageReceiver(int id,Socket server) {
		
		try {
			dis = new DataInputStream(server.getInputStream());
			System.out.println("Client connection successful with : "+server.getInetAddress().getHostName());
			
			while(true){
				String message = dis.readUTF().toString();
				System.out.println("Client says : "+message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {

		
	
	}
	
}
