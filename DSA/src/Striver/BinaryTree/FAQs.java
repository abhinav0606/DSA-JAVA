package Striver.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;

public class FAQs {
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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return null;
        }
        queue.add(root);
        boolean l2r = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>(Collections.nCopies(size, 0));
            for (int i=0;i<size;i++) {
                int index = l2r ? i : (size-1-i);
                TreeNode poll = queue.poll();
                level.set(index,poll.data);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            l2r = !l2r;
            result.add(level);
        }
        return result;
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    private void addLeftBoundary(TreeNode root, List<Integer> result) {
        TreeNode cur = root.left;
        while (cur != null) {
            if (!isLeaf(cur)) {
                result.add(cur.data);
            }
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
    }


    private void addRightBoundary(TreeNode root, List<Integer> result) {
        TreeNode cur = root.right;
        Stack<Integer> temp = new Stack<>();
        while (cur != null) {
            if (!isLeaf(cur)) {
                temp.add(cur.data);
            }
            if (cur.right != null) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        while (!temp.isEmpty()) {
            result.add(temp.pop());
        }
    }

    private void addLeaf(TreeNode root,List<Integer> result){
        if (isLeaf(root)) {
            result.add(root.data);
            return;
        }
        if (root.left != null) {
            addLeaf(root.left,result);
        }
        if (root.right != null) {
            addLeaf(root.right,result);
        }
    }

    public List<Integer> boundary(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (!isLeaf(root)) result.add(root.data);
        addLeftBoundary(root,result);
        addLeaf(root,result);
        addRightBoundary(root,result);
        return result;
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer,Map<Integer,List<Integer>>> levelMap = new HashMap<>();
        if (root == null) {
            return new ArrayList<>();
        }
        levelMap.put(0,Map.of(0,new ArrayList<>(List.of(root.data))));
        if (root.left != null) {
            maintainLevel(root.left,levelMap,-1,1);
        }
        if (root.right != null) {
            maintainLevel(root.right,levelMap,1,1);
        }
        levelMap = new TreeMap<>(levelMap);
        List<List<Integer>> result = new ArrayList<>();
        levelMap.forEach((key, value) -> {
            Map<Integer,List<Integer>> map = new TreeMap<>(value);
            List<Integer> internalList = new ArrayList<>();
            map.forEach((keyInternal,valueInternal) -> {
                internalList.add(valueInternal.get(0));
            });
            result.add(internalList);
        });
        return result;
    }
    private void maintainLevel(TreeNode root,Map<Integer,Map<Integer,List<Integer>>> levelMap, int verticalLevel, int horizontalLevel) {
        if (root == null) return;
        if (levelMap.containsKey(verticalLevel)) {
            Map<Integer,List<Integer>> mapList = levelMap.get(verticalLevel);
            if (mapList.containsKey(horizontalLevel)) {
                List<Integer> list = mapList.get(horizontalLevel);
                list.add(root.data);
                mapList.put(horizontalLevel,list);
            } else {
                Map<Integer, List<Integer>> newMap = new HashMap<>();
                newMap.put(horizontalLevel, new ArrayList<>(List.of(root.data)));
                levelMap.put(verticalLevel, newMap);
            }
            levelMap.put(verticalLevel,mapList);
        } else {
            levelMap.put(verticalLevel,Map.of(horizontalLevel,new ArrayList<>(List.of(root.data))));
        }
        maintainLevel(root.left,levelMap,verticalLevel-1,horizontalLevel+1);
        maintainLevel(root.right,levelMap,verticalLevel+1,horizontalLevel+1);
    }

    public List<Integer> topView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<Map<TreeNode,Integer>> queue = new LinkedList<>();
        Map<Integer,Integer> mpp = new HashMap<>();
        Map<TreeNode,Integer> map = new HashMap<>();
        map.put(root,0);
        queue.add(map);
        while (!queue.isEmpty()) {
            Map<TreeNode,Integer> internalMap = queue.poll();
            TreeNode treeNode = internalMap.keySet().stream().toList().get(0);
            int level = internalMap.get(treeNode);
            if (!mpp.containsKey(level)) {
                mpp.put(level,treeNode.data);
            }
            if (treeNode.left != null) {
                Map<TreeNode,Integer> leftTreeNode = new HashMap<>();
                leftTreeNode.put(root,level-1);
                queue.add(leftTreeNode);
            }
            if (treeNode.left != null) {
                Map<TreeNode,Integer> rightTreeNode = new HashMap<>();
                rightTreeNode.put(root,level-1);
                queue.add(rightTreeNode);
            }
        }
        return new ArrayList<>(mpp.values());
    }


    public List<Integer> topView2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        Map<Integer,Integer> mpp = new TreeMap<>();
        queue.add(new Pair<>(root,0));
        while (!queue.isEmpty()) {
            Pair<TreeNode,Integer> internalMap = queue.poll();
            TreeNode treeNode = internalMap.getKey();
            int level = internalMap.getValue();
            if (!mpp.containsKey(level)) {
                mpp.put(level,treeNode.data);
            }
            if (treeNode.left != null) {
                queue.add(new Pair<> (treeNode.left,level-1));
            }
            if (treeNode.right != null) {
                queue.add(new Pair<> (treeNode.right,level+1));
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Integer value : mpp.values()) {
            result.add(value);
        }
        return result;
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
    public List<Integer> bottomView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        Map<Integer,Integer> mpp = new TreeMap<>();
        queue.add(new Pair<>(root,0));
        while (!queue.isEmpty()) {
            Pair<TreeNode,Integer> internalMap = queue.poll();
            TreeNode treeNode = internalMap.getKey();
            int level = internalMap.getValue();
            mpp.put(level,treeNode.data);
            if (treeNode.left != null) {
                queue.add(new Pair<> (treeNode.left,level-1));
            }
            if (treeNode.right != null) {
                queue.add(new Pair<> (treeNode.right,level+1));
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Integer value : mpp.values()) {
            result.add(value);
        }
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
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
            result.add(level.get(level.size()-1));
        }
        return result;
    }

    public List<List<Integer>> allRootToLeaf(TreeNode root) {
        List<List<Integer>> allPath = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root,allPath,currentPath);
        return allPath;
    }
    private void dfs(TreeNode node,List<List<Integer>> allPath,List<Integer> currentPath) {
        if (node == null) return;
        currentPath.add(node.data);
        if (node.left == null && node.right == null) {
            allPath.add(currentPath);
        } else {
            dfs(node.left,allPath,currentPath);
            dfs(node.right,allPath,currentPath);
        }
        // remove last node for bakctracking
        currentPath.remove(currentPath.size()-1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left == null) return right;
        else if (right == null) return left;
        else {
            // This means we got the ancestor
            return root;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        Queue<Pair<TreeNode,Integer>> queuePair = new LinkedList<>();
        queuePair.add(new Pair<>(root,0));
        while (!queuePair.isEmpty()) {
            int size = queuePair.size();
            int firstIndex = 0;
            int lastIndex = 0;
            for (int i=0;i<size;i++) {
                int currentValue = queuePair.peek().getValue();
                if (i==0) firstIndex = currentValue;
                if (i==size-1) lastIndex=currentValue;
                TreeNode node = queuePair.peek().getKey();
                queuePair.poll();
                if (node.left != null) {
                    queuePair.add(new Pair<>(node.left,currentValue*2 + 1));
                }
                if (node.right != null) {
                    queuePair.add(new Pair<>(node.right,currentValue*2 + 2));
                }
            }
            ans = Math.max(ans, lastIndex-firstIndex+1);
        }
        return ans;
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Creating parent mapping
        if (root == null) return null;
        Map<TreeNode,TreeNode> parentMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tn = queue.poll();
            if (tn.left != null) {
                parentMap.put(tn.left,tn);
                queue.add(tn.left);
            }
            if (tn.right != null) {
                parentMap.put(tn.right,tn);
                queue.add(tn.right);
            }
        }
        List<Integer> result = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(target);
        visited.add(target);
        int distance = 0;
        while (!queue.isEmpty()) {
            if (distance == k) {
                while (!queue.isEmpty()) {
                    result.add(queue.poll().data);
                }
                return result;
            }
            int size = queue.size();
            for (int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.add(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.add(node.right);
                }
                if (parentMap.containsKey(node) && !visited.contains(parentMap.get(node))) {
                    visited.add(parentMap.get(node));
                    queue.add(parentMap.get(node));
                }
            }
            distance++;
        }
        return result;
    }

    public int timeToBurnTree(TreeNode root, int start) {
        if (root == null) return 0;
        Map<TreeNode,TreeNode> parentMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode result = new TreeNode(-1);
        while (!queue.isEmpty()) {
            TreeNode tn = queue.poll();
            if (tn.data == start) {
                result = tn;
            }
            if (tn.left != null) {
                parentMap.put(tn.left,tn);
                queue.add(tn.left);
            }
            if (tn.right != null) {
                parentMap.put(tn.right,tn);
                queue.add(tn.right);
            }
        }


        Map<TreeNode,Integer> visited = new HashMap<>();
        queue.add(result);
        visited.put(result,1);
        int maximum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int flag = 0;
            for (int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                if (node.left != null && visited.get(node.left) == null) {
                    flag = 1;
                    visited.put(node.left,1);
                    queue.add(node.left);
                }
                if (node.right != null && visited.get(node.right) == null) {
                    flag = 1;
                    visited.put(node.right,1);
                    queue.add(node.right);
                }
                if (parentMap.containsKey(node) && visited.get(parentMap.get(node)) == null) {
                    flag = 1;
                    visited.put(parentMap.get(node),1);
                    queue.add(parentMap.get(node));
                }
            }
            if (flag == 1) maximum++;
        }
        return maximum;
    }

    public int countNodesInorderWay(TreeNode root) {
        if (root == null) return 0;
        int[] count = new int[1];
        count[0] = 0;
        inorderWay(root,count);
        return count[0];
    }
    private void inorderWay(TreeNode node,int[] count) {
        if (node == null) return;
        count[0]++;
        inorderWay(node.left,count);
        inorderWay(node.right,count);
    }

    public int countNodesOptimized(TreeNode root) {
        if (root == null) return 0;
        int lh = leftHeight(root);
        int rh = rightHeight(root);
        if (lh == rh) {
            return (1 << lh) - 1;
        }
        return 1 + countNodesOptimized(root.left) + countNodesOptimized(root.right);
     }

     private int leftHeight(TreeNode root) {
        if (root == null) return 0;
        int h = 0;
        while (root != null) {
            h++;
            root = root.left;
        }
        return h;
     }
    private int rightHeight(TreeNode root) {
        if (root == null) return 0;
        int h = 0;
        while (root != null) {
            h++;
            root = root.right;
        }
        return h;
    }

}
