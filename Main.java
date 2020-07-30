import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Properties propsProd = new Properties();
		Properties propsDev = new Properties();
		Properties propsStaging = new Properties();

		ConfigParser configProd = new ConfigParser("config.txt");
		ConfigParser configDev = new ConfigParser("config.txt.dev");
		ConfigParser configStaging = new ConfigParser("config.txt.staging");
		
		try (FileInputStream prod = new FileInputStream(configProd.getConfig());
			 FileInputStream dev = new FileInputStream(configDev.getConfig());
			 FileInputStream staging = new FileInputStream(configStaging.getConfig())
		) {

			//loads the prod file
		    propsProd.load(prod);
			propsDev.load(dev);
			propsStaging.load(staging);

		    //scans the input from the
			Scanner scanner = new Scanner(System.in);

			if (args.length != 0) {
				switch (args[0]) {
					case "prod" -> {
						String dbName = propsProd.getProperty("dbname");
						String prodDbname = propsProd.getProperty("name");

						System.out.println(dbName + " " + prodDbname);
					}
					case "dev" -> System.out.println(propsDev.getProperty("mode"));
					case "staging" -> System.out.println(propsStaging.getProperty("mode"));
				}
			}

			Map<String, String> env = new LinkedHashMap<>();
			env.put("prod", propsProd.getProperty("dbname"));
			env.put("dev", propsDev.getProperty("mode"));
			env.put("staging", propsStaging.getProperty("mode"));
			System.out.print(env.toString());
		}
	}

}
