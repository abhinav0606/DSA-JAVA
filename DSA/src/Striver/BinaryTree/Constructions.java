package Striver.BinaryTree;

import java.util.*;

public class Constructions {
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

    public boolean uniqueBinaryTree(int a, int b) {
        return !(a == b || (a == 1 && b == 3) || (a == 3 && b == 1));
    }

    // Build Binary Tree from PreOrder and Inorder
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> inOrderMap = new HashMap<>();
        for (int i=0;i<inorder.length;i++) {
            inOrderMap.put(inorder[i],i);
        }

        int inStart = 0;
        int inEnd = inorder.length-1;

        int preStart = 0;
        int preEnd = preorder.length-1;

        return buildTree(preorder,preStart,preEnd,inorder,inStart,inEnd,inOrderMap);
    }
    private TreeNode buildTree(int[] preOrder,int preStart, int preEnd,
                               int[] inOrder,int inStart,
                               int inEnd,Map<Integer,Integer> inOrderMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preOrder[preStart]);
        int index = inOrderMap.get(preOrder[preStart]);
        int numberLeft = index - inStart;
        root.left = buildTree(preOrder,preStart+1,preStart+numberLeft,inOrder,inStart,index-1,inOrderMap);
        root.right = buildTree(preOrder,preStart+numberLeft+1,preEnd,inOrder,index+1,inEnd,inOrderMap);
        return root;
    }

    public TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        Map<Integer,Integer> inOrderMap = new HashMap<>();
        for (int i=0;i<inorder.length;i++) {
            inOrderMap.put(inorder[i],i);
        }

        int inStart = 0;
        int inEnd = inorder.length-1;

        int postStart = 0;
        int postEnd = postorder.length-1;

        return buildTreeInPost(postorder,postStart,postEnd,inorder,inStart,inEnd,inOrderMap);
    }

    private TreeNode buildTreeInPost(int[] postOrder,int postStart, int postEnd,
                               int[] inOrder,int inStart,
                               int inEnd,Map<Integer,Integer> inOrderMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(postOrder[postEnd]);
        int index = inOrderMap.get(postOrder[postEnd]);
        int numberLeft = index - inStart;
        root.left = buildTree(postOrder,postStart,postStart+numberLeft-1,inOrder,inStart,index-1,inOrderMap);
        root.right = buildTree(postOrder,postStart+numberLeft,postEnd-1,inOrder,index+1,inEnd,inOrderMap);
        return root;
    }

    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder s = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode polledNode = queue.poll();
            if (polledNode == null) {
                s.append("#,");
            } else {
                s.append(polledNode.data).append(",");
                queue.add(polledNode.left);
                queue.add(polledNode.right);
            }
        }
        return s.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] s  = data.split(",");
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(s[index]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode polledNode = queue.poll();
            index = index + 1;
            if (!Objects.equals(s[index], "#")) {
                polledNode.left = new TreeNode(Integer.parseInt(s[index]));
                queue.add(polledNode.left);
            }
            index = index + 1;
            if (!Objects.equals(s[index], "#")) {
                polledNode.right = new TreeNode(Integer.parseInt(s[index]));
                queue.add(polledNode.right);
            }
        }
        return root;
    }

}
