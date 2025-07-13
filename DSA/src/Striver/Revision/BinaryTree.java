package Striver.Revision;

import Striver.BinaryTree.FAQs;
import Trees.tree;
import com.sun.source.tree.Tree;

import java.util.*;
import java.util.LinkedList;

public class BinaryTree {

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

    public static void main(String[] args) {

    }

    private void recursionInorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        recursionInorder(root.left,result);
        result.add(root.data);
        recursionInorder(root.right,result);
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursionInorder(root,result);
        return result;
    }

    private void recursionPreorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.data);
        recursionPreorder(root.left,result);
        recursionPreorder(root.right,result);
    }

    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursionPreorder(root,result);
        return result;
    }

    private void recursionPostorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        recursionPostorder(root.left,result);
        recursionPostorder(root.right,result);
        result.add(root.data);
    }

    public List<Integer> postOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursionPostorder(root,result);
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return result;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0;i<size;i++) {
                TreeNode polledData = queue.poll();
                level.add(polledData.data);
                if (polledData.left != null) queue.add(polledData.left);
                if (polledData.right != null) queue.add(polledData.right);
            }
            result.add(level);
        }
        return result;
    }

    public static class NodeState {
        TreeNode node;
        int state;
        NodeState(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    List<List<Integer>> treeTraversal(TreeNode root) {
        Stack<NodeState> stack = new Stack<>();
        List<Integer> inTraversal = new ArrayList<>();
        List<Integer> preTraversal = new ArrayList<>();
        List<Integer> postTraversal = new ArrayList<>();
        if (root == null) return List.of(inTraversal,preTraversal,postTraversal);
        stack.add(new NodeState(root,1));
        while (!stack.isEmpty()) {
            NodeState ns = stack.pop();
            TreeNode node = ns.node;
            int state = ns.state;
            if (state == 1) {
                preTraversal.add(node.data);
                stack.add(new NodeState(node,2));
                if (node.left != null) {
                    stack.add(new NodeState(node.left,1));
                }
            }
            else if (state == 2) {
                inTraversal.add(node.data);
                stack.add(new NodeState(node,3));
                if (node.right != null) {
                    stack.add(new NodeState(node.left,1));
                }
            } else {
                postTraversal.add(node.data);
            }
        }
        return List.of(inTraversal,preTraversal,postTraversal);
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
        if (p.data != q.data) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }
    private int dfsHeight(TreeNode node) {
        if (node == null) return 0;
        int left = dfsHeight(node.left);
        if (left == -1) return -1;
        int right = dfsHeight(node.right);
        if (right == -1) return -1;
        if (Math.abs(right-left) > 1) return -1;
        return 1 + Math.max(left,right);
    }
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left,root.right);
    }

    public boolean isMirror(TreeNode p,TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.data != q.data) return false;
        return isSameTree(p.left,q.right) && isSameTree(p.right,q.left);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        diameter[0] = Integer.MIN_VALUE;
        calculateDiameter(root,diameter);
        return diameter[0];
    }
    private int calculateDiameter(TreeNode root, int[] diameter) {
        if (root == null) return 0;
        int lh = calculateDiameter(root.left,diameter);
        int rh = calculateDiameter(root.right,diameter);
        diameter[0] = Math.max(diameter[0],lh + rh);
        return 1 + Math.max(lh,rh);
    }

    public int maxPathSum(TreeNode root) {
        int[] sum = {Integer.MIN_VALUE};
        calculateSum(root,sum);
        return sum[0];
    }
    private int calculateSum(TreeNode root, int[] sum) {
        if (root == null) return 0;
        int lSum = Math.max(0,calculateSum(root.left,sum));
        int rSum = Math.max(0,calculateSum(root.right,sum));
        sum[0] = Math.max(sum[0],lSum + rSum + root.data);
        return Math.max(lSum,rSum) + root.data;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        queue.add(root);
        boolean l2r = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(Collections.nCopies(size,0));
            for (int i=0;i<size;i++) {
                TreeNode polledNode = queue.poll();
                int polledData = polledNode.data;
                int index = l2r ? i : (size - 1 - i);
                level.set(index,polledData);
                if (polledNode.left != null) queue.add(polledNode.left);
                if (polledNode.right != null) queue.add(polledNode.right);
            }
            l2r = !l2r;
            result.add(level);
        }
        return result;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
    private void addLeftBoundary(TreeNode root, List<Integer> result) {
        TreeNode node = root.left;
        while (node != null) {
            if (!isLeaf(node)) {
                result.add(node.data);
            }
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }
    private void addRightBoundary(TreeNode root, List<Integer> result) {
        TreeNode node = root.right;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null) {
            if (!isLeaf(node)) {
                stack.push(node);
            }
            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop().data);
        }
    }

    private void addLeaf(TreeNode node, List<Integer> result) {
        if (isLeaf(node)) {
            result.add(node.data);
            return;
        }
        if (node.left != null) {
            addLeaf(node.left,result);
        }
        if (node.right != null) {
            addLeaf(node.right,result);
        }
    }
    public List<Integer> boundary(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        if (!isLeaf(root)) result.add(root.data);
        addLeftBoundary(root,result);
        addLeaf(root,result);
        addRightBoundary(root,result);
        return result;
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        int level = 0;
        int verticalLevel = 0;
        TreeMap<Integer,TreeMap<Integer,List<Integer>>> levelMap = new TreeMap<>();
        levelMap.put(level,new TreeMap<>(Map.of(verticalLevel,new ArrayList<>(List.of(root.data)))));
        maintainMap(root,level,verticalLevel,levelMap);
        levelMap.forEach((key,value) -> {
            List<Integer> resultValue = new ArrayList<>();
            value.forEach((keyInside,valueInside) -> {
                resultValue.addAll(valueInside);
            });
            result.add(resultValue);
        });
        return result;
    }

    private void maintainMap(TreeNode root, int level, int verticalLevel,TreeMap<Integer,TreeMap<Integer,List<Integer>>> levelMap) {
        if (root.left != null) {
            if (levelMap.containsKey(level - 1)) {
                TreeMap<Integer, List<Integer>> map = levelMap.get(level - 1);
                if (map.containsKey(verticalLevel + 1)) {
                    List<Integer> list = map.get(verticalLevel+1);
                    list.add(root.left.data);
                    Collections.sort(list);
                    map.put(verticalLevel+1,list);
                } else {
                    map.put(verticalLevel+1,new ArrayList<>(List.of(root.left.data)));
                }
                levelMap.put(level-1,map);
            } else {
                levelMap.put(level-1,new TreeMap<>(Map.of(verticalLevel+1,new ArrayList<>(List.of(root.left.data)))));
            }
            maintainMap(root.left,level-1,verticalLevel+1,levelMap);
        }
        if (root.right != null) {
            if (levelMap.containsKey(level + 1)) {
                TreeMap<Integer, List<Integer>> map = levelMap.get(level + 1);
                if (map.containsKey(verticalLevel + 1)) {
                    List<Integer> list = map.get(verticalLevel+1);
                    list.add(root.right.data);
                    Collections.sort(list);
                    map.put(verticalLevel+1,list);
                } else {
                    map.put(verticalLevel+1,new ArrayList<>(List.of(root.right.data)));
                }
                levelMap.put(level+1,map);
            } else {
                levelMap.put(level+1,new TreeMap<>(Map.of(verticalLevel+1,new ArrayList<>(List.of(root.right.data)))));
            }
            maintainMap(root.right,level+1,verticalLevel+1,levelMap);
        }
    }

    public List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        int level = 0;
        int verticalLevel = 0;
        TreeMap<Integer,TreeMap<Integer,List<Integer>>> levelMap = new TreeMap<>();
        levelMap.put(level,new TreeMap<>(Map.of(verticalLevel,new ArrayList<>(List.of(root.data)))));
        maintainMap(root,level,verticalLevel,levelMap);
        levelMap.forEach((key,value) -> {
            List<Integer> keySet = new ArrayList<>(value.keySet());
            result.add(value.get(keySet.get(0)).get(0));
        });
        return result;
    }

    public List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        int level = 0;
        int verticalLevel = 0;
        TreeMap<Integer,TreeMap<Integer,List<Integer>>> levelMap = new TreeMap<>();
        levelMap.put(level,new TreeMap<>(Map.of(verticalLevel,new ArrayList<>(List.of(root.data)))));
        maintainMap(root,level,verticalLevel,levelMap);
        levelMap.forEach((key,value) -> {
            List<Integer> keySet = new ArrayList<>(value.keySet());
            result.add(value.get(keySet.get(keySet.size()-1)).get(value.get(keySet.get(keySet.size()-1)).size()-1));
        });
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0;i<size;i++) {
                TreeNode polledNode = q.poll();
                level.add(polledNode.data);
                if (polledNode.left != null) q.add(polledNode.left);
                if (polledNode.right != null) q.add(polledNode.right);
            }
            result.add(level.get(size-1));
        }
        return result;
    }

    public List<List<Integer>> allRootToLeaf(TreeNode root) {
        // We can use DFS here to go path by path
        List<List<Integer>> allPath = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root,currentPath,allPath);
        return allPath;
    }
    private void dfs(TreeNode node, List<Integer> currentPath, List<List<Integer>> allPath) {
        if (node == null) return;
        currentPath.add(node.data);
        if (node.left == null && node.right == null) {
            allPath.add(currentPath);
        } else {
            dfs(node.left,currentPath,allPath);
            dfs(node.right,currentPath,allPath);
        }
        currentPath.remove(currentPath.size()-1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left == null) return right;
        else if (right == null) return left;
        else return root;
    }

    static class Pair<  K , V  > {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        // We will go level wise by BFS
        if (root == null) return 0;
        int result = 0;
        Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root,0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int firstIndex = 0;
            int lastIndex = 0;
            for (int i=0;i<size;i++) {
                Pair<TreeNode,Integer> currentValueMap = queue.poll();
                int currentValue = currentValueMap.getValue();
                if (i == 0) firstIndex = currentValue;
                if (i == size-1) lastIndex = currentValue;
                TreeNode node = currentValueMap.getKey();
                if (node.left != null) queue.add(new Pair<>(node.left,2*currentValue + 1));
                if (node.right != null) queue.add(new Pair<>(node.right,2*currentValue + 2));
            }
            result = Math.max(result, lastIndex-firstIndex+1);
        }
        return result;
    }

}

