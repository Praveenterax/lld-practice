package stackoverflow.entities;

import java.util.UUID;

public class Answer extends Post {
	private boolean isAccepted = false;
	
	public Answer(String body, User author) {
		super(UUID.randomUUID().toString(), body, author);
	}
	
	public void setAccepted(boolean value) {
		this.isAccepted = value;
	}
	
	public boolean getIsAccepted() {
		return this.isAccepted;
	}
	
}
