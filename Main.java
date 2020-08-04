import java.io.*;

public class Main extends ConfigParser {

	public static void main(String[] args) {

		ConfigParser config = new ConfigParser();

		try {

			//checks if an arg is provided from the terminal
			if(args.length != 0) {

				//scans the input from command line args
				switch (args[0].toLowerCase()) {

					case "development" ->

						//if arg is development, pass the development file
						config.configEnv("config.txt.dev");

					case "staging" ->

						//if arg is staging, pass the staging file
						config.configEnv("config.txt.staging");

				}
			}
			else{

				//without any arg pass the production file
				config.configEnv("config.txt");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

