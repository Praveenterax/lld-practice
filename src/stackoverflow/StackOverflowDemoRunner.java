package stackoverflow;


import java.util.List;
import java.util.Set;

import stackoverflow.entities.Answer;
import stackoverflow.entities.Question;
import stackoverflow.entities.Tag;
import stackoverflow.entities.User;
import stackoverflow.enums.VoteType;
import stackoverflow.strategy.KeywordSearchStrategy;
import stackoverflow.strategy.SearchStrategy;
import stackoverflow.strategy.TagSearchStrategy;

public class StackOverflowDemoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Initialize the app
		StackOverflowService application = new StackOverflowService();
		
		
		// Create users
		User adam = application.createUser("Adam");
		User bob = application.createUser("Bob");
		User charlie = application.createUser("Charlie");
		
		
		// Create tags
		Tag javaTag = new Tag("Java");
		Tag pythonTag = new Tag("Python");
		Tag programmingTag = new Tag("programming");
		
		Set<Tag> javaProgrammingTags = Set.of(javaTag, programmingTag);
		Set<Tag> pythonProgrammingTags = Set.of(pythonTag, programmingTag);
		
		
		
		// Post questions
		Question javaQue = application.postQuestion(adam.getId(), "What is Java?", "Explain please", javaProgrammingTags);
		Question javaQueWithoutTag = application.postQuestion(charlie.getId(), "Is java multithreaded?", "Multithreading is supported?", Set.of(programmingTag));
		Question pythonQue = application.postQuestion(bob.getId(), "Is python snake?", "Will it bite?", pythonProgrammingTags);
		System.out.println("After posting questions");
		printReputations(adam, bob, charlie);
		
		// Answer questions
		Answer javaAnswer = application.postAnswer(bob.getId(), javaQue.getId(), "Java is high-level object oriented programming language");
		Answer pythonAnswer = application.postAnswer(charlie.getId(), pythonQue.getId(), "Depends on which python you are referring to! Lol!..");
		System.out.println("After posting answers");
		printReputations(adam, bob, charlie);
		
		
		// Voting
		application.voteOnPost(javaQue.getId(), charlie.getId(), VoteType.UPVOTE);
		application.voteOnPost(pythonQue.getId(), adam.getId(), VoteType.DOWNVOTE);
		
		application.voteOnPost(javaAnswer.getId(), bob.getId(), VoteType.UPVOTE);
		application.voteOnPost(pythonAnswer.getId(), adam.getId(), VoteType.UPVOTE);
		
		System.out.println("After voting");
		printReputations(adam, bob, charlie);
		
		// Accept answer
		javaQue.acceptAnswer(javaAnswer);
		
		System.out.println("After accepting");
		printReputations(adam, bob, charlie);
		
		// Search questions
		List<SearchStrategy> strategies = List.of(
				new KeywordSearchStrategy("java"),
				new TagSearchStrategy(javaTag)
		);
		
		List<Question> filteredQues = application.searchQuestions(strategies);
		filteredQues.forEach(q -> System.out.println("- " + q.getTitle()));
		

	}
	
	private static void printReputations(User ...users) {
		for(User user: users) {
			System.out.println("User - " + user.getName() + ", Reputation  - " + user.getRepuation());
		}
	}

}
