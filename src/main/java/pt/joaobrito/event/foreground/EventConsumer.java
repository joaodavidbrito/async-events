package pt.joaobrito.event.foreground;

import java.text.SimpleDateFormat;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import pt.joaobrito.event.MyEvent;
import pt.joaobrito.events.annoation.InBackground;

@Stateless
public class EventConsumer implements EventConsumerItf {

	/**
	 * consumes the event sent by the JMS queue
	 *
	 * @param event
	 */
	@Override
	public void afterMyEvent(@Observes @InBackground MyEvent event) {
		System.out.println("Event message: " + event.getData());
		System.out.println("Event time: " + new SimpleDateFormat("yyyy.MM.dd @ HH:mm:ss").format(event.getEventTime()));
	}

}
