package fr.julman.platform;

import java.awt.HeadlessException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.JFrame;

import org.json.simple.parser.ParseException;

import fr.julman.platform.events.common.Event;
import fr.julman.platform.exceptions.ApplicationAlreadyDisplayedException;

@SuppressWarnings("serial")
public class Application extends JFrame implements EventRegister {
	private static boolean alreadyDisplayed = false;
	private static Application currentApplication;
	
	private final HashMap<Event, Consumer<Event>> EVENTS = new HashMap<>();

	public static Application openNewWindow() throws ApplicationAlreadyDisplayedException, HeadlessException, URISyntaxException, ParseException {
		return new Application();
	}

	public Application() throws ApplicationAlreadyDisplayedException, HeadlessException, URISyntaxException, ParseException {
		if (alreadyDisplayed == true) {
			throw new ApplicationAlreadyDisplayedException("Can not display 2 application at same time");
		} else {
			alreadyDisplayed = true;
			currentApplication = this;
			initUI();
		}
	}

	public static Application getCurrentApplication() {
		return currentApplication;
	}

	private void initUI() throws HeadlessException, URISyntaxException, ParseException {
		this.add(new Board());

		this.setSize(750, 500);
		
		this.setIconImage(this.getToolkit().getImage("icon.png"));
		this.setTitle("Platform");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAutoRequestFocus(true);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void registerEvent(Event event, Consumer<Event> eventTrigger) {
		this.EVENTS.put(event, eventTrigger);
	}

	@Override
	public void triggerEvent(Event event) {
		this.EVENTS.forEach((Event e, Consumer<Event> executor) -> {
			if (e == event) {
				executor.accept(event);
			}
		});
	}

	@Override
	public void removeEvent(Event event) {
		this.EVENTS.remove(event);
	}

	@Override
	public void removeEvent(Event event, Consumer<Event> eventTrigger) {
		this.EVENTS.remove(event, eventTrigger);
	}
}
