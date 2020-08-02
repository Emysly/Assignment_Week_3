import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class Main {

	static Map<String, String> map = new LinkedHashMap<>();

	public static void configEnv(String config) throws FileNotFoundException {
		ConfigParser configEnv = new ConfigParser(config);

		// read development config file
		File f1 = new File(configEnv.getConfig());
		Scanner devIn = new Scanner(f1);

		//checks if there is next line of characters and split where it
		//founds '=' to have two words which forms an array and it also
		// checks that the array is atleast 2 words, finally store it
		// in a map if the key does not exist in the map
		while(devIn.hasNextLine()) {
			String[] arr = devIn.next().split("=");
			if(arr.length >= 2) map.putIfAbsent(arr[0], arr[1]);
		}


	}
	public static void main(String[] args) {


		//read prod, dev and staging files
		try {
			if(args.length != 0) {
				//scans the input from command line args
				switch (args[0]) {

					case "development": {

						configEnv("config.txt.dev");

						//use the second map to map the properties of the config file that is stored in the first map
						Map<String, String> devEnv = new LinkedHashMap<>();

						devEnv.put("dbName", map.get("dbname"));
						devEnv.put("developmentDbname", map.get("name"));
						System.out.println(devEnv);
						break;
					}
					case "staging": {
						configEnv("config.txt.staging");

						//use the second map to map the properties of the config file
						// that is stored in the first map
						Map<String, String> stagingEnv = new LinkedHashMap<>();

						stagingEnv.put("dbName", map.get("dbname"));
						stagingEnv.put("stagingDbname", map.get("name"));
						System.out.println(stagingEnv);
						break;
					}
				}

			}
			else{
				configEnv("config.txt");

				//use the second map to map the properties of the config file that is stored in the first map
				Map<String, String> prodEnv = new LinkedHashMap<>();

				prodEnv.put("dbName", map.get("dbname"));
				prodEnv.put("productionDbname", map.get("name"));
				System.out.println(prodEnv);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

