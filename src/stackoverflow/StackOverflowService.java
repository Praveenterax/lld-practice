package stackoverflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import stackoverflow.entities.Answer;
import stackoverflow.entities.Post;
import stackoverflow.entities.Question;
import stackoverflow.entities.Tag;
import stackoverflow.entities.User;
import stackoverflow.enums.VoteType;
import stackoverflow.observer.PostObserver;
import stackoverflow.observer.ReputationManager;
import stackoverflow.strategy.SearchStrategy;

public class StackOverflowService {
	private final Map<String, User> users = new ConcurrentHashMap<String, User>();
	private final Map<String, Question> questions = new ConcurrentHashMap<String, Question>();
	private final Map<String, Answer> answers = new ConcurrentHashMap<String, Answer>();
	private final PostObserver reputationManager = new ReputationManager();
	
	public User createUser(String name) {
		User user = new User(name);
		users.put(user.getId(), user);
		return user;
	}
	
	public Question postQuestion(String userId, String title, String body, Set<Tag> tags) {
		User author = users.get(userId);
		Question question = new Question(title, body, author, tags);
		questions.put(question.getId(), question);
		question.addObserver(reputationManager);
		return question;
	}
	
	public Answer postAnswer(String userId, String questionId, String body) {
		User author = users.get(userId);
		Question question = questions.get(questionId);
		Answer answer = new Answer(body, author);
		question.addAnswer(answer);
		answer.addObserver(reputationManager);
		answers.put(answer.getId(), answer);
		return answer;
	}
	
	public void voteOnPost(String postId, String userId, VoteType voteType) {
		Post post = findPostById(postId);
		User user = users.get(userId);
		post.vote(user, voteType);
	}
	
	public void acceptAnswer(String questionId, String answerId) {
		Question question = questions.get(questionId);
		Answer answer = answers.get(answerId);
		question.acceptAnswer(answer);
	}
	
	public List<Question> searchQuestions(List<SearchStrategy> strategies) {
		List<Question> results = new ArrayList<Question>(questions.values());
		for (SearchStrategy strategy: strategies) {
			results = strategy.filter(results);
		}
		return results;
	}
	
	public Post findPostById(String postId) {
		if (questions.containsKey(postId)) {
			return questions.get(postId);
		} else if (answers.containsKey(postId)) {
			return answers.get(postId);
		} else {
			throw new NoSuchElementException("No Post found");
		}
	}
}
