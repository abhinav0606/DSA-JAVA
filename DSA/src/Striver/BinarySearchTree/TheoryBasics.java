package Striver.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class TheoryBasics {
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

    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.data != val) {
            root = root.data > val ? root.left : root.right;
        }
        return root;
    }

    public List<Integer> floorCeilOfBST(TreeNode root, int key) {
        TreeNode current = root;
        List<Integer> result = new ArrayList<>();
        int floor = -1;
        int ceil = -1;
        while (current != null) {
            if (current.data == key) floor = current.data;
            else {
                if (current.data < key) {
                    floor = current.data;
                    current = current.right;
                } else {
                    current = current.left;
                }
            }
        }
        current = root;
        while (current != null) {
            if (current.data == key) ceil = current.data;
            else {
                if (current.data > key) {
                    ceil = current.data;
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
        return new ArrayList<>(List.of(floor,ceil));
    }
}
