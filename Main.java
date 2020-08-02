import java.io.*;
import java.util.*;

public class Main {

	static Map<String, String> config = new LinkedHashMap<>();

	public static void configEnv(String fileName) throws FileNotFoundException {
		ConfigParser configEnv = new ConfigParser(fileName);

		// read development config file
		File configFile = new File(configEnv.getConfig());
		Scanner scanner = new Scanner(configFile);


		//checks if there is next line of characters and split where it
		//founds '=' to have two words which forms an array and it also
		// checks that the array is atleast 2 words, finally store it
		// in a map if the key does not exist in the map
		while(scanner.hasNextLine()) {
			String[] arr = scanner.next().split("=");
			if(arr.length >= 2) config.putIfAbsent(arr[0], arr[1]);
		}

		//use the second map to map the properties of the config file that is stored in the first map
		Map<String, String> env = new LinkedHashMap<>();

		env.put("dbName", config.get("dbname"));
		env.put("environmentDbname", config.get("name"));
		System.out.println(env);

	}
	public static void main(String[] args) {

		//read prod, dev and staging files
		try {
			if(args.length != 0) {

				//scans the input from command line args
				switch (args[0]) {
					case "development": {
						configEnv("config.txt.dev");
						break;
					}
					case "staging": {
						configEnv("config.txt.staging");
						break;
					}
				}

			}
			else{
				configEnv("config.txt");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

