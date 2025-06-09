package datastructures;

import java.util.ArrayList;

public class HashTable {

    // The array of nodes that stores the key-value pairs
    private final Node[] dataMap;

    // The inner Node class that represents a node in the linked list
    // of values stored at a particular index in the hash table
    static class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // The constructor that initializes the dataMap array to
    // an array of Node objects with the default size
    public HashTable() {
        // The default size of the hash table
        int size = 7;
        dataMap = new Node[size];
    }

    public Node[] getDataMap() {
        return dataMap;
    }

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

            // Check if first node's key matches new key.
            // This is for updating value if key exists.
            if (temp.key.equals(key)) {
                // If keys match, update/overwrite node's value.
                temp.value = value;
                //temp.value += value;
                // Exit method, as value for existing key
                // is updated.
                return;
            }

            // Traverse list to find matching key or
            // reach end of list.
            while (temp.next != null) {
                // Move to next node in list.
                temp = temp.next;
                // Check if current node's key matches.
                if (temp.key.equals(key)) {
                    // If keys match, update node's value.
                    temp.value = value;
                    // Exit method, as value for existing key
                    // is updated.
                    return;
                }
            }

            // If key not found in list, append new node
            // at end to handle collision.
            temp.next = newNode;
        }
    }

    public void printTable() {
        for(int i = 0; i < dataMap.length; i++) {
            System.out.print(i + ":");
            if(dataMap[i] != null) {
                Node temp = dataMap[i];
                while (temp.next != null) {
                    System.out.print(" {" + temp.key + ", " + temp.value + "} -> ");
                    temp = temp.next;
                }
                System.out.println(" {" + temp.key + ", " + temp.value + "}");
                System.out.println();
            }
            System.out.println();
        }
    }

    public int get(String key) {
        // Calculate the hash index for the key to find
        // the correct bucket in the dataMap
        int index = hash(key);
        // Access the linked list at the calculated bucket index
        Node temp = dataMap[index];
        // Iterate over the linked list
        while (temp != null) {
            // Check if current node's key matches the search key
            if (temp.key.equals(key))
                return temp.value; // Return the value if keys match
            // Move to the next node in the list
            temp = temp.next;
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

    public ArrayList keys() {
        // Create a new ArrayList to hold the keys
        ArrayList<String> allKeys = new ArrayList<>();

        // Loop through all the indices in the dataMap array
        for (int i = 0; i < dataMap.length; i++) {
            // Get the head node of the linked list at the current index
            Node temp = dataMap[i];

            // Loop through the linked list at the current index
            while (temp != null) {
                // Add the key of the current node to the allKeys ArrayList
                allKeys.add(temp.key);

                // Move to the next node in the linked list
                temp = temp.next;
            }
        }

        // Return the ArrayList containing all the keys
        return allKeys;
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();
        ht.set("a", 1);
        ht.set("b", 2);
        ht.set("c", 3);
        ht.set("c", 33);
        ht.printTable();
        System.out.println(ht.get("c"));
    }
}
