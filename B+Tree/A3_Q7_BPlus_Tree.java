/**
 * Code used to implement the B+Tree bottom up approach required for
 * Assignment 3 Question 7
 * Re-used here for Assignment 3, to sort the data in the assignment 3
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @version  1.0
 * @since    1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class A3_Q7_BPlus_Tree{

    private static A3_Q7_BPlus_Leaf[] leafNodes;
    private static ArrayList<A3_Q7_BPlus_Node[]> BPNodes;

    private static double fillLimit;
    private static int twoM;
    private static int floor;
    public static A3_Q7_BPlus_Node root;

    private static int keyCompares = 0;

    /**
     * Reads the data file and sorts the data required to build the B+ tree
     * @param filepath the file containing the data
     * @return an sorted array of records
     */
    public static ArrayList<A3_Q7_DataModel> readFile(String filepath){
        File dataFile = new File(filepath);
        ArrayList<String> rawData = new ArrayList<>();
        ArrayList<A3_Q7_DataModel> processedData =  new ArrayList<>();
        try{
            //Read the raw data in
            Scanner scanner = new Scanner(dataFile);
            while(scanner.hasNextLine()) {
                rawData.add(scanner.nextLine().replaceAll(": ", ":"));
            }
            //Sort the raw data
            Collections.sort(rawData);
            for(String str:rawData){
                processedData.add(new A3_Q7_DataModel(str));
            }
            return processedData;
        } catch (FileNotFoundException ex){
            System.out.println("File could not be found...");
            return null;
        }
    }

    /**
     * The constructor for the B+ Tree
     * @param twoM the size of the nodes
     * @param userData the data used to build the tree
     * @param fillLimit the fill limit for the nodes
     */
    public A3_Q7_BPlus_Tree(int twoM, ArrayList<A3_Q7_DataModel> userData, double fillLimit){
        //Determine the B+ tree's parameters
        A3_Q7_BPlus_Tree.fillLimit = fillLimit;
        A3_Q7_BPlus_Tree.twoM = twoM;

        A3_Q7_BPlus_Leaf activeLeaf = new A3_Q7_BPlus_Leaf(twoM,fillLimit);

        for(A3_Q7_DataModel record:userData){
            activeLeaf = insertRecord(activeLeaf, record);
        }

        root = getRoot(activeLeaf);
    }

    /**
     * Inserts a new record into the tree during bottom up construction
     * @param activeLeaf the current leaf
     * @param record the record to insert
     * @return a pointer to the newly added leaf
     */
    public static A3_Q7_BPlus_Leaf insertRecord(A3_Q7_BPlus_Leaf activeLeaf, A3_Q7_DataModel record){
        if(!activeLeaf.checkNextKey()){
            activeLeaf.insertRecord(record);
        }
        else{
            A3_Q7_BPlus_Node temp = climbUp(activeLeaf, record);
            activeLeaf = (A3_Q7_BPlus_Leaf)trickleDown(temp, record);
        }
        return activeLeaf;
    }

    /**
     * Works in conjunction with climbUp() to build the B+ Tree from the bottom up
     * @param activeNode The currently active node to trickle down from
     * @param record The record which will be added at the end of the trickle down
     * @return the active node after the step down
     */
    private static A3_Q7_BPlus_Node trickleDown(A3_Q7_BPlus_Node activeNode, A3_Q7_DataModel record){
        if(floor>1){
            A3_Q7_BPlus_Node temp = new A3_Q7_BPlus_Node(twoM, fillLimit);
            temp.linkParent(activeNode);
            activeNode.linkChild(temp);
            temp.linkLeft();
            floor--;
            return trickleDown(temp, record);
        }
        else{
            A3_Q7_BPlus_Leaf temp = new A3_Q7_BPlus_Leaf(twoM, fillLimit);
            temp.insertRecord(record);
            temp.linkParent(activeNode);
            activeNode.linkChild(temp);
            temp.linkLeft();
            floor--;
            return temp;
        }
    }

    /**
     * Works in conjunction with trickleDown() to build the B+ Tree from the bottom up
     * @param activeNode the currently active node to climb up from
     * @param record the record to add once the climb down is complete
     * @return the active node after the step up
     */
    private static A3_Q7_BPlus_Node climbUp(A3_Q7_BPlus_Node activeNode, A3_Q7_DataModel record){
        if(activeNode.parent != null){
            if(!activeNode.parent.checkNextKey()){
                activeNode = activeNode.parent;
                activeNode.insertKey(record);
                floor++;
                return activeNode;
            }
            else{
                floor++;
                return climbUp(activeNode.parent, record);
            }
        }
        else { //(activeNode.parent == null)
            A3_Q7_BPlus_Node temp = new A3_Q7_BPlus_Node(twoM, fillLimit);
            activeNode.parent = temp;
            temp.insertKey(record);
            temp.linkChild(activeNode);
            floor++;
            return temp;
        }
    }

    /**
     * Climbs the tree until the root is found
     * @param activeNode starting point for the climb
     * @return the root of the B+ Tree
     */
    private static A3_Q7_BPlus_Node getRoot(A3_Q7_BPlus_Node activeNode){
        while(activeNode.parent!=null){
            activeNode = activeNode.parent;
        }
        return activeNode;
    }

    /**
     * Displays the B+ Tree node structure
     * @param activeNode from where to start displaying the tree
     */
    public static void displayBPlusTree(A3_Q7_BPlus_Node activeNode){
        if(activeNode.getClass()== A3_Q7_BPlus_Node.class){
            for(String key:activeNode.getKeys()){
                System.out.print(key + ": ");
            }
            if(activeNode.right!=null){
                System.out.print("<-----|| NEXT NODE ||----->");
                displayBPlusTree(activeNode.right);
            }
            else{
                System.out.print("\n");
                while(activeNode.left!=null){
                    activeNode = activeNode.left;
                }
                displayBPlusTree(activeNode.children[0]);
            }
        }
    }

    /**
     * Used to search for a record in the B+ Tree
     * @param key the search key, in this case the name associated to the record
     * @return the record if found, an empty record otherwise
     */
    public static A3_Q7_DataModel searchFor(String key){
        keyCompares=0;
        A3_Q7_BPlus_Node temp = findLeaf(key, root);
        if(temp!=null){
            A3_Q7_DataModel dataTemp = findRecord(key,(A3_Q7_BPlus_Leaf)temp);
            System.out.println("");
            System.out.println("Search For(" + key + ") found in (" + keyCompares + ") key compares");
            dataTemp.printRecord();
            System.out.println("====================================================================");
            return dataTemp;
        }
        else{
            return null;
        }
    }

    /**
     * Trickles down the tree until the leaf node that may contain the desired value is found.
     * @param key search key
     * @param currentNode the starting point
     * @return the leaf node which may contain the record
     */
    private static A3_Q7_BPlus_Node findLeaf(String key, A3_Q7_BPlus_Node currentNode){
        String[] keySet = currentNode.getKeys();
        if (currentNode.getClass() == A3_Q7_BPlus_Leaf.class){
            keyCompares+=1;
            return currentNode;
        }
        else if(key.compareTo(keySet[0]) < 0){
            keyCompares+=1;
            return findLeaf(key, currentNode.children[0]);
        }
        else{
            int j = 0;
            for(int i = 0; i < keySet.length; i++){
                if(keySet[i]!=null){
                    j++;
                }
            }
            for(int i = 0; i < j; i++){
                if(i==j-1){
                    if(key.compareTo(keySet[i]) >= 0){
                        keyCompares+=1;
                        return findLeaf(key, currentNode.children[i+1]);
                    }

                }
                else{
                    if (key.compareTo(keySet[i]) >= 0 && key.compareTo(keySet[i+1]) < 0){
                        keyCompares+=1;
                        return findLeaf(key, currentNode.children[i+1]);
                    }
                }
            }
        }
        keyCompares+=1;
        return null;
    }

    /**
     * Searches a leaf node for a specific record
     * @param key the key to the record to find, in this case the name associated to the record
     * @param leafNode the leaf node to search
     * @return the record if found, an null record otherwise
     */
    private static A3_Q7_DataModel findRecord(String key, A3_Q7_BPlus_Leaf leafNode){
        if(leafNode==null){
            return null;
        }
        else{
            A3_Q7_DataModel[] records = leafNode.getRecords();
            for(A3_Q7_DataModel record:records){
                if(record!=null){
                    keyCompares+=1;
                    if(record.getName().equals(key) && record!=null){
                        return record;
                    }
                }
            }
        }
        return new A3_Q7_DataModel();
    }

    public static void main(String args[]){
        ArrayList<A3_Q7_DataModel> userData = readFile("data.txt");

        A3_Q7_BPlus_Tree bPlusTree = new A3_Q7_BPlus_Tree(10, userData, 60);

        //displayBPlusTree(root);

        A3_Q7_DataModel temp = searchFor("Azevedo, Ana");

        temp = searchFor("Silva, Rui");

        temp = searchFor("Boussebough, Imane");

        temp = searchFor("Terracina, Giorgio");

        temp = searchFor("Lefebvre, Peter");

        temp = searchFor("Houghten, Sher");

        temp = searchFor("Revesz, Peter");
    }
}
