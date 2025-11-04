package stackoverflow.strategy;

import java.util.List;

import stackoverflow.entities.Question;

public interface SearchStrategy {
	public List<Question> filter(List<Question> questions);
}
