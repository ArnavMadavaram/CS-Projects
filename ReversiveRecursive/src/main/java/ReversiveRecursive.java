import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ReversiveRecursive {

	public static String reverse(String s) {
		if (s == null || s.length() <= 1) {
			return s;
		}
		return reverse(s.substring(1)) + s.charAt(0);
	}

	public static void wordCount(RandomAccessFile reader, ArrayList<Word> words) throws IOException {
		try {
			String word = reader.readUTF();
			Word newWord = new Word(word);

			if (words.contains(newWord)) {
				words.get(words.indexOf(newWord)).count();
			} else {
				words.add(newWord);
			}

			wordCount(reader, words);
		} catch (EOFException e) {
		}
	}
}
