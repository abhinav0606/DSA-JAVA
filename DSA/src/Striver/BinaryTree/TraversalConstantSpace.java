package Striver.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class TraversalConstantSpace {
    public static void main(String[] args) {

    }
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            data = val;
            left = null; right = null;
        }
    }
    public List<Integer> getInorder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                result.add(current.data);
                current = current.right;
            } else {
                TreeNode previous = current.left;
                while (previous.right != null && previous.right != current) {
                    previous = previous.right;
                }
                if (previous.right == null) {
                    previous.right = current;
                    current = current.left;
                } else {
                    previous.right = null;
                    result.add(current.data);
                    current = current.right;
                }
            }
        }
        return result;
    }
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                result.add(current.data);
                current = current.right;
            } else {
                TreeNode previous = current.left;
                while (previous.right != null && previous.right != current) {
                    previous = previous.right;
                }
                if (previous.right == null) {
                    previous.right = current;
                    result.add(current.data);
                    current = current.left;
                } else {
                    previous.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }
}
