import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * This class should not be modified in any way.
 * Develop the ReversiveRecursive class to satisfy 
 * all of the tests below.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecursiveTests {
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(output));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	@Order(1)
	void reverseWords() throws IOException {
		RandomAccessFile reader = new RandomAccessFile("nevergonna.bin", "r");
		String words = "";
		try {
			while(true) {
				words += ReversiveRecursive.reverse(reader.readUTF()) + " ";
			}
		}
		catch (EOFException e) {
			//Done reading
			System.out.print(words);
			reader.close();

			assertEquals("er'ew on sregnarts ot evol uoy wonk eht "
					+ "selur dna os od i a lluf s'tnemtimmoc tahw "
					+ "m'i gnikniht fo uoy t'ndluow teg siht morf "
					+ "yna rehto yug i tsuj annaw llet uoy woh m'i "
					+ "gnileef attog ekam uoy dnatsrednu reven annog "
					+ "evig uoy pu reven annog tel uoy nwod reven annog "
					+ "nur dnuora dna tresed uoy reven annog ekam uoy yrc "
					+ "reven annog yas eybdoog reven annog llet a eil dna "
					+ "truh uoy ev'ew nwonk hcae rehto rof os gnol ruoy "
					+ "s'traeh neeb gnihca tub er'uoy oot yhs ot yas ti "
					+ "edisni ew htob wonk s'tahw neeb gniog no ew wonk eht "
					+ "emag dna er'ew annog yalp ti dna fi uoy ksa em woh "
					+ "m'i gnileef t'nod llet em er'uoy oot dnilb ot ees "
					+ "reven annog evig uoy pu reven annog tel uoy nwod reven "
					+ "annog nur dnuora dna tresed uoy reven annog ekam uoy "
					+ "yrc reven annog yas eybdoog reven annog llet a eil dna "
					+ "truh uoy reven annog evig uoy pu reven annog tel uoy "
					+ "nwod reven annog nur dnuora dna tresed uoy reven annog "
					+ "ekam uoy yrc reven annog yas eybdoog reven annog llet "
					+ "a eil dna truh uoy reven annog ,evig reven annog evig "
					+ "evig( uoy )pu ev'ew nwonk hcae rehto rof os gnol ruoy "
					+ "s'traeh neeb gnihca tub er'uoy oot yhs ot yas ti edisni "
					+ "ew htob wonk s'tahw neeb gniog no ew wonk eht emag dna "
					+ "er'ew annog yalp ti i tsuj annaw llet uoy woh m'i gnileef "
					+ "attog ekam uoy dnatsrednu reven annog evig uoy pu reven "
					+ "annog tel uoy nwod reven annog nur dnuora dna tresed uoy "
					+ "reven annog ekam uoy yrc reven annog yas eybdoog reven "
					+ "annog llet a eil dna truh uoy reven annog evig uoy pu "
					+ "reven annog tel uoy nwod reven annog nur dnuora dna tresed "
					+ "uoy reven annog ekam uoy yrc reven annog yas eybdoog reven "
					+ "annog llet a eil dna truh uoy reven annog evig uoy pu reven "
					+ "annog tel uoy nwod reven annog nur dnuora dna tresed uoy "
					+ "reven annog ekam uoy yrc reven annog yas eybdoog", 
					output.toString().trim());
		}	
	}

	@Test
	@Order(2)
	void countWords() throws IOException {
		RandomAccessFile reader = new RandomAccessFile("nevergonna.bin", "r");

		ArrayList<Word> wordCounts = new ArrayList<Word>();

		//Count occurrences of all words
		ReversiveRecursive.wordCount(reader, wordCounts);

		wordCounts.sort(null);
		System.out.print(wordCounts);
		reader.close();

		assertEquals("[gonna: 39, you: 37, never: 37, and: 15, tell: 8, make: 8, "
				+ "say: 8, give: 7, a: 6, up: 6, let: 6, down: 6, run: 6, "
				+ "around: 6, desert: 6, cry: 6, goodbye: 6, know: 5, lie: 5, "
				+ "hurt: 5, to: 4, i'm: 4, been: 4, it: 4, we: 4, we're: 3, the: 3, "
				+ "so: 3, i: 3, other: 3, how: 3, feeling: 3, you're: 3, too: 3, "
				+ "just: 2, wanna: 2, gotta: 2, understand: 2, we've: 2, known: 2, "
				+ "each: 2, for: 2, long: 2, your: 2, heart's: 2, aching: 2, "
				+ "but: 2, shy: 2, inside: 2, both: 2, what's: 2, going: 2, on: 2, "
				+ "game: 2, play: 2, me: 2, no: 1, strangers: 1, love: 1, rules: 1, "
				+ "do: 1, full: 1, commitment's: 1, what: 1, thinking: 1, of: 1, "
				+ "wouldn't: 1, get: 1, this: 1, from: 1, any: 1, guy: 1, if: 1, "
				+ "ask: 1, don't: 1, blind: 1, see: 1, give,: 1, (give: 1, up): 1]", 
				output.toString().trim());

	}
}