package hard;

import java.util.*;

public class LRUCache {
	  
    class Node {
        public int key; 
        public int value; 
        Node prev; 
        Node next;
        public Node(int k, int val){
            key = k; 
            value = val; 
        }
    }
    Node dummyhead;
    Node dummytail; 
    Map<Integer, Node> map; 
    int maxSize; 
    int size; 
    
    // @param capacity, an integer
    public LRUCache(int capacity) {
        // write your code here
        maxSize = capacity; 
        size = 0; 
        dummyhead = new Node(-1, -1); 
        dummytail = new Node(-1, -1);
        dummyhead.next = dummytail; 
        dummytail.prev = dummyhead; 
        map = new HashMap<Integer, Node>(); 
    }
    
    // @return an integer
    public int get(int key) {
        // write your code here
        Node node = map.get(key);
        if (node==null) return -1; 
        node.prev.next = node.next; 
        node.next.prev = node.prev; 
        node.next = dummytail; 
        node.prev = dummytail.prev; 
        dummytail.prev.next = node; 
        dummytail.prev = node; 
        return node.value; 
    }
    
    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // write your code here
        Node node = map.get(key); 
        if (node!=null){
            node.value = value; 
            node.prev.next = node.next; 
            node.next.prev = node.prev; 
            node.next = dummytail; 
            node.prev = dummytail.prev; 
            dummytail.prev.next = node; 
            dummytail.prev = node; 
        }
        else {
            node = new Node(key, value);
            node.next = dummytail; 
            node.prev = dummytail.prev; 
            dummytail.prev.next = node; 
            dummytail.prev = node; 
            map.put(key, node); 
            if (++size>maxSize){
                Node del = dummyhead.next; 
                dummyhead.next = del.next; 
                del.next.prev = dummyhead;
                map.remove(del.key); 
                size--; 
            }
        }
    }
}
