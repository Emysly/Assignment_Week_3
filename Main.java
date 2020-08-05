import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main extends ConfigParser {

	//a method that handles the environment implementations
	public static void configEnv(String name_of_file, String mode) throws IOException {

		//pass the file to the configparser
		final ConfigParser config = new ConfigParser(name_of_file);

		Path path;

		//get the file path for production
		if (mode.equals("")) {
			path = Paths.get(config.getFilename());
		} else {

			//get the file path for development and staging
			path = Paths.get(config.getFilename().replace(".", "-" + mode + "."));
		}

		//add all lines read from the config file to an array
		final List<String> strings = Files.readAllLines(path, StandardCharsets.UTF_8);

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
		final String dbName = config.get("dbname");
		final String environmentDbname = config.get("application.name");
		System.out.println(dbName);
		System.out.println(environmentDbname);
	}

	public static void main(String[] args) {

		String filename = "config.txt";
		String environment = "";

		try {

			//checks if an arg is provided from the terminal
			if(args.length != 0) {

				//scans the input from command line args
				switch (args[0].toLowerCase()) {

					case "development", "staging" -> {
						environment += args[0].toLowerCase();

						//if arg is development or staging and then pass the appropriate file
						configEnv(filename, environment);
					}

					default -> System.out.println("This mode does not exist");
				}
			}
			else{

				//without any arg pass the production file
				configEnv(filename, environment);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

