package Striver.BinarySearchTree;

import java.util.*;
import java.util.stream.Collectors;

public class Medium {
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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode current = root;
        while (true) {
            if (val < current.data) {
                if (current.left == null) {
                    current.left = new TreeNode(val);
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = new TreeNode(val);
                    break;
                } else {
                    current = current.right;
                }
            }
        }
        return root;
    }
    private TreeNode connector(TreeNode node) {
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        TreeNode leftNode = node.left;
        TreeNode leftMostRightNode = node.right;
        while (leftMostRightNode.left != null) {
            leftMostRightNode = leftMostRightNode.left;
        }
        leftMostRightNode.left = leftNode;
        return node.right;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.data == key) {
            return connector(root);
        }
        TreeNode current = root;
        while (current != null) {
            if (current.data > key) {
                if (current.left != null && current.left.data == key) {
                    current.left = connector(current.left);
                } else {
                    current = current.left;
                }
            } else {
                if (current.right != null && current.right.data == key) {
                    current.right = connector(current.right);
                } else {
                    current = current.right;
                }
            }
        }
        return current;
    }

    private void inOrder(TreeNode root, List<Integer> values) {
        if (root == null) return;
        inOrder(root.left,values);
        values.add(root.data);
        inOrder(root.right,values);
    }

    public List<Integer> kLargesSmall(TreeNode root, int k) {
        if (root == null) return null;
        List<Integer> values = new ArrayList<>();
        inOrder(root,values);
        Collections.sort(values);
        List<Integer> result = new ArrayList<>();
        result.add(values.get(k-1));
        result.add(values.get(values.size()-k));
        return result;
    }

    public boolean isBST(TreeNode root) {
        return validate(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean validate(TreeNode node,int min,int max) {
        if (node == null) return true;
        if (node.data <= min || node.data >= max) return false;
        boolean leftValidation = validate(node.left,min,node.data);
        boolean rightValidation = validate(node.right,node.data,max);
        return leftValidation && rightValidation;
    }

    public TreeNode lca(TreeNode root, int p, int q) {
        if (root == null) return null;
        if (p > root.data && q > root.data) {
            return lca(root.right,p,q);
        } else if (p < root.data && q < root.data) {
            return lca(root.left,p,q);
        } else {
            return root;
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return null;
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode root = new TreeNode(preorder[0]);
        List<Integer> smaller = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        int index = 1;
        while (index < preorder.length && preorder[index] < preorder[0]) {
            smaller.add(preorder[index]);
            index++;
        }
        while (index < preorder.length) {
            greater.add(preorder[index]);
            index++;
        }
        root.left = bstFromPreorder(smaller.stream().mapToInt(i -> i).toArray());
        root.right = bstFromPreorder(greater.stream().mapToInt(i -> i).toArray());
        return root;
    }

    private static int pre = -1;
    private static int suc = Integer.MAX_VALUE;

    static List<Integer> succPredBST(TreeNode root, int key) {
        fetchSucPre(root,key);
        return Arrays.asList(pre,suc == Integer.MAX_VALUE ? -1 : suc);
    }
    private static void fetchSucPre(TreeNode root, int key) {
        if (root == null) {
            return;
        }


        if (root.data < key) {
            pre = Math.max(root.data,pre);
            fetchSucPre(root.right,key);
        } else if (root.data > key) {
            suc = Math.min(root.data,suc);
            fetchSucPre(root.left,key);
        } else {
            if (root.left != null) {
                TreeNode temp = root.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                pre = temp.data;
            }
            if (root.right != null) {
                TreeNode temp = root.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                suc = temp.data;
            }
        }
    }


}
