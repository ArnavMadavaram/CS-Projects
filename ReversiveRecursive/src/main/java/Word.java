public class Word implements Comparable<Word>{

	private String word;
	private int count;
	
	public Word(String word) {
		this.word = word;
		count = 1;
	}
	
	public String getWord() {
		return word;
	}
	
	public int getCount() {
		return count;
	}
	
	public void count() {
		count++;
	}
	
	public boolean equals(Object other) {
		Word otherWord = (Word)other;
		
		if (word.equals(otherWord.getWord())){
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		return word + ": " + count;
	}

	public int compareTo(Word otherWord) {
		
		if (count == otherWord.getCount()) {
			return 0;
		}
		else if (count > otherWord.getCount()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
