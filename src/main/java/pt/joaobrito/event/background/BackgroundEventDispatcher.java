package pt.joaobrito.event.background;

import java.io.Serializable;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import pt.joaobrito.event.BaseEvent;
import pt.joaobrito.events.annoation.InBackground;

@MessageDriven(mappedName = "myQueue", activationConfig = {
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BackgroundEventDispatcher implements MessageListener {

	public BackgroundEventDispatcher() {
	}

	@Inject
	@InBackground
	private Event<BaseEvent> event;

	/**
	 * dispatches the message received from the queue and fires a new event (now with the @InBackground qualifier)
	 *
	 * @param message
	 */
	public void onMessage(Message message) {
		if (!(message instanceof ObjectMessage)) {
			throw new RuntimeException("Invalid message type received");
		}
		ObjectMessage msg = (ObjectMessage) message;
		try {
			Serializable eventObject = msg.getObject();
			if (!(eventObject instanceof BaseEvent)) {
				throw new RuntimeException("Unknown event type received");
			}
			BaseEvent evt = (BaseEvent) eventObject;
			this.event.fire(evt);

		} catch (JMSException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}
