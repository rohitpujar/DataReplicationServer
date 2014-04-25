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
	MessageSender msgSender;
	
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
				
				if(recvdMsgTokens.get(0).equals("PING")){
//					System.out.println("Inside PING receiver ...");
					int clientId = Integer.parseInt(recvdMsgTokens.get(1));
					pingMessage(recvdMsgTokens.get(0), clientId);
				}
				if(recvdMsgTokens.get(0).equals("READ")){
					String objectName = recvdMsgTokens.get(1);
					System.out.println("Read request for Object "+objectName+" from Client : "+recvdMsgTokens.get(2));
					ReadObjects.readObjects(objectName, Integer.parseInt(recvdMsgTokens.get(2)));
					
				}
				
				if(recvdMsgTokens.get(0).equals("WRITE")){
					String objectToWrite = recvdMsgTokens.get(1)+","+recvdMsgTokens.get(2);
//					System.out.println("Write request, object & value : "+objectToWrite);
					WriteObjects.writeObject(recvdMsgTokens.get(1), recvdMsgTokens.get(2));
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void pingMessage(String message,int clientId){
		msgSender = new MessageSender();
		Boolean status = PartitionHandler.getClientConnectionStatus(clientId);
		System.out.println("#Connection to client "+clientId+" : "+status);
		msgSender.sendMesageToClient(clientId, status.toString());
		
	}

}
