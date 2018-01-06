package pt.joaobrito.event.foreground;

import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import pt.joaobrito.event.BaseEvent;
import pt.joaobrito.event.MyEvent;
import pt.joaobrito.events.annoation.InForeground;

@LocalBean
@Stateless
public class MyEJB {

	@Inject
	@InForeground
	private Event<BaseEvent> event;

	/**
	 * fires the @InForeground event
	 *
	 * @throws InterruptedException
	 */
	public void sendEvent() throws InterruptedException {
		event.fire(new MyEvent("Hello world event", new Date()));
		Thread.sleep(5000);
	}
}
