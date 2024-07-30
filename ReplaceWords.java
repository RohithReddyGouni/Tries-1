// Time Complexity:
// - insert: O(N * L), where N is the number of words in the dictionary and L is the average length of the words.
// - replaceWords: O(N * L), where N is the number of words in the sentence and L is the average length of the words.
// Space Complexity: O(N * L), where N is the number of words in the dictionary and L is the average length of the words.

import java.util.List;

class TrieNode {
    boolean isEnd;
    TrieNode[] children;

    public TrieNode() {
        isEnd = false;
        children = new TrieNode[26];
    }
}

class Solution {
    private TrieNode root;

    public Solution() {
        root = new TrieNode();
    }

    private void insert(String word) {
        TrieNode node = root;
        for (char letter : word.toCharArray()) {
            int index = letter - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String word : dictionary) {
            insert(word);
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            TrieNode node = root;
            StringBuilder tempString = new StringBuilder();
            for (char letter : word.toCharArray()) {
                int index = letter - 'a';
                if (node.children[index] == null || node.isEnd) {
                    break;
                }
                tempString.append(letter);
                node = node.children[index];
            }

            if (node.isEnd) {
                result.append(tempString).append(" ");
            } else {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }
}
