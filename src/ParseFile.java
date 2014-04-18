import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseFile {
	static List<Node> nodeList = new ArrayList<Node>();

	public static List<Node> parseConfigFile(int id) {
		String line;

		BufferedReader readFile;
		try {
			readFile = new BufferedReader(new FileReader("/home/rohit/indigo_workspace/FileReplicationServer/Config"));
			while ((line = readFile.readLine()) != null) {

				if (!line.startsWith("#")) {

					String[] data = line.split(" ");
					if (data[1].equals("#")) {
						SocketConnections.setTotalNodes(Integer.parseInt(data[0]));
						System.out.println("Total nodes : " + SocketConnections.getTotalNodes());
					} else {

						Node n = new Node();
						int nodeid = Integer.parseInt(data[0]);
						String ip = data[1];
						int port = Integer.parseInt(data[2]);
						n.id = nodeid;
						n.ipaddr = ip;
						n.portno = port;
						nodeList.add(n);

					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	public static void parsePartitionConfigFile(int nodeId) {
		String line;
		int[] serverPartitionData1 = null;
		int[] clientPartitionData1 = null;
		int[] serverPartitionData2 = null;
		int[] clientPartitionData2 = null;

		BufferedReader readFile;
		try {
			readFile = new BufferedReader(new FileReader("/home/rohit/indigo_workspace/FileReplicationServer/PartitionConfig"));
			while ((line = readFile.readLine()) != null) {

				if (!line.startsWith("#")) { // to ignore comments, if any

					String[] data = line.split(" "); // split by space
					if (data[0].equals("Partition1")) {

						line = readFile.readLine(); // read next line
						String[] fileData = line.split(" "); // take only the node numbers which is after the space
						if (fileData[0].equals("server")) { // these nodes belong to server partition information
							String[] serverPartition = fileData[1].split(","); // they are separated by comma, so fetch them

							serverPartitionData1 = new int[serverPartition.length];
							for (int i = 0; i < serverPartition.length; i++) {
								serverPartitionData1[i] = Integer.parseInt(serverPartition[i]); // the server partition is in the serverPartitionData1
							}
						}
						line = readFile.readLine();
						String[] fileData1 = line.split(" ");
						if (fileData1[0].equals("client")) { // similarly for client partition nodes information
							String[] clientPartition = fileData1[1].split(",");

							clientPartitionData1 = new int[clientPartition.length];
							for (int i = 0; i < clientPartition.length; i++) {
								clientPartitionData1[i] = Integer.parseInt(clientPartition[i]); // client partition data is present here
							}
						}
						for (int i = 0; i < serverPartitionData1.length; i++) {
							if (serverPartitionData1[i] == nodeId) {
								// System.out.println("Node id " + nodeId + " Present here in server partition 1");
								createPartition(serverPartitionData1, clientPartitionData1);
							}
						}

						// ********************** Partition 2 config information **************************
						line = readFile.readLine();
						line = readFile.readLine();
						line = readFile.readLine();

						String[] fileData2 = line.split(" ");
						if (fileData2[0].equals("server")) {
							String[] serverPartition = fileData2[1].split(",");

							serverPartitionData2 = new int[serverPartition.length];
							for (int i = 0; i < serverPartition.length; i++) {
								serverPartitionData2[i] = Integer.parseInt(serverPartition[i]);
							}
						}
						line = readFile.readLine();
						String[] fileData3 = line.split(" ");
						if (fileData3[0].equals("client")) {
							String[] clientPartition = fileData3[1].split(",");

							clientPartitionData2 = new int[clientPartition.length];
							for (int i = 0; i < clientPartition.length; i++) {
								clientPartitionData2[i] = Integer.parseInt(clientPartition[i]);
							}

						}
						for (int i = 0; i < serverPartitionData2.length; i++) {
							if (serverPartitionData2[i] == nodeId) {
								createPartition(serverPartitionData2, clientPartitionData2);
							}
						}

						// **************
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void createPartition(int[] serverPartition, int[] clientPartition) {

		System.out.println("---Initial partition status---");
		PartitionHandler.displayConnectionStatusMap();
		PartitionHandler.createPartition(serverPartition, clientPartition);
		System.out.println("----- Partition done -----");
		PartitionHandler.displayConnectionStatusMap();

	}
}
