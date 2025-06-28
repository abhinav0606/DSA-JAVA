package Striver.Tries;

public class TriesImplementation {
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

        boolean search(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                    return false;
                }
                node = node.get(ch);
            }
            return node.isEnd();
        }

        boolean startsWith(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                if (!node.containsKey(ch)) {
                    return false;
                }
                node = node.get(ch);
            }
            return true;
        }
    }

    class Node2 {
        Node2[] links;
        int cntEndWith;
        int cntStartWith;

        public Node2() {
            this.links = new Node2[26];
            this.cntEndWith = 0;
            this.cntStartWith = 0;
        }

        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        void put(char ch, Node2 node) {
            links[ch - 'a'] = node;
        }

        Node2 get(char ch) {
            return links[ch - 'a'];
        }

        void increaseEnd() {
            cntEndWith++;
        }

        void decreaseEnd() {
            cntEndWith--;
        }
        void increaseStart() {
            cntStartWith++;
        }
        void decreaseStart() {
            cntStartWith--;
        }
        int getEnd() {
            return cntEndWith;
        }
        int getPrefix() {
            return cntStartWith;
        }
    }

    class Trie2 {
        private Node2 root;
        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            Node2 node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                    node.put(ch,new Node2());
                }
                node = node.get(ch);
                node.increaseStart();
            }
            node.increaseEnd();
         }

        public int countWordsEqualTo(String word) {
            Node2 node = root;
            for (char ch : word.toCharArray()) {
                if (node.containsKey(ch)) {
                    node = node.get(ch);
                } else {
                    return 0;
                }
            }
            return node.getEnd();
        }

        public int countWordsStartingWith(String prefix) {
            Node2 node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.containsKey(ch)) {
                    node = node.get(ch);
                } else {
                    return 0;
                }
            }
            return node.getPrefix();
        }

        public void erase(String word) {
            Node2 node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                    node = node.get(ch);
                    node.decreaseStart();
                } else {
                    return;
                }
            }
            node.decreaseEnd();
        }
    }
}
