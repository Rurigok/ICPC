import java.util.*;

public class Boggle {

    public static Set<String> dictWords = new HashSet<>();
    public static Map<Character, Map> charTree = new HashMap<Character, Map>();

    public static void main(String[] argv) {
        //System.out.println((long) 5 << 32 | 5);
        //System.exit(0);

        Scanner s = new Scanner(System.in);

        int numDictWords = s.nextInt(); s.nextLine();

        for (int i = 0; i < numDictWords; i++) {
            String word = s.nextLine();
            word = word.replace("qu", "q");
            dictWords.add(word);
            registerDictWord(charTree, word);
        }

        int numRows = s.nextInt(); s.nextLine();

        char[][] grid;
        while (numRows > 0) {
            // do stuff
            grid = new char[numRows][numRows];
            for (int i = 0; i < numRows; i++) {
                grid[i] = s.nextLine().toCharArray();
            }
            //findWords(dictWords, grid);
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    findWordsRecursive(grid, charTree, new HashSet<Long>(), x, y, "");
                }
            }
            // stop doing stuff
            numRows = s.nextInt(); s.nextLine();
        }

    }

    public static void registerDictWord(Map<Character, Map> charMap, String word) {
        if (word.length() == 0) return;
        Character firstChar = word.charAt(0);
        if (!charMap.containsKey(firstChar)) {
            charMap.put(firstChar, new HashMap<Character, Map>());
        }
        registerDictWord(charMap.get(firstChar), word.substring(1));
    }

    public static void findWordsRecursive(char[][] grid, Map<Character, Map> charMap, Set<Long> visited, int x, int y, String sofar) {
        long longCoords = (long) x << 32 | y;
        if (visited.contains(longCoords) || !charMap.containsKey(grid[x][y])) {
            return;
        }
        char curChar = grid[x][y];
        sofar += curChar;
        System.out.println(sofar);
        if (dictWords.contains(sofar)) {
            System.out.println(sofar);
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                int newX = x + dx, newY = y + dy;
                if (newX < 0 || newY < 0 || newX >= grid.length || newY >= grid.length) continue;
                char newChar = grid[newX][newY];
                long newLongCoords = (long) newX << 32 | newY;
                visited.add(newLongCoords);
                findWordsRecursive(grid, charMap.get(newChar), visited, newX, newY, sofar);
                visited.remove(newLongCoords);
            }
        }
    }
/*
    public static void findWords(ArrayList<String> words, char[][] grid) {

        // Start building word-trees
        ArrayList<Node> trees = new ArrayList<>();

        // If grid[i][j] is the beginning of a word in the list, create a new tree
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                for (String word : words) {
                    if (word.charAt(0) == grid[i][j]) {
                        trees.append(new Node(word, word.charAt(0), i, j));
                    }
                }
            }
        }



    }
*/
}

class Node {

    public String word;
    public char letter;
    public int x;
    public int y;
    public ArrayList<Node> next = new ArrayList<>();

    public Node(String word, char letter, int x, int y) {
        this.word = word;
        this.letter = letter;
        this.x = x;
        this.y = y;
    }

}
