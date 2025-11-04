package stackoverflow.entities;

import stackoverflow.enums.EventType;

public class Event {
	private final EventType type;
	private final User actor;
	private final Post targetPost;
	
	public Event(EventType type, User actor, Post targetPost) {
		this.type = type;
		this.actor = actor;
		this.targetPost = targetPost;
	}
	
	public EventType getEventType() {
		return this.type;
	}
	
	public User getActor() {
		return this.actor;
	}
	
	public Post getTargetPost() {
		return this.targetPost;
	}
	
}
