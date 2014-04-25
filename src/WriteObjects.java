import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteObjects {

	public static void writeObject(String id, String value) {
		try {
			
//			Read the file to an arraylist
			BufferedReader reader = new BufferedReader(new FileReader("G:\\UTD\\kepler\\FileReplicationServer\\Objects"));
			String line = null;
			ArrayList<FileLineStructure> fileLines = new ArrayList<FileLineStructure>();
			while ((line = reader.readLine()) != null) {
				String tokens[] = line.split(",");
				String id1 = tokens[0];
				float version = Float.parseFloat(tokens[1]);
				String value1 = tokens[2];
				FileLineStructure fileLine = new FileLineStructure(id1, version, value1);
				fileLines.add(fileLine);
			}
			boolean found = false;
//			If the element is present, just increment the version
			for (FileLineStructure fileLine : fileLines) {
				if (fileLine.getId().equals(id)) {
					found = true;
					fileLine.setVersion((float) (fileLine.getVersion() + .01));
					fileLine.setValue(value);
					System.out.println("	>> Object already PRESENT -> Updated version : "+id+" ,"+value);
					break;
				}
			}
//			If not found, it is a new object and should be written to the file
			if (!found) {
				FileLineStructure fileLine = new FileLineStructure(id, ((float) 1.01), value);
				fileLines.add(fileLine);
			}
			reader.close();
//			Now write the Arraylist contents to the new file
			FileWriter fw = new FileWriter("G:\\UTD\\kepler\\FileReplicationServer\\Objects");
			for (FileLineStructure fileLine : fileLines) {
				String lineToWrite = fileLine.getId() + "," + Float.toString(fileLine.getVersion()) + "," + fileLine.getValue() + "\n";
				fw.write(lineToWrite);
			}
			System.out.println(" 			>>> WRITE Successful -> New Object : "+id);
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class FileLineStructure {
	String id;
	float version;
	String value;

	public FileLineStructure(String id, float version, String value) {
		this.id = id;
		this.version = version;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
