package com.alpha.message.service.concurrent.event;

import java.util.Map;
import java.util.Set;

public class NotfiyEvent extends BaseEvent {
	private static final long serialVersionUID = 5336376667519765718L;
	private String event;
	private Set<Long> user;
	private Map<String, Object> data;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Set<Long> getUser() {
		return user;
	}

	public void setUser(Set<Long> user) {
		this.user = user;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
