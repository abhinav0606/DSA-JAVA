package Striver.BinaryTree;

import javax.swing.tree.TreeCellRenderer;

public class Medium {
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

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return 1 + Math.max(l,r);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.data == q.data) return true;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }
    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        if (left == -1) return -1;

        int right = dfs(root.right);
        if (right == -1) return -1;

        if (Math.abs(right-left) > 1) return -1;

        return Math.max(left,right) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        diameter[0] = 0;
        calculateDiameter(root,diameter);
        return diameter[0];
    }
    private int calculateDiameter(TreeNode root, int[] diameter) {
        if (root == null) return 0;
        int lh = calculateDiameter(root.left,diameter);
        int rh = calculateDiameter(root.right,diameter);
        diameter[0] = Math.max(diameter[0],1 + lh + rh);
        return 1 + Math.max(lh,rh);
    }

    public int maxPathSum(TreeNode root) {
        int[] sum = {1};
        maxPath(root, sum);
        return sum[0];
    }
    private int maxPath(TreeNode root, int[] sum) {
        if (root == null) return 0;
        int lPath = Math.max(0,maxPath(root.left,sum));
        int rPath = Math.max(0,maxPath(root.right,sum));
        sum[0] = Math.max(sum[0],lPath + rPath + root.data);
        return Math.max(rPath,lPath) + root.data;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return checkSymmetry(root.left,root.right);
    }
    private boolean checkSymmetry(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.data != q.data) return false;
        // For mirroring check
        return checkSymmetry(p.left,q.right) && checkSymmetry(p.right,q.left);
    }
}
