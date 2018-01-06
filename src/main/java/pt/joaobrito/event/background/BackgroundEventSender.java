package pt.joaobrito.event.background;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import pt.joaobrito.event.BaseEvent;
import pt.joaobrito.events.annoation.InForeground;

@Stateless
public class BackgroundEventSender implements BackgroundEventSenderItf {

	@Resource(mappedName = "myConnectionFactory") // the JNDI name for this connection factory
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "myQueue") // JNDI name for this queue
	private Queue backgroundEventQueue;

	private Connection connection;
	private Session session;
	private MessageProducer producer;

	@PostConstruct
	public void init() {
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(backgroundEventQueue);
		} catch (JMSException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	@PreDestroy
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception ex) {
			// todo: handle the exception
		}

	}

	/**
	 * sends the foreground event to the queue with the help of the producer object
	 *
	 * @param event
	 */
	@Override
	public void event(@Observes @InForeground BaseEvent event) {
		try {
			ObjectMessage msg = session.createObjectMessage();
			msg.setObject(event);
			producer.send(msg);
		} catch (JMSException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}

	}

}
