import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main extends ConfigParser {

	//a method that handles the environment implementations
	public static void configEnv(String fileName, String mode) throws IOException {

		fileName = fileName.replace(".txt", mode);
		//pass the file to the configparser
		final ConfigParser config = new ConfigParser(fileName);

		//get the file path
		final Path path = Paths.get(config.getFilename());

		//add all lines to an array
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

		try {

			//checks if an arg is provided from the terminal
			if(args.length != 0) {

				//scans the input from command line args
				switch (args[0].toLowerCase()) {

					case "development" ->

						//if arg is development, pass the development file
						configEnv("config.txt", "-dev.txt");

					case "staging" ->

						//if arg is staging, pass the staging file
						configEnv("config.txt", "-staging.txt");

				}
			}
			else{

				//without any arg pass the production file
				configEnv("config.txt", ".txt");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

