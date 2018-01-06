package pt.joaobrito.event.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.joaobrito.event.foreground.MyEJB;

@Named
@RequestScoped
public class EventProducer {

	@Inject
	private MyEJB myEJB;

	public void init() throws InterruptedException {
		myEJB.sendEvent();
	}


}
