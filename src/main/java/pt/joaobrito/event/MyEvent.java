package pt.joaobrito.event;

import java.util.Date;

public class MyEvent extends BaseEvent {

	private String data;

	private Date eventTime;

	public String getData() {
		return data;
	}

	public MyEvent(String data, Date eventTime) {
		this.data = data;
		this.eventTime = eventTime;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
}
