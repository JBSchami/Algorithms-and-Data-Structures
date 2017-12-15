/*
COMP5511, Desai, Assignment 3 - Question 7
avirup CHATTAPADAY, 26451640, av_chatt@encs.concordia.ca
brandon HANDFIELD, 40070779, b_handfi@encs.concordia.ca
jonathan BEDARD SCHAMI, 40050610, j_edars@encs.concordia.ca
*/

/**
 * Code used to implement the key nodes of B+Tree required for
 * Assignment 3 Question 7
 * Re-used here for Assignment 3, to sort the data in the assignment 3
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @author   Brandon Handfield <b_handfi@encs.concordia.ca>
 * @author   Avirup Chattapadday <av_chatt@encs.concordia.ca>
 * @version  1.0
 * @since    1.0
 */

public class A3_Q7_BPlus_Node {
    protected A3_Q7_BPlus_Node parent;
    protected A3_Q7_BPlus_Node[] children;
    protected A3_Q7_BPlus_Node left;
    protected A3_Q7_BPlus_Node right;
    private String[] keys;

    protected double fillLimit;
    private int nextPointer;
    private int nextKey;

    /**
     * Default constructor for a null node
     */
    public A3_Q7_BPlus_Node(){
        keys = null;
        parent = null;
        children = null;
        left = null;
        right = null;
        fillLimit = 100;
        nextPointer=0;
    }

    /**
     * Constructor for Node
     * @param keysPerNode the number of keys each node holds
     * @param fillLimit the fill limit for the the node
     */
    public A3_Q7_BPlus_Node(int keysPerNode, double fillLimit){
        keys = new String[keysPerNode-1];
        parent = null;
        children = new A3_Q7_BPlus_Node[keysPerNode];
        left = null;
        right = null;

        this.fillLimit = fillLimit;
        nextPointer=0;
        nextKey=0;
    }

    /**
     * Checks if adding the next key exceeds the fill limit
     * @return true if the next key busts the fill limit
     */
    public boolean checkNextKey(){
        return Math.ceil(((double)(nextKey+1)/(double)keys.length)*100) >= this.fillLimit;
    }

    /**
     * Inserts a new key into the node
     * @param data the record from which to extract the key
     */
    public void insertKey(A3_Q7_DataModel data){
        keys[nextKey] = data.getName();
        this.nextKey++;
    }

    /**
     * Creates a link to a new child node
     * @param child the new child node
     */
    public void linkChild(A3_Q7_BPlus_Node child){
        children[nextPointer] = child;
        this.nextPointer++;
    }

    /**
     * Creates a link to the parent node of this node
     * @param parent the parent node of this node
     */
    public void linkParent(A3_Q7_BPlus_Node parent){
        this.parent = parent;
    }

    /**
     * Creates a link to the node directly to the left of this node, and a link from the left node to this node
     */
    public void linkLeft(){

        if(this.parent.nextPointer-2 > -1){
            A3_Q7_BPlus_Node temp = this.parent;
            temp = temp.children[temp.nextPointer-2];
            this.left = temp;
            temp.right = this;
        }
        else if(this.parent.nextPointer-2 <= -1 && this.parent.left!=null){
            A3_Q7_BPlus_Node temp = this.parent;
            temp = temp.left;
            temp = temp.children[temp.nextPointer-1];
            this.left = temp;
            temp.right = this;
        }

    }

    /**
     * Returns the key set for the node
     * @return key set
     */
    public String[] getKeys() {
        return keys;
    }
}
