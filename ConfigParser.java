
public class ConfigParser {

	String config;

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

	@Override
	public String toString() {
		return "ConfigParser{" +
				"config='" + config + '\'' +
				'}';
	}
}
