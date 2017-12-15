/*
COMP5511, Desai, Assignment 3 - Question 7
avirup CHATTAPADAY, 26451640, av_chatt@encs.concordia.ca
brandon HANDFIELD, 40070779, b_handfi@encs.concordia.ca
jonathan BEDARD SCHAMI, 40050610, j_edars@encs.concordia.ca
*/

/**
 * Code used to implement the B+Tree bottom up approach required for
 * Assignment 3 Question 7
 * Re-used here for Assignment 3, to sort the data in the assignment 3
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @author   Brandon Handfield <b_handfi@encs.concordia.ca>
 * @author   Avirup Chattapadday <av_chatt@encs.concordia.ca>
 * @version  1.0
 * @since    1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class A3_Q7_BPlus_Tree_V2{

    private static A3_Q7_BPlus_Node root;
    private static double fillLimit;
    private static int twoM;

    private static int[] nodesPerDepth;
    private static int[] skipIntervalAtDepth;
    private static int[] nodesAtDepth;

    private static int nodeCNT=0;
    private static int keyCompares = 0;

    private static double oneLessRecord;

    private static int treeHeight;
    private static int floor;

    /**
     * Constructor
     * @param twoM the 2m tree parameter
     * @param userData the data used for the BPlus Tree
     * @param fillLimit the fill limit for the nodes
     */
    public A3_Q7_BPlus_Tree_V2(int twoM, ArrayList<A3_Q7_DataModel> userData, double fillLimit){
        //Set and Determine parameters of the B+ tree
        A3_Q7_BPlus_Tree_V2.fillLimit = fillLimit;
        A3_Q7_BPlus_Tree_V2.twoM = twoM;
        A3_Q7_BPlus_Tree_V2.treeHeight = numberOfLevels(userData.size(), twoM, fillLimit);
        A3_Q7_BPlus_Tree_V2.nodesPerDepth = nodesPerDepth(userData.size(), twoM, fillLimit);
        A3_Q7_BPlus_Tree_V2.nodesAtDepth = new int[nodesPerDepth.length];
        A3_Q7_BPlus_Tree_V2.skipIntervalAtDepth = skipPerDepth(userData.size(), nodesPerDepth, twoM, fillLimit);
        A3_Q7_BPlus_Tree_V2.oneLessRecord = (1/twoM)*100;

        A3_Q7_BPlus_Leaf activeLeaf = new A3_Q7_BPlus_Leaf(twoM,fillLimit);
        nodesAtDepth[0]++;

        for(A3_Q7_DataModel record:userData){
            activeLeaf = insertRecord(activeLeaf, record);
        }
        root = getRoot(activeLeaf);
    }

    /**
     * Reads the file and returns the data as objects
     * @param filepath the filepath for the data file
     * @return an array of records
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
                processedData.add(new A3_Q7_DataModel(str, 2));
            }
            return processedData;
        } catch (FileNotFoundException ex){
            System.out.println("File could not be found...");
            return null;
        }
    }

    /**
     * Determines the depth of the tree
     * @param dataSize number of records
     * @param twoM 2m tree parameter
     * @param fillLimit fill limit for nodes
     * @return the depth of the tree
     */
    public static int numberOfLevels(int dataSize, int twoM, double fillLimit){
        int counter = 0;
        double temp = (double)dataSize;
        double divider = (double)twoM*fillLimit/100;
        while(temp > 1){
            temp = temp/divider;
            counter+=1;
        }
        return counter;
    }

    /**
     * Number of nodes needed at each depth
     * @param dataSize the number of records
     * @param twoM the 2m paramter of the tree
     * @param fillLimit the fill limit for the nodes
     * @return An array with the number of nodes at each depth
     */
    public static int[] nodesPerDepth(int dataSize, int twoM, double fillLimit){
        int levels = numberOfLevels(dataSize, twoM, fillLimit);
        int[] floorSize = new int[levels];
        double temp = (double)dataSize;
        double divider = (double)twoM*fillLimit/100;
        for(int i = 0; i < levels; i++){
            temp = (int)Math.ceil(temp/divider);
            floorSize[i] = (int)temp;
        }
        return floorSize;
    }

    /**
     * Determines at what interval a less full node needs to be created so that the last node is at least half full
     * @param dataSize the number of records
     * @param nodesPerDepth the number of nodes at each depth
     * @param twoM the 2m tree parameter
     * @param fillLimit the node fill limit
     * @return An array with the node interval at each depth for less full nodes
     */
    public static int[] skipPerDepth(int dataSize, int[] nodesPerDepth, int twoM, double fillLimit){
        double min = Math.floor((double)twoM/2);
        double maxFill = Math.floor((double)twoM*fillLimit/100);
        int[] skipRate = new int[nodesPerDepth.length];
        skipRate[0] = (int)Math.floor(((double)nodesPerDepth[0])/(min-((double)dataSize-((double)nodesPerDepth[0]-1)*maxFill)));//minimum not maxfill
        for(int i = 1; i < skipRate.length; i++){
            skipRate[i] = (int)Math.floor(((double)nodesPerDepth[i])/(min-(((double)nodesPerDepth[i-1]-1)-((double)nodesPerDepth[i]-1)*maxFill)));
        }
        return skipRate;
    }

    /**
     * Inserts a record into the B+ tree from the bottom up
     * @param activeLeaf the current active node
     * @param record the record to add
     * @return the newly active node
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
     * Creates a node while moving up the tree
     * @param activeNode the active node
     * @param record the record
     * @param maxFill the fill limit for the node
     * @return the new node
     */
    private static A3_Q7_BPlus_Node createNodeUp(A3_Q7_BPlus_Node activeNode, A3_Q7_DataModel record, double maxFill){
        A3_Q7_BPlus_Node temp = new A3_Q7_BPlus_Node(twoM, maxFill);
        activeNode.parent = temp;
        temp.insertKey(record);
        temp.linkChild(activeNode);
        nodesAtDepth[floor]++;
        return temp;
    }

    /**
     * Creates an internal node moving down the tree
     * @param activeNode the current node
     * @param maxFill the limit for the new node
     * @return the new node
     */
    private static A3_Q7_BPlus_Node createNodeDown(A3_Q7_BPlus_Node activeNode, double maxFill){
        A3_Q7_BPlus_Node temp = new A3_Q7_BPlus_Node(twoM, maxFill);
        temp.linkParent(activeNode);
        activeNode.linkChild(temp);
        temp.linkLeft();
        floor--;
        nodesAtDepth[floor]++;
        return temp;
    }

    /**
     * Creates a new leaf node when moving down the tree
     * @param activeNode the current node
     * @param record the record to add
     * @param maxFill the fill limit for the leaf
     * @return the new leaf node
     */
    private static A3_Q7_BPlus_Leaf createLeafDown(A3_Q7_BPlus_Node activeNode, A3_Q7_DataModel record, double maxFill){
        A3_Q7_BPlus_Leaf temp = new A3_Q7_BPlus_Leaf(twoM, maxFill);
        temp.insertRecord(record);
        temp.linkParent(activeNode);
        activeNode.linkChild(temp);
        temp.linkLeft();
        floor--;
        nodesAtDepth[floor]++;
        return temp;
    }

    /**
     * climbs up the tree until the appropriate location for the key is found
     * Creates a new parent key if the current parent doesn't exist or is full
     * @param activeNode the current node
     * @param record the record to add
     * @return the node where the key was added
     */
    private static A3_Q7_BPlus_Node climbUp(A3_Q7_BPlus_Node activeNode, A3_Q7_DataModel record){
        //If the active node has a parent
        if(activeNode.parent != null){
            //If the parent is not full
            if(!activeNode.parent.checkNextKey()){
                activeNode = activeNode.parent;
                activeNode.insertKey(record);
                floor++;
                return activeNode;
            }
            else{
                //parent is full
                floor++;
                return climbUp(activeNode.parent, record);
            }
        }
        else { //if there is no parent, create a new one
            floor++;
            //Check if this a less full node, required to satisfy 50% fullness in last node
            if(skipIntervalAtDepth[floor]!=0){
                if(nodesPerDepth[floor]%(skipIntervalAtDepth[floor]) == 0){
                    return createNodeUp(activeNode, record, fillLimit-((double)1/(double)(twoM-1)*100));
                }
                else{
                    return createNodeUp(activeNode, record, fillLimit);
                }
            }
            else{
                return createNodeUp(activeNode, record, fillLimit);
            }
        }
    }

    /**
     * Trickles back down from the upper location to create the newest leaf node
     * @param activeNode the current node
     * @param record the record to add to the new leaf
     * @return the new leaf
     */
    private static A3_Q7_BPlus_Node trickleDown(A3_Q7_BPlus_Node activeNode, A3_Q7_DataModel record){
        //if at depth before leaf node, keep trickling down
        if(floor>1){
            if(skipIntervalAtDepth[floor-1]!=0){
                if(nodesAtDepth[floor-1]%(skipIntervalAtDepth[floor-1]) == 0){
                    return trickleDown(createNodeDown(activeNode,fillLimit-((double)1/(double)(twoM-1)*100)), record);
                }
                else{
                    return trickleDown(createNodeDown(activeNode, fillLimit), record);
                }
            }
            else{
                return trickleDown(createNodeDown(activeNode, fillLimit), record);
            }
        }
        //Otherwise insert the record in the new blank leaf node
        else{
            if (skipIntervalAtDepth[floor-1]!=0){
                if(nodesAtDepth[floor-1]%(skipIntervalAtDepth[floor-1]) == 0){
                    return createLeafDown(activeNode, record, fillLimit-((double)1/(double)(twoM-1)*100));
                }
                else{
                    return createLeafDown(activeNode, record, fillLimit);
                }
            }
            else{
                return createLeafDown(activeNode, record, fillLimit);
            }
        }
    }

    /**
     * Climbs the tree up to the root
     * @param activeNode the current location in the tree
     * @return the root of the tree
     */
    private static A3_Q7_BPlus_Node getRoot(A3_Q7_BPlus_Node activeNode){
        while(activeNode.parent!=null){
            activeNode = activeNode.parent;
        }
        return activeNode;
    }

    /**
     * Displays the tree, each depth is on one line, nodes are seperated on the same line
     * @param activeNode the current node, use the root for the whole tree
     */
    private static void displayBPlusTree(A3_Q7_BPlus_Node activeNode){

        if(activeNode.getClass()== A3_Q7_BPlus_Node.class){
            for(String key:activeNode.getKeys()){
                System.out.print(key + ": ");
            }
            if(activeNode.right!=null){
                nodeCNT++;
                System.out.print("<-----|| NEXT NODE ||----->");
                displayBPlusTree(activeNode.right);
            }
            else{
                System.out.println("\n" + (nodeCNT + 1));
                nodeCNT=0;
                System.out.print("\n");
                while(activeNode.left!=null){
                    activeNode = activeNode.left;
                }
                displayBPlusTree(activeNode.children[0]);
            }
        }
    }

    /**
     * Searches for a value in the B+ Tree
     * @param key the key for search
     * @return the record if found
     */
    public static A3_Q7_DataModel searchFor(String key){
        keyCompares=0;
        A3_Q7_BPlus_Node temp = findLeaf(key, root);
        if(temp!=null){
            A3_Q7_DataModel dataTemp = findRecord(key,(A3_Q7_BPlus_Leaf)temp);
            System.out.println("");
            System.out.println("Search For (" + key + ") found in (" + keyCompares + ") key compares");
            dataTemp.printRecord();
            System.out.println("====================================================================");
            return dataTemp;
        }
        else{
            return null;
        }
    }

    /**
     * Finds the leaf where the record shoul be
     * @param key the key
     * @param currentNode the current node
     * @return the leaf node where the record should be
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
     * Searches the leaf node for the record
     * @param key the search key
     * @param leafNode the leaf node
     * @return the record if found, a blank record otherwise
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
        ArrayList<A3_Q7_DataModel> userData = readFile("names.txt");

        int size = 10;
        double limit = 60;

        A3_Q7_BPlus_Tree_V2 bPlusTree = new A3_Q7_BPlus_Tree_V2(size, userData, limit);

        displayBPlusTree(root);

        A3_Q7_DataModel searchResult = searchFor("Azevedo, Ana");

        searchResult = searchFor("Silva, Rui");

        searchResult = searchFor("Boussebough, Imane");

        searchResult = searchFor("Terracina, Giorgio");

        searchResult = searchFor("Lefebvre, Peter");

        searchResult = searchFor("Houghten, Sher");

        searchResult = searchFor("Revesz, Peter");

    }
}
