package Striver.BinarySearchTree;

import java.util.*;

public class FAQs {
    public static void main(String[] args) {

    }

    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            data = val;
            left = null;
            right = null;
        }
    }
    static class BSTIterator {
        Stack<Medium.TreeNode> stack = new Stack<>();
        public BSTIterator(Medium.TreeNode root) {
            pushAllLeft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            Medium.TreeNode node = stack.pop();
            pushAllLeft(node.right);
            return node.data;
        }
        private void  pushAllLeft(Medium.TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    public boolean twoSumBST(TreeNode root, int k) {
        List<Integer> set = new ArrayList<>();
        if (root == null) return false;
        inOrderTraversal(root,set);
        int l = 0;
        int r = set.size()-1;
        while (l < r) {
            if (set.get(l) + set.get(r) == k) return true;
            else if (set.get(l) + set.get(r) < k) l++;
            else r--;
        }
        return false;
    }
    private void inOrderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inOrderTraversal(node.left,list);
        list.add(node.data);
        inOrderTraversal(node.right,list);
    }

    static TreeNode first = null;
    static  TreeNode middle = null;
    static TreeNode prev = null;
    static TreeNode last = null;
    void recoverTree(TreeNode root) {
        morrisInorder(root);
        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        } else if (first != null && middle != null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }
    void morrisInorder(TreeNode node) {
        if (node == null) return;
        morrisInorder(node.left);
        if (prev != null && prev.data > node.data) {
            if (first == null) {
                first = prev;
                middle = node;
            } else {
                last = node;
            }
        }
        prev = node;
        morrisInorder(node.right);
    }
}
