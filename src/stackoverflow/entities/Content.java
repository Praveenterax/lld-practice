package stackoverflow.entities;

import java.time.LocalDateTime;

public abstract class Content {
	protected final String id;
	protected final String body;
	protected final User author;
	protected final LocalDateTime createdAt;
	
	public Content(String id, String body, User author) {
		this.id = id;
		this.body = body;
		this.author = author;
		this.createdAt = LocalDateTime.now();
	}
	
	public String getId() { return this.id; }
	public String getBody() {
		return this.body;
	}
	public User getAuthor() {
		return this.author;
	}

}
