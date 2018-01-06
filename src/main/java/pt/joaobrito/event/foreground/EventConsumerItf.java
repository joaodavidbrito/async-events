package pt.joaobrito.event.foreground;

import javax.ejb.Local;
import javax.enterprise.event.Observes;
import pt.joaobrito.event.MyEvent;
import pt.joaobrito.events.annoation.InBackground;

@Local
public interface EventConsumerItf {
	void afterMyEvent(@Observes @InBackground MyEvent event);
}
