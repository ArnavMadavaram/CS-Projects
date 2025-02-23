package lab;

//Do not change any code in this file
/**
 * This class represents a question in the game.
 * It can only be answered once.
 */
public class Question {

	private String question;
	private String answer;
	private double value;
	private boolean answered;
	
	public Question(String question, String answer, double value) {
		this.question = question;
		this.answer = answer;
		this.value = value;
		this.answered = false;
	}
	
	/**
	 * Checks if the answer is correct
	 * @param answer with "what is " and "?" removed
	 * @return true if the answer is correct (ignoring case)
	 */
	public boolean checkAnswer(String answer) {
		return this.answer.equalsIgnoreCase(answer);
	}
	
	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	
	public boolean isAnswered() {
        return answered;
	}
	
	public double getValue() {
		return value;
	}
	
	public String toString() {
		return question;
	}
}
