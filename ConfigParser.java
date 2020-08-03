import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConfigParser {

	private String config;

	private Map<String, String> env = new LinkedHashMap<>();

	public ConfigParser(String config) {
		this.config = config;
	}

	public ConfigParser() {
		this.config = "default-name";
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Map<String, String> getEnv() {
		return env;
	}

	@Override
	public String toString() {
		return "ConfigParser{" +
				"config='" + config + '\'' +
				'}';
	}

	public void configEnv(String fileName) throws FileNotFoundException {
		ConfigParser config = new ConfigParser(fileName);

		// read development config file
		File configFile = new File(config.getConfig());
		Scanner scanner = new Scanner(configFile);

		String[] arr;
		String prefix = "";

		//checks if there is next line of characters and split where it
		//founds '=' character to have two words which forms an array and it also
		// checks that the array is at least 2 words, finally store it
		// in a map if the key does not exist in the map
		while(scanner.hasNextLine()) {
			arr = scanner.next().split("=");
			if(arr.length > 1)
				//puts if the key doesn't already exist in the map
				config.getEnv().putIfAbsent(prefix + arr[0], arr[1]);


			//checks if the first item in the array has '[' character
			if (arr[0].contains("[")) {
				prefix = arr[0].substring(1, arr[0].length() - 1);
				prefix += ".";
			}
			//if there is nothing in the array
			if (arr[0].length() == 0) prefix = "";
		}

		System.out.println("You are currently in the " + config.get("application.mode") + " mode...");
		//gets the values of the config map
		String dbName = config.get("dbname");
		String environmentDbname = config.get("application.name");
		System.out.println(dbName);
		System.out.println(environmentDbname);

	}

	public String get(String key) {
		return env.get(key);
	}
}
