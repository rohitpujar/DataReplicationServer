import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteObjects {

	public static void main(String[] args) {

		writeObject("b", "00");
	}

	public static void writeObject(String id, String value) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/home/rohit/kepler_workspace/FileReplicationServer/Objects"));
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
			for (FileLineStructure fileLine : fileLines) {
				if (fileLine.getId().equals(id)) {
					found = true;
					fileLine.setVersion((float) (fileLine.getVersion() + .01));
					fileLine.setValue(value);
					break;
				}
			}
			if (!found) {
				FileLineStructure fileLine = new FileLineStructure(id, ((float) 1.01), value);
				fileLines.add(fileLine);
			}
			reader.close();
			FileWriter fw = new FileWriter("/home/rohit/kepler_workspace/FileReplicationServer/Objects");
			for (FileLineStructure fileLine : fileLines) {
				String lineToWrite = fileLine.getId() + "," + Float.toString(fileLine.getVersion()) + "," + fileLine.getValue() + "\n";
				fw.write(lineToWrite);
			}
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class FileLineStructure
{
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
