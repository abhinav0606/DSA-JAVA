package Striver.Tries;

import java.util.List;

public class Problems {
    public static void main(String[] args) {
        
    }
    class Node {
        Node[] links = new Node[26];
        boolean flag = false;

        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        Node get(char ch) {
            return links[ch - 'a'];
        }

        void setEnd() {
            flag = true;
        }

        boolean isEnd() {
            return flag;
        }
    }

    class Trie {
        private Node root;

        Trie() {
            root = new Node();
        }

        void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                    node.put(ch, new Node());
                }
                node = node.get(ch);
            }
            node.setEnd();
        }

        boolean startsWith(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.containsKey(ch)) {
                    node = node.get(ch);
                    if (!node.isEnd()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
    }
    public String completeString(List<String> nums) {
        Trie ob = new Trie();
        for (String s : nums) {
            ob.insert(s);
        }
        String longest = "";
        for (String s : nums) {
            if (ob.startsWith(s)) {
                if (s.length() > longest.length()) {
                    longest = s;
                } else if (s.length() == longest.length() && s.compareTo(longest) < 0) {
                    longest = s;
                }
            }
        }
        return longest.isEmpty() ? "None" : longest;
    }

    public int countDistinctSubstring(String s) {
        Node root = new Node();
        int count = 0;
        for (int i=0;i<s.length();i++) {
            Node node = root;
            for (int j=i;j<s.length();j++) {
                if (!node.containsKey(s.charAt(j))) {
                    count++;
                    node.put(s.charAt(j),new Node());
                }
                node = node.get(s.charAt(j));
            }
        }
        return count+1;
    }
}
