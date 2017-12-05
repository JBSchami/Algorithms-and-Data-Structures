/**
 * Code used to implement the leaf nodes of the B+ tree required for
 * Assignment 3 Question 7, It is a extension of the key node
 * Re-used here for Assignment 3, to sort the data in the assignment 3
 * @author   Jonathan Bedard Schami <jbschami@gmail.com>
 * @version  1.0
 * @since    1.0
 */

public class A3_Q7_BPlus_Leaf extends A3_Q7_BPlus_Node{
    private A3_Q7_DataModel[] records;
    int nextRecord;


    /**
     * Constructor for Leaf Node
     * @param recordsPerLeaf the number of records contained in each leaf node
     * @param fillLimit the allowable fill percentage for the leaf node
     */
    public A3_Q7_BPlus_Leaf(int recordsPerLeaf, double fillLimit){
        records = new A3_Q7_DataModel[recordsPerLeaf];
        parent = null;
        left = null;
        right = null;

        this.fillLimit = fillLimit;
        nextRecord=0;
    }

    /**
     * Checks to make sure adding the next record does not exceed the fill limit
     * @return true if the next record busts the limit
     */
    public boolean checkNextKey(){
        return Math.ceil(((double)(nextRecord)/(double)records.length)*100) >= fillLimit;
    }

    /**
     * Inserts a new record into the leaf node
     * @param data
     */
    public void insertRecord(A3_Q7_DataModel data){
        records[nextRecord] = data;
        this.nextRecord++;
    }

    /**
     * Returns all the records in the leaf node
     * @return
     */
    public A3_Q7_DataModel[] getRecords() {
        return records;
    }
}


