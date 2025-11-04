package stackoverflow.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import stackoverflow.enums.EventType;

public class Question extends Post {
	private final String title;
	private final Set<Tag> tags;
	private final List<Answer> answers = new ArrayList<Answer>();
	private Answer acceptedAnswer;

	public Question(String title, String body, User author, Set<Tag> tags) {
		super(UUID.randomUUID().toString(), body, author);
		this.title = title;
		this.tags = tags;
	}
	
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}
	
	private boolean ifAnswerExists(String answerId) {
		return answers.stream().anyMatch(a -> a.getId().equals(answerId));
	}
	
	public synchronized void acceptAnswer(Answer answer) {
		if(!this.author.getId().equals(answer.author.getId()) && this.acceptedAnswer == null && ifAnswerExists(answer.getId())) {
			answer.setAccepted(true);
			this.acceptedAnswer = answer;
			notifyObservers(new Event(EventType.ACCEPT_ANSWER, answer.getAuthor(), answer));
		}
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Set<Tag> getTags() {
		return this.tags;
	}
	
	public List<Answer> getAnswers() {
		return this.answers;
	}

}
