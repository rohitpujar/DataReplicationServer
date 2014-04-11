import java.util.List;
import java.util.Scanner;



public class TCPRunner {
	
	static int id;
	
	public static void main(String[] args) {
		
		readConfigFile();
		
	}
	
	
	private static void readConfigFile(){
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Node id");
		id = in.nextInt();
		List<Node> nodeList = ParseFile.parseConfigFile(id);
		
		
	}
}
