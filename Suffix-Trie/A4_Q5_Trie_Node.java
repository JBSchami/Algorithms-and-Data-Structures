/**
 * Code used to implement the trie nodes required for the suffix tree
 * used in an assignment in COMP5511
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @version  1.0
 * @since    1.0
 */


import java.util.ArrayList;
import java.util.Comparator;

public class A4_Q5_Trie_Node {
    public Character key;
    public ArrayList<A4_Q5_Trie_Node> subKeys;
    public ArrayList<A4_Q5_DataModel> recordList;
    /**
     * Default constructor for blank node
     */
    public A4_Q5_Trie_Node(){
        key = null;
        subKeys = null;
    }

    /**
     * Constructor when key is provided
     * @param key the key to add
     */
    public A4_Q5_Trie_Node(Character key){
        this.key = key;
        this.subKeys = null;
        this.recordList = new ArrayList<>();
    }

    /**
     * Constructor when end of string is reached
     * @param key the last key
     * @param record the record associated to the trie path
     */
    public A4_Q5_Trie_Node(Character key, A4_Q5_DataModel record) {
        this.key = key;
        this.subKeys = null;
        this.recordList = null;

    }

    /**
     * Getter for the key in the node
     * @return the key of the node
     */
    public Character getKey() {
        return key;
    }

    /**
     * Adds a subkey to the node and sorts them to maintain proper ordering
     * @param subkey the subkey to add
     */
    public void addSubKey(A4_Q5_Trie_Node subkey){
        if(subKeys == null){
            subKeys = new ArrayList<>();
        }
        this.subKeys.add(subkey);
        this.subKeys.sort(Comparator.comparing(A4_Q5_Trie_Node::getKey));
    }

    /**
     * Adds a pointer to a record to the trie node with which said record is associated
     * @param record the record to add
     */
    public void addRecord(A4_Q5_DataModel record){
        if(recordList == null){
            recordList = new ArrayList<>();
        }
        recordList.add(record);
        this.recordList.sort(Comparator.comparing(A4_Q5_DataModel::getName));
    }

    /**
     * Binary search to find if a subkey exists in the node, used for first call
     * @param key the key to search for
     * @return the key if it is found, null otherwise
     */
    public A4_Q5_Trie_Node findKeyInSubKeys(Character key){
        if(this.subKeys==null){
            return null;
        }
        int start = 0;
        int end = subKeys.size()-1;
        int mid = (end-start)/2;

        if(end < start){
            return null;
        }

        if(key.compareTo(this.subKeys.get(mid).getKey()) < 0){
            return findKeyInSubKeys(key, start, mid);
        }
        else if(key.compareTo(this.subKeys.get(mid).getKey()) > 0){
            return findKeyInSubKeys(key, mid+1, end);
        }
        else {
            return this.subKeys.get(mid);
        }
    }

    /**
     * Binary search to find if a subkey exists in the node, used for recursive calls
     * @param key the search key
     * @param start the starting point for binary search
     * @param end the end point for binary search
     * @return
     */
    private A4_Q5_Trie_Node findKeyInSubKeys(Character key, int start, int end){
        int mid = start+(end-start)/2;

        if(end < start){
            return null;
        }

        if(key.compareTo(this.subKeys.get(mid).getKey()) < 0){
            return findKeyInSubKeys(key, start, mid-1);
        }
        else if(key.compareTo(this.subKeys.get(mid).getKey()) > 0){
            return findKeyInSubKeys(key, mid+1, end);
        }
        else {
            return this.subKeys.get(mid);
        }
    }
}
