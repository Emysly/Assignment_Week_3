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
		//founds '=' character to have two words which forms an array and it also
		// checks that the array is at least 2 words, finally store it
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

		//gets the values of the config map
		String dbName = config.get("dbname");
		String environmentDbname = config.get("application.name");
		System.out.println(dbName);
		System.out.println(environmentDbname);
	}
	public static void main(String[] args) {

		try {
			//checks if an arg is provided from the terminal
			if(args.length != 0) {
				//scans the input from command line args
				switch (args[0].toLowerCase()) {
					case "development" ->
						//if arg is development, pass the development file
						configEnv("config.txt.dev");

					case "staging" ->
						//if arg is staging, pass the staging file
						configEnv("config.txt.staging");
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

