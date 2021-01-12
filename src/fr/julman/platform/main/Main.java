package fr.julman.platform.main;

import java.awt.HeadlessException;
import java.net.URISyntaxException;

import org.json.simple.parser.ParseException;

import fr.julman.platform.Application;
import fr.julman.platform.Launcher;
import fr.julman.platform.exceptions.ApplicationAlreadyDisplayedException;

public class Main {
	public static Application APPLICATION;
	
	public static void launchGame() throws ParseException {
		try {
			Launcher.LOGGER.info("main", "Openning window...");
			APPLICATION = Application.openNewWindow();
			APPLICATION.setVisible(true);
			APPLICATION.setIconImage(APPLICATION.getToolkit().getImage("resources/icon.png"));
			Launcher.LOGGER.info("main", "Window displayed!");
		} catch (HeadlessException e) {
			Launcher.LOGGER.fatal("main", "Unable to open window, no display variable found!");
		} catch (ApplicationAlreadyDisplayedException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
