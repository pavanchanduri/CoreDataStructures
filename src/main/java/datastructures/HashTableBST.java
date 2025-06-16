package datastructures;

public class HashTableBST {

    // The array of nodes that stores the key-value pairs
    private final Node[] dataMap;

    // The inner Node class that represents a node in the binary search tree
    // of values stored at a particular index in the hash table
    static class Node {
        String key;
        int value;
        Node left;
        Node right;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // The constructor that initializes the dataMap array to
    // an array of Node objects with the default size
    public HashTableBST() {
        // The default size of the hash table
        int size = 7;
        dataMap = new Node[size];
    }

    public Node[] getDataMap() {
        return dataMap;
    }

    /**
     * This method sets a key-value pair in the hash table.
     * It calculates the hash index for the key, creates a new Node
     * for the key-value pair, and handles collisions using a binary search tree.
     * If the key already exists, it updates the value.
     * @param key
     * @param value
     */

    public void set(String key, int value) {
        // Calculate index using hash function.
        // This determines where to place the
        // key-value pair in the array.
        int index = hash(key);

        // Create a new Node to store key and value.
        // This node will be inserted into the hash table.
        Node newNode = new Node(key, value);

        // Check if slot at calculated index is empty.
        // This means no collision at this index.
        if (dataMap[index] == null) {
            // If slot is empty, place newNode here.
            // This is the case with no collision.
            dataMap[index] = newNode;
        } else {
            // If slot not empty, implies collision.
            // Handle collision by chaining.

            // Start with first node in list at index.
            Node temp = dataMap[index];

            // Traverse the tree to find matching key or
            // reach end of the tree.
            while (true) {
                if(temp.left == null && temp.key.compareTo(key) > 0) {
                    // If left child is null and current key is greater,
                    // insert newNode here.
                    temp.left = newNode;
                    return;
                } else if(temp.right == null && temp.key.compareTo(key) < 0) {
                    // If right child is null and current key is smaller,
                    // insert newNode here.
                    temp.right = newNode;
                    return;
                } else if(temp.key.equals(key)) {
                    // If current node's key matches new key,
                    // update value.
                    temp.value = value;
                    return;
                } else if(temp.key.compareTo(key) > 0) {
                    // If current key is greater, move to left child.
                    temp = temp.left;
                } else {
                    // If current key is smaller, move to right child.
                    temp = temp.right;
                }
            }
        }
    }

    
    /**
     * This method retrieves the value associated with a given key
     * from the hash table. It calculates the hash index for the key,
     * traverses the BST at that index, and returns the value
     * @param key
     * @return
     */
    public int get(String key) {
        // Calculate the hash index for the key to find
        // the correct bucket in the dataMap
        int index = hash(key);
        // Access the BST at the calculated bucket index
        Node temp = dataMap[index];
        // Iterate over the BST
        while (temp != null) {
            // Check if current node's key matches the search key
            if (temp.key.equals(key))
                return temp.value; // Return the value if keys match
            // Move to the next node in the BST
            if(temp.key.compareTo(key) > 0) {
                temp = temp.left; // Move to left child if search key is smaller
            } else {
                temp = temp.right; // Move to right child if search key is larger
            }
        }
        // Return 0 if key is not found in the map
        return 0;
    }

    /**
     * 1. Convert the key to char Array
     * 2. Hash = Hash+ascii*23
     * 3. Modulo with dataMap length so that the hash is between 0 and dataMap.length-1
     * @param key - Key for which the hash needs to be computed
     * @return hash value of the key
     */
    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            int asciiValue = keyChars[i];
            hash = (hash + asciiValue * 23) % dataMap.length;
        }
        return hash;
    }

    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {
            System.out.print("Index " + i + ": ");
            Node temp = dataMap[i];
            if (temp == null) {
                System.out.println("null");
            } else {
                // Print the BST at this index
                while (temp != null) {
                    System.out.print("[" + temp.key + ": " + temp.value + "] ");
                    // Traverse left first, then right
                    if (temp.left != null) {
                        temp = temp.left;
                    } else if (temp.right != null) {
                        temp = temp.right;
                    } else {
                        break; // Exit loop if no children
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();
        ht.set("aba", 1);
        ht.set("baa", 2);
        ht.set("aab", 2);
        ht.set("c", 3);
        ht.set("c", 33);
        ht.printTable();
        System.out.println(ht.get("c"));
    }
}
