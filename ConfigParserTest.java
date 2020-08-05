import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {

    ConfigParser config = null;
    @BeforeEach
    void setUp() {
        config = new ConfigParser("config.txt");
    }

    @Test
    void getFilename() {
        assertTrue(config.getFilename().length() > 0);
        assertNotNull(config.getFilename());
    }

    @Test
    void getEnv() {
        assertNotNull(config.getEnv());
    }

    @Test
    void get() {
        assertSame(config.get("mode"), config.get("mode"));
        assertSame(config.get("dbname"), config.get("dbname"));
        assertSame(config.get("application.name"), config.get("application.name"));
    }

    @AfterEach
    void tearDown() {
        config = null;
    }
}