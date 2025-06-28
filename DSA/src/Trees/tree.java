package Trees;


import java.util.*;

public class tree {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);
        System.out.println(countNodes(tree));
        System.out.println(maxOfTree(tree));
        System.out.println(countLeaves(tree));
        System.out.println(countNonLeaves(tree));
        int count = 0;
        System.out.println(countFullNodes(tree, count));
        System.out.println(heightOfTree(tree));
        System.out.println("Level Order Traversal");
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree);
        levelOrderTraversal(queue);
        queue = new LinkedList<>();
        queue.add(tree);
        reverseLevelOrderTraversal(queue);
        queue = new LinkedList<>();
        queue.add(tree);
        leftViewOfTree(queue);
        queue = new LinkedList<>();
        queue.add(tree);
        rightViewOfTree(queue);
        System.out.println("ZigZag Traversal");
        Stack<Node> stack = new Stack<>();
        stack.add(tree);
        zigzagTraversal(stack);
        System.out.println("Level Order Successor");
        queue = new LinkedList<>();
        queue.add(tree);
        System.out.println((levelOrderSuccessor(queue,3)));
        System.out.println(binaryTreePathSum(tree,11));
        System.out.println("All path sum data");
        List<List<Integer>> globalList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        binaryTreePathSumData(tree,11,list,globalList);
        System.out.println("Sum from root to leaf");
        System.out.println(sumRootToLeafNode(tree,0));
    }

    private static int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        count = countNodes(node.left) + countNodes(node.right) + 1 + count;
        return count;
    }

    private static int maxOfTree(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        int leftTree = maxOfTree(node.left);
        int rightTree = maxOfTree(node.right);
        return Math.max(node.data, Math.max(leftTree, rightTree));
    }

    private static int countLeaves(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int leftCount = countLeaves(node.left);
        int rightCount = countLeaves(node.right);
        return leftCount + rightCount;
    }

    private static int countNonLeaves(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        int leftCount = countNonLeaves(node.left);
        int rightCount = countNonLeaves(node.right);
        return 1 + leftCount + rightCount;
    }

    private static int countFullNodes(Node node, int count) {
        if (node == null) {
            return 0;
        }
        if (node.left != null && node.right != null) {
            count++;
        }
        int l = count + countFullNodes(node.left, count);
        int r = count + countFullNodes(node.right, count);
        return count;
    }

    private static int heightOfTree(Node node) {
        if (node == null) {
            return 0;
        }
        int l = heightOfTree(node.left);
        int r = heightOfTree(node.right);
        return 1 + Math.max(l, r);
    }

    // Breadth First Traversal
    private static void levelOrderTraversal(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Node removedNode = queue.poll();
                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }
                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }
                System.out.print(removedNode.data);
            }
            System.out.println();
        }
    }

    // Reverse Level Order Traversal
    private static void reverseLevelOrderTraversal(Queue<Node> queue) {
        Stack<Node> stack = new Stack<>();
        while (!queue.isEmpty()) {
            Node removedNode = queue.poll();
            stack.push(removedNode);
            if (removedNode.right != null) {
                queue.add(removedNode.right);
            }
            if (removedNode.left != null) {
                queue.add(removedNode.left);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data + "->");
        }
    }

    // left view of tree
    private static void leftViewOfTree(Queue<Node> queue) {
        System.out.println();
        System.out.println("Left View of Tree:");
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Node removedNode = queue.poll();
                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }
                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }
                if (i == 0) {
                    System.out.print(removedNode.data + " ");
                }
            }
        }
    }

    // right view of tree
    private static void rightViewOfTree(Queue<Node> queue) {
        System.out.println();
        System.out.println("Right View of Tree:");
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Node removedNode = queue.poll();
                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }
                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }
                if (i == queueSize - 1) {
                    System.out.print(removedNode.data + " ");
                }
            }
        }
    }

    // zigzag traversal
    private static void zigzagTraversal(Stack<Node> queue) {
        boolean flag = true;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            Stack<Node> newStack = new Stack<>();
            for (int i = 0; i < queueSize; i++) {
                if (flag) {
                    Node removedNode = queue.pop();
                    if (removedNode.right != null) {
                        newStack.add(removedNode.right);
                    }
                    if (removedNode.left != null) {
                        newStack.add(removedNode.left);
                    }
                    System.out.print(removedNode.data + " ");
                } else {
                    Node removedNode = queue.pop();
                    if (removedNode.left != null) {
                        newStack.add(removedNode.left);
                    }
                    if (removedNode.right != null) {
                        newStack.add(removedNode.right);
                    }
                    System.out.print(removedNode.data + " ");
                }
            }
            queue = newStack;
            flag = !flag;
        }
    }
    // Level order successor in binary tree
    private static int levelOrderSuccessor(Queue<Node> queue,int element) {
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Node removedNode = queue.poll();
                if (removedNode.left != null) {
                    queue.add(removedNode.left);
                }
                if (removedNode.right != null) {
                    queue.add(removedNode.right);
                }
                if (removedNode.data == element) {
                    if (queue.peek() != null) return queue.peek().data;
                    else return -1;
                }
            }
        }
        return -1;
    }

    //Binary tree path sum
    private static boolean binaryTreePathSum(Node tree,int targetSum) {
        if (tree == null) return false;
        if (targetSum - tree.data == 0 && (tree.left == null && tree.right == null)) return true;
        boolean l = binaryTreePathSum(tree.left,targetSum-tree.data);
        boolean r = binaryTreePathSum(tree.right,targetSum-tree.data);
        if (l) return true;
        if (r) return true;
        return false;
    }

    //Binary tree path sum with literals and list
    private static void binaryTreePathSumData(Node tree, int targetSum, List<Integer> list,List<List<Integer>> globalList) {
        if (tree == null) return;
        else list.add(tree.data);
        if (targetSum - tree.data == 0 && (tree.left == null && tree.right == null)) {
            System.out.println(list);
        }
        binaryTreePathSumData(tree.left,targetSum-tree.data,list,globalList);
        binaryTreePathSumData(tree.right,targetSum-tree.data,list,globalList);
        if (!list.isEmpty()) {
            list.removeLast();
        }
    }

    //Sum root to leaf nodes
    private static int sumRootToLeafNode(Node tree,int number) {
        if (tree == null) return 0;
        number = number*10 + tree.data;
        if (tree.left == null && tree.right == null) {
            return number;
        }
        int l = sumRootToLeafNode(tree.left,number);
        int r = sumRootToLeafNode(tree.right,number);
        return l+r;
    }
}
