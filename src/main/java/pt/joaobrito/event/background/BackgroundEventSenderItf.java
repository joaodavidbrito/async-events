package pt.joaobrito.event.background;

import javax.ejb.Local;
import javax.enterprise.event.Observes;
import pt.joaobrito.event.BaseEvent;
import pt.joaobrito.events.annoation.InForeground;

@Local
public interface BackgroundEventSenderItf {
	void event(@Observes @InForeground BaseEvent event);
}
