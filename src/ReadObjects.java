import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadObjects {

	
	public void readObjects(String object){
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(""));
			
			while((line = reader.readLine())!=null){
				
				String[] data = line.split(",");
				
				if(data[1].equals(object)){
					String value = data[3];
					
					System.out.println("Value of "+object+" is : "+value);
				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
