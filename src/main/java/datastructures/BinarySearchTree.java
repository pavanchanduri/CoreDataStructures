package datastructures;

public class BinarySearchTree {

    Node root;

    class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public void insert(int value) {
        // Create a new node with the given value
        Node newNode = new Node(value);

        // If the tree is empty...
        if (root == null) {
            // Set the root to be the new node
            root = newNode;
            // Return true to indicate that the insertion was successful
            return;
        }

        // Set temp to be the root node
        Node temp = root;

        // Loop until the new node is successfully inserted or determined to be a duplicate
        while (true) {
            // If the new node's value is equal to the current node's value, return false to indicate that the insertion failed
            if (newNode.value == temp.value)
                return;

            // If the new node's value is less than the current node's value...
            if (newNode.value < temp.value) {
                // If the current node does not have a left child...
                if (temp.left == null) {
                    // Set the left child of the current node to be the new node
                    temp.left = newNode;
                    // Return true to indicate that the insertion was successful
                    return;
                }
                // Otherwise, set temp to be the left child of the current node and continue looping
                temp = temp.left;
            } else {
                // If the current node does not have a right child...
                if (temp.right == null) {
                    // Set the right child of the current node to be the new node
                    temp.right = newNode;
                    // Return true to indicate that the insertion was successful
                    return;
                }
                // Otherwise, set temp to be the right child of the current node and continue looping
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        // If the tree is empty, return false
        if (root == null) {
            return false;
        }

        // Start at the root node
        Node temp = root;

        // Loop until we find a node with the given value or exhaust the search space
        while (temp != null) {
            if (value < temp.value) {
                // If the given value is less than the current node's value, search the left subtree
                temp = temp.left;
            } else if (value > temp.value) {
                // If the given value is greater than the current node's value, search the right subtree
                temp = temp.right;
            } else {
                // If the given value is equal to the current node's value, we've found the node, so return true
                return true;
            }
        }

        // If we've exhausted the search space without finding the value, return false
        return false;
    }

    public int minValue(Node currentNode) {
        // Traverse left subtree until null is encountered
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        // Return the value of the leftmost node
        return currentNode.value;
    }

    public int maxValue(Node currentNode) {
        // Traverse left subtree until null is encountered
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        // Return the value of the leftmost node
        return currentNode.value;
    }

    public boolean rContains(int value) {
        return rContains(root, value);
    }

    private boolean rContains(Node currentNode, int value) {
        // If node is null, return false
        if (currentNode == null) return false;

        // If current node's value equals search value, return true
        if (currentNode.value == value) return true;

        // If search value is less than node's value,
        // search in the left subtree
        if (value < currentNode.value) {
            return rContains(currentNode.left, value);
        } else {
            // If search value is more than node's value,
            // search in the right subtree
            return rContains(currentNode.right, value);
        }
    }

    public Node rInsert(int value) {
        return rInsert(root, value);
    }

    private Node rInsert(Node currentNode, int value) {
        // If the node is null, create a new node with the value
        if (currentNode == null) return new Node(value);

        // If the value is less than current node's value,
        // try to insert it in the left subtree
        if (value < currentNode.value) {
            currentNode.left = rInsert(currentNode.left, value);
        }
        // If the value is greater than current node's value,
        // try to insert it in the right subtree
        else if (value > currentNode.value) {
            currentNode.right = rInsert(currentNode.right, value);
        }
        // Return the current node after insertion
        return currentNode;
    }

    public int deleteNode(int value) {
        Node newHead = deleteNode(root, value);
        return newHead.value;
    }

    private Node deleteNode(Node root, int value) {
        if(root==null) return null; //when the tree is empty
        if(root.value == value) {
            return helper(root);
        }
        Node currentNode = root;
        while(root!=null) {
            if(root.value>value) {
                if(root.left!=null && root.left.value == value) {
                    root.left = helper(root.left);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                if(root.right!=null && root.right.value == value) {
                    root.right = helper(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return currentNode;
    }

    private Node helper(Node node) {
        if(node.left == null) {
            return node.right;
        }
        if(node.right == null) {
            return node.left;
        }
        Node rightChild = node.right;
        Node lastRight = findLastRightNode(node.left);
        lastRight.right = rightChild;
        return node.left; //This would be the new root
    }

    private Node findLastRightNode(Node node) {
        if(node.right==null) return node;
        return findLastRightNode(node.right);
    }

    public void rDeleteNode(int value) {
        rDeleteNode(root, value);
    }

    private Node rDeleteNode(Node node, int value) {
        if(node==null) return null;

        if(value<node.value) {
            node.left = rDeleteNode(node.left, value);
        } else if(value>node.value) {
            node.right = rDeleteNode(node.right, value);
        } else {
            if(node.left==null && node.right==null) {
                node=null;
            } else if(node.left==null) {
                node = node.right;
            } else if(node.right==null) {
                node=node.left;
            } else {
                int subTreeMin = minValue(node.right);
                node.value = subTreeMin;
                node.right = rDeleteNode(node.right, subTreeMin);
            }
        }
        return node;
    }

    public void inorderTraversal(Node node) {
        if(node==null) return;
        inorderTraversal(node.left);
        System.out.print(node.value+" ");
        inorderTraversal(node.right);
    }

    public void preOrderTraversal(Node node) {
        if(node==null) return;
        System.out.print(node.value+" ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void postOrderTraversal(Node node) {
        if(node==null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.value+" ");
    }

    public int findHeight(Node root) {
        if(root==null) return -1;
        return Math.max(findHeight(root.left), findHeight(root.right)) + 1;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(47);
        bst.insert(21);
        bst.insert(76);
        bst.insert(18);
        bst.insert(52);
        bst.insert(82);
        bst.insert(42);
        bst.insert(27);
        bst.rInsert(17);
        System.out.println("Inorder Traversal of the Tree: ");
        bst.inorderTraversal(bst.root);
        System.out.println("\nPreorder Traversal of the Tree: ");
        bst.preOrderTraversal(bst.root);
        System.out.println("\nPostorder Traversal of the Tree: ");
        bst.postOrderTraversal(bst.root);
        System.out.println();
        System.out.println("Height of the tree: "+bst.findHeight(bst.root));
        System.out.println(bst.contains(27));
        System.out.println(bst.rContains(42));
        System.out.println(bst.root.value);
        System.out.println(bst.minValue(bst.root));
        System.out.println(bst.maxValue(bst.root));
        System.out.println(bst.deleteNode(47));
        bst.rDeleteNode(17);
        System.out.println(bst.contains(17));
    }
}
