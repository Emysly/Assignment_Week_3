import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
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

	//a method that handles the environment implementations
	public void configEnv(String fileName) throws IOException {
		ConfigParser config = new ConfigParser(fileName);

		//get the file path
		Path path = Paths.get(config.getConfig());

		//add all lines to an array
		List<String> strings = Files.readAllLines(path, StandardCharsets.UTF_8);

		String[] arr;
		String prefix = "";

		//loop through the array to print each line of string
		for (String string : strings) {
			arr = string.split("=");

			if (arr.length > 1)
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

		System.out.println("You are currently in the " + config.get("mode") + " mode...");

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
