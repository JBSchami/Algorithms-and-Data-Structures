/**
 * Code used to implement a suffix trie to allow for a phone book type
 * application for a COMP5511 Assignment
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @version  1.0
 * @since    1.0
 */


import java.util.ArrayList;

public class A4_Q5_Suffix_Trie {
    private static A4_Q5_Trie_Node root;

    /**
     * Constructor
     */
    public A4_Q5_Suffix_Trie(){
        root = new A4_Q5_Trie_Node();
    }

    /**
     * Adds a record into the trie. All possible search keys for the record are inlcuded into the trie
     * @param record the record to add to the trie
     */
    public void addRecord(A4_Q5_DataModel record){
        addWordToTrie(record.getFirstName(), record);
        addWordToTrie(record.getLastName(), record);
        addWordToTrie(record.getCountry(), record);
        addWordToTrie(record.getEmail(), record);
        addWordToTrie(record.getUniversity(), record);
    }

    /**
     * Adds all possible suffixes of a word to the trie
     * @param word the word to add
     * @param record the record associated to that word
     */
    private void addWordToTrie(String word, A4_Q5_DataModel record){

        ArrayList<String> suffixes = new ArrayList<>();
        suffixes = getSuffixes(word);
        for(String suffix:suffixes){
            addToTrie(root, suffix, record);
        }
    }

    /**
     * Returns all possible suffixes of a word
     * @param word the word to extract suffixes from
     * @return an array of the suffixes of the word
     */
    private ArrayList<String> getSuffixes(String word){
        ArrayList<String> suffixes = new ArrayList<>();
        for(int i = 0; i < word.length();i++){
            suffixes.add(word.substring(i));
        }
        return suffixes;
    }

    /**
     * Adds the missing nodes to the trie to represent the string being added.
     * Also adds a link from existing nodes to the new record
     * @param activeNode the active node to which to add the string
     * @param stringValue the string to add
     * @param record the record associated to the string
     */
    private void addToTrie(A4_Q5_Trie_Node activeNode,String stringValue, A4_Q5_DataModel record){
        char[] chars = stringValue.toCharArray();
        for(char character:chars){
            if(activeNode.findKeyInSubKeys(character) == null){
                A4_Q5_Trie_Node temp = new A4_Q5_Trie_Node(character);
                activeNode.addSubKey(temp);
                activeNode = activeNode.findKeyInSubKeys(character);
            }
            else{
                activeNode = activeNode.findKeyInSubKeys(character);
            }
            if(!activeNode.recordList.contains(record)){
                activeNode.addRecord(record);
            }
        }
    }

    /**
     * Searches for records, returns a list of records that contain a match with the substring
     * @param key the search key
     * @return an array of records that contain a substring which matches the search key
     */
    public ArrayList<A4_Q5_DataModel> searchForRecords(String key){
        A4_Q5_Trie_Node activeNode = root;
        char[] chars = key.toLowerCase().toCharArray();
        for(char character:chars){
            if(activeNode.findKeyInSubKeys(character) == null){
                return null;
            }
            else{
                activeNode = activeNode.findKeyInSubKeys(character);
            }
        }
        return activeNode.recordList;
    }
}
