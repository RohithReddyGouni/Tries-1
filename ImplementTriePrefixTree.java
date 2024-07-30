// Time Complexity:
// - insert: O(m), where m is the length of the word to be inserted.
// - search: O(m), where m is the length of the word to be searched.
// - startsWith: O(m), where m is the length of the prefix.
// Space Complexity: O(N * M), where N is the number of words inserted and M is the average length of the words.

class TrieNode {
    boolean endOfWord;
    TrieNode children[];

    public TrieNode() {
        endOfWord = false;
        children = new TrieNode[26];
    }

    public boolean containsKey(char ch) {
        return children[ch - 'a'] != null;
    }

    public void put(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }

    public TrieNode get(char ch) {
        return children[ch - 'a'];
    }

    public void setEnd() {
        endOfWord = true;
    }

    public boolean isEnd() {
        return endOfWord;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (node.containsKey(currentChar)) {
                node = node.get(currentChar);
            } else {
                return null;
            }
        }
        return node;
    }
}
