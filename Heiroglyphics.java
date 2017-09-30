import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

// Note: "Heiroglyphics" is the name of the problem,
// despite "hieroglyphics" being the correct spelling.
// https://icpcarchive.ecs.baylor.edu/external/60/6059.pdf
public class Heiroglyphics {
	
	public static final PrintStream t = System.out;
	public static final boolean debug = false;
	
	public static String[] vowels = {
			"110101",
			"101101",
			"010101",
			"111011",
	};
	public static String[] consonants = {
			"110111",
			"110011",
			"110000",
			"101111",
			"101011",
			"101000",
			"010111",
			"010011",
			"010000",
			"111101",
			"111111",
			"111000",
	};
	// Maps of incomplete symbols to the numbers of symbols that could satisfy them
	public static Map<String, Integer> vowelMatches = new HashMap<>(729);
	public static Map<String, Integer> consMatches = new HashMap<>(729);
	
	public static void main(String[] args) throws Throwable {
		long beforeTime = System.nanoTime();
		char[] tabletChars = {'0', '1', '?'};
		for (char c1 : tabletChars)
		for (char c2 : tabletChars)
		for (char c3 : tabletChars)
		for (char c4 : tabletChars)
		for (char c5 : tabletChars)
		for (char c6 : tabletChars) {
			String key = "" + c1 + c2 + c3 + c4 + c5 + c6;
			int numVowelMatches = 0;
			for (String vowel : vowels) {
				if (letterMatches(key, vowel)) {
					numVowelMatches++;
				}
			}
			vowelMatches.put(key, numVowelMatches);
			int numConsMatches = 0;
			for (String consonant : consonants) {
				if (letterMatches(key, consonant)) {
					numConsMatches++;
				}
			}
			consMatches.put(key, numConsMatches);
		}
		long afterTime = System.nanoTime();
		if (debug) {
			t.println("Match maps initialized in " + (afterTime - beforeTime) / 1000000.0 + " ms");
		}
		
		BufferedReader reader = new BufferedReader(new FileReader("src/heiroglyphics.txt"));
		int numTablets = Integer.parseInt(reader.readLine());
		for (int iTablet = 0; iTablet < numTablets; iTablet++) {
			int numSymbols = Integer.parseInt(reader.readLine());
			String[] tablet = new String[numSymbols];
			for (int i = 0; i < numSymbols; i++) {
				tablet[i] = reader.readLine();
			}
			if (debug) {
				t.print("Without partial sums: ");
				beforeTime = System.nanoTime();
				try {
					t.print(numWords_noPartial(tablet, 0, false, false));
				} catch (RuntimeException exc) {
					t.print(exc.getMessage());
				}
				afterTime = System.nanoTime();
				t.println(" (" + (afterTime - beforeTime) / 1000000.0 + " ms)");
				
				t.print("With partial sums:    ");
				beforeTime = System.nanoTime();
				try {
					t.print(numWords(tablet, 0, false, false, 0));
				} catch (RuntimeException exc) {
					t.print(exc.getMessage());
				}
				afterTime = System.nanoTime();
				t.println(" (" + (afterTime - beforeTime) / 1000000.0 + " ms)");
			} else {
				try {
					t.println(numWords(tablet, 0, false, false, 0));
				} catch (RuntimeException exc) {
					t.println(exc.getMessage());
				}
			}
		}
		reader.close();
	}
	
	public static boolean letterMatches(String pattern, String letter) {
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) != letter.charAt(i) && pattern.charAt(i) != '?') {
				return false;
			}
		}
		return true;
	}
	
	// The partialSum parameter is supposed to help us realize a little faster if we've hit 10000 words
	public static int numWords(String[] tablet, int cur, boolean vowelPresent, boolean vowelPrev, int partialSum) {
		if (cur == tablet.length) {
			return 1;
		}
		// If we're at the last one and there hasn't been a vowel, this one must be a vowel
		if (cur == tablet.length - 1 && !vowelPresent) {
			return vowelMatches.get(tablet[cur]);
		}
		// No matter what, we may have a consonant at this point
		int res = consMatches.get(tablet[cur]) * numWords(tablet, cur + 1, vowelPresent, false, partialSum);
		testWordCount(partialSum + res);
		// If not immediately preceded by a vowel, we may have a vowel
		if (!vowelPrev) {
			res += vowelMatches.get(tablet[cur]) * numWords(tablet, cur + 1, true, true, partialSum + res);
			testWordCount(partialSum + res);
		}
		return res;
	}
	
	// For comparison with numWords
	public static int numWords_noPartial(String[] tablet, int cur, boolean vowelPresent, boolean vowelPrev) {
		if (cur == tablet.length) {
			return 1;
		}
		// If we're at the last one and there hasn't been a vowel, this one must be a vowel
		if (cur == tablet.length - 1 && !vowelPresent) {
			return vowelMatches.get(tablet[cur]);
		}
		// No matter what, we may have a consonant at this point
		int res = consMatches.get(tablet[cur]) * numWords_noPartial(tablet, cur + 1, vowelPresent, false);
		testWordCount(res);
		// If not immediately preceded by a vowel, we may have a vowel
		if (!vowelPrev) {
			res += vowelMatches.get(tablet[cur]) * numWords_noPartial(tablet, cur + 1, true, true);
			testWordCount(res);
		}
		return res;
	}
	
	public static void testWordCount(int n) {
		if (n > 10000) {
			throw new RuntimeException("CANNOT DECIPHER");
		}
	}
}
