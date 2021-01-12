package fr.julman.platform;

import java.awt.EventQueue;
import java.io.File;
import java.io.InputStream;

import org.json.simple.parser.ParseException;

import fr.julman.platform.Locales.Language;
import fr.julman.platform.log.Logger;
import fr.julman.platform.main.Main;

public abstract class Launcher {
	public static final String VERSION = "0.2-DEMO";
	
	public static final Logger LOGGER = new Logger(System.out);
	public static Language LANG = Language.USA;
	public static File CONFIGURATION_FILE;

	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			try {
				Launcher.CONFIGURATION_FILE = new File(new File("").getAbsolutePath() + "/config.json");
				
				startGame();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
	}

	private static void startGame() throws ParseException {
		Main.launchGame();
	}
	
	public static InputStream accessFile(String resource) {
        InputStream input = Launcher.class.getResourceAsStream("resources/" + resource);
        if (input == null) {
            input = Launcher.class.getClassLoader().getResourceAsStream(resource);
        }

        return input;
    }
}
