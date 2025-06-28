package Striver.BinaryTree;

import javax.swing.*;
import java.util.*;

public class Implementation {
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

    // Recursive Approach
    private void recursiveInorder(TreeNode root, List<Integer> arr) {
        if (root == null) return;
        recursiveInorder(root.left,arr);
        arr.add(root.data);
        recursiveInorder(root.right,arr);
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        recursiveInorder(root,arr);
        return arr;
    }
    // Iterative Approach
    public List<Integer> inorder2(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (root != null) {
                stack.add(root);
                root = root.left;
            }
             else {
                 if (stack.isEmpty()) break;
                 root = stack.pop();
                 arr.add(root.data);
                 root = root.right;
            }
        }
        return arr;
    }

    public List<Integer> preorder(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        recursivePreOrder(root,arr);
        return arr;
    }
    private void recursivePreOrder(TreeNode node, List<Integer> arr) {
        if (node == null) return;
        arr.add(node.data);
        recursivePreOrder(node.left,arr);
        recursivePreOrder(node.right,arr);
    }

    public List<Integer> postorder(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        recursivePostOrder(root,arr);
        return arr;
    }
    private void recursivePostOrder(TreeNode node, List<Integer> arr) {
        if (node == null) return;
        recursivePostOrder(node.left,arr);
        recursivePostOrder(node.right,arr);
        arr.add(node.data);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i=0;i<size;i++) {
                TreeNode polled = queue.poll();
                level.add(polled.data);
                if (polled.left != null) {
                    queue.add(polled.left);
                }
                if (polled.right != null) {
                    queue.add(polled.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    static class NodeState{
        TreeNode treeNode;
        int state;

        NodeState(TreeNode treeNode,int state) {
            this.treeNode = treeNode;
            this.state = state;
        }
    }
    public List<List<Integer>> treeTraversal(TreeNode root) {
        // We will use the stack method to cover up things
        Stack<NodeState> stack = new Stack<>();
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        if (root == null) return List.of(pre,in,post);
        stack.add(new NodeState(root,1));
        while (!stack.isEmpty()) {
            NodeState ns = stack.pop();
            TreeNode top = ns.treeNode;
            if (ns.state == 1) {
                pre.add(top.data);
                stack.add(new NodeState(top,2));
                if (top.left != null) {
                    stack.add(new NodeState(top.left,1));
                }
            } else if (ns.state == 2) {
                in.add(top.data);
                stack.add(new NodeState(top,3));
                if (top.right != null) {
                    stack.add(new NodeState(top.left,1));
                }
            } else {
                post.add(top.data);
            }
        }
        return List.of(pre,in,post);
    }
}
