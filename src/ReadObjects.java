import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadObjects {

	static public void readObjects(String object, int clientId) {

		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("G:\\UTD\\kepler\\FileReplicationServer\\Objects"));
			MessageSender msgSender = new MessageSender();
			while ((line = reader.readLine()) != null) {

				if (line.contains(object)) {

					String[] data = line.split(",");
					System.out.println("------OBJECT requested for READ------");
					System.out.println("Object : " + object + ", Value : " + data[2]);
					msgSender.sendMesageToClient(clientId, Message.READ_RESPONSE.toString() + "," + line);
					return;
				}
			}

			msgSender.sendMesageToClient(clientId, Message.READ_RESPONSE.toString() + "," + Message.OBJECT_NOT_FOUND.toString());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
