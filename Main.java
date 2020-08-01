import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		Properties prodProps = new Properties();
		Properties devProps = new Properties();
		Properties stagingProps = new Properties();

		//passed prod, dev and staging file to the configParser
		ConfigParser prodConfig = new ConfigParser("config.txt");
		ConfigParser devConfig = new ConfigParser("config.txt.dev");
		ConfigParser stagingConfig = new ConfigParser("config.txt.staging");

		//read prod, dev and staging files
		try (FileInputStream prod = new FileInputStream(prodConfig.getConfig());
			 FileInputStream dev = new FileInputStream(devConfig.getConfig());
			 FileInputStream staging = new FileInputStream(stagingConfig.getConfig())
		) {


			//loads the prod file
		    prodProps.load(prod);

			//loads the dev file
			devProps.load(dev);

			//loads the staging file
			stagingProps.load(staging);


			Map<String, String> env = new LinkedHashMap<>();

			if (args.length != 0) {

				//scans the input from command line args
				switch (args[0]) {
					case "production": {
						env.put("dbName", prodProps.get("dbname").toString());
						env.put("productionDbname", prodProps.get("name").toString());
						System.out.println(env);
						break;
					}
					case "development": {
						env.put("dbName", devProps.get("dbname").toString());
						env.put("devDbname", devProps.get("name").toString());
						System.out.println(env);
						break;
					}
					case "staging": {
						env.put("dbName", stagingProps.get("dbname").toString());
						env.put("stagingDbname", stagingProps.get("name").toString());
						System.out.println(env);
						break;
					}
				}
			}

		}
	}


}

