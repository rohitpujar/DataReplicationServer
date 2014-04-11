import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParseFile {

	static List<Node> nodeList = new ArrayList<Node>();
	
	public static List<Node> parseConfigFile(int id){
		int totalNodes=0;
		String line;
		
		for(int i=id;i<totalNodes-1;i++){
			
			Node newNode = new Node();
			try {
				BufferedReader readFile = new BufferedReader(new FileReader("Config"));
				
				while((line = readFile.readLine())!=null){
					String[] data = line.split(" ");
					if(data[1].equals("#")){
						continue;
					}
					newNode.id = Integer.parseInt(data[0]);
					newNode.ipaddr = data[1];
					newNode.portno = Integer.parseInt(data[2]);
					nodeList.add(newNode);
					
				}
				
				
			} catch (FileNotFoundException e) {
				System.out.println("Config file not found");
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
		return nodeList;
	}
}
