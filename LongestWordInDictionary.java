// Time Complexity:
// - insert: O(N * L), where N is the number of words and L is the average length of the words.
// - findLongestWord: O(N * L), where N is the number of words and L is the average length of the words.
// Space Complexity: O(N * L), where N is the number of words and L is the average length of the words.



class TrieNode {
    TrieNode[] children;
    String word;

    public TrieNode() {
        children = new TrieNode[26];
        word = null;
    }

    public void insert(String word) {
        TrieNode node = this;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.word = word;
    }

    public String findLongestWord() {
        return dfs(this);
    }

    private String dfs(TrieNode node) {
        String result = node.word != null ? node.word : "";
        for (TrieNode child : node.children) {
            if (child != null && child.word != null) {
                String childWord = dfs(child);
                if (childWord.length() > result.length() ||
                        (childWord.length() == result.length() && childWord.compareTo(result) < 0)) {
                    result = childWord;
                }
            }
        }
        return result;
    }
}

class Solution {
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            root.insert(word);
        }
        return root.findLongestWord();
    }
}
