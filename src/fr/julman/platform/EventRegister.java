package fr.julman.platform;

import java.util.function.Consumer;

import fr.julman.platform.events.common.CommonEvent;
import fr.julman.platform.events.common.Event;

public interface EventRegister extends CommonEvent {
	public void registerEvent(Event event, Consumer<Event> eventTrigger);
	public void triggerEvent(Event event);
	public void removeEvent(Event event);
	public void removeEvent(Event event, Consumer<Event> eventTrigger);
}
