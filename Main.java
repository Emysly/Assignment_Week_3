import java.io.*;
import java.util.*;

public class Main {

	static Map<String, String> config = new LinkedHashMap<>();

	public static void configEnv(String fileName) throws FileNotFoundException {
		ConfigParser configEnv = new ConfigParser(fileName);

		// read development config file
		File configFile = new File(configEnv.getConfig());
		Scanner scanner = new Scanner(configFile);

		String[] arr;
		String prefix = "";

		//checks if there is next line of characters and split where it
		//founds '=' to have two words which forms an array and it also
		// checks that the array is atleast 2 words, finally store it
		// in a map if the key does not exist in the map
		while(scanner.hasNextLine()) {
			arr = scanner.next().split("=");
			if(arr.length > 1) {

				//puts if the key doesn't already exist in the map
				config.putIfAbsent(prefix + arr[0], arr[1]);
				}

			//checks if the first item in the array has '[' character
			if (arr[0].contains("[")) {
				prefix = arr[0].substring(1, arr[0].length() - 1);
				prefix += ".";
			}
			//if there is nothing in the array
			if (arr[0].length() == 0) prefix = "";
		}

		//use the second map to map the properties of the config file that is stored in the first map
		Map<String, String> env = new LinkedHashMap<>();

		//add the values of the config map to env map
		env.put("dbName", config.get("dbname"));
		env.put("environmentDbname", config.get("application.name"));
		System.out.println(env);

	}
	public static void main(String[] args) {

		try {
			//checks if an arg is provided from the terminal
			if(args.length != 0) {

				//scans the input from command line args
				switch (args[0].toLowerCase()) {
					case "development": {

						//if arg is development, pass the development file
						configEnv("config.txt.dev");
						break;
					}
					case "staging": {

						//if arg is staging, pass the staging file
						configEnv("config.txt.staging");
						break;
					}
				}

			}
			else{
				//without any arg pass the production file
				configEnv("config.txt");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

