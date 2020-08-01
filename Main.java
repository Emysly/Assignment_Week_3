import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		Properties propsProd = new Properties();
		Properties propsDev = new Properties();
		Properties propsStaging = new Properties();

		//passed prod, dev and staging file to the configParser
		ConfigParser configProd = new ConfigParser("config.txt");
		ConfigParser configDev = new ConfigParser("config.txt.dev");
		ConfigParser configStaging = new ConfigParser("config.txt.staging");

		//read prod, dev and staging files
		try (FileInputStream prod = new FileInputStream(configProd.getConfig());
			 FileInputStream dev = new FileInputStream(configDev.getConfig());
			 FileInputStream staging = new FileInputStream(configStaging.getConfig())
		) {


			//loads the prod file
		    propsProd.load(prod);

			//loads the dev file
			propsDev.load(dev);

			//loads the staging file
			propsStaging.load(staging);


			Map<String, String> env = new LinkedHashMap<>();

			if (args.length != 0) {

				//scans the input from command line args
				switch (args[0]) {
					case "production": {
						env.put("dbName", propsProd.get("dbname").toString());
						env.put("productionDbname", propsProd.get("name").toString());
						System.out.println(env);
						break;
					}
					case "development": {
						env.put("dbName", propsDev.get("dbname").toString());
						env.put("devDbname", propsDev.get("name").toString());
						System.out.println(env);
						break;
					}
					case "staging": {
						env.put("dbName", propsStaging.get("dbname").toString());
						env.put("stagingDbname", propsStaging.get("name").toString());
						System.out.println(env);
						break;
					}
				}
			}

		}
	}


}

