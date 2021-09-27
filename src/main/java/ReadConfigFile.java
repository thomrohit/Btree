import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadConfigFile {
	
	/**
	 * Reads from the config file and sends the List with the entire data to populate Btree
	 * @return ArrayList
	 */
	public ArrayList<String> readConfig() {

		ArrayList<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/estate.config"))) {

			String line;
			while ((line = br.readLine()) != null) {
				List<String> tmpList = Arrays.asList(line.split(":|-")); // split the line into array based on ":" or "-"
				tmpList.replaceAll(e -> e.trim());
				tmpList.set(tmpList.size() - 1 , "-" + tmpList.get(tmpList.size()- 1));  
				list.addAll(tmpList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static void main(String[] args) {
		ReadConfigFile r = new ReadConfigFile();
		r.readConfig();
	}
}
