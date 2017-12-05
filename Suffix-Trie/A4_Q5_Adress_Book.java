/**
 * Code used to produce a searchable address book for an 
 * assignment in COMP5511 - Algorithms and Data Strutures
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @version  1.0
 * @since    1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class A4_Q5_Adress_Book {
    public static ArrayList<A4_Q5_DataModel> records;

    /**
     * Reads the data file and sorts the data required to build the B+ tree
     * @param filepath the file containing the data
     * @return an sorted array of records
     */
    private static ArrayList<A4_Q5_DataModel> readFile(String filepath){
        File dataFile = new File(filepath);
        ArrayList<String> rawData = new ArrayList<>();
        ArrayList<A4_Q5_DataModel> processedData =  new ArrayList<>();
        try{
            //Read the raw data in
            Scanner scanner = new Scanner(dataFile);
            while(scanner.hasNextLine()) {
                rawData.add(scanner.nextLine().replaceAll(": ", ":").toLowerCase());
            }
            //Sort the raw data
            Collections.sort(rawData);
            for(String str:rawData){
                processedData.add(new A4_Q5_DataModel(str));
            }
            return processedData;
        } catch (FileNotFoundException ex){
            System.out.println("File could not be found...");
            return null;
        }
    }

    public static void main(String args[]){
        records = readFile("data.txt");

        A4_Q5_Suffix_Trie addressBook = new A4_Q5_Suffix_Trie();

        //Easy to add records
        for(A4_Q5_DataModel record:records){
            addressBook.addRecord(record);
        }

        Scanner keyboard = new Scanner(System.in);
        ArrayList<A4_Q5_DataModel> recordSearchA;
        boolean repeat = true;
        String userInput = "start";
        while(!userInput.equals("!EXIT")){
            System.out.println();
            System.out.println("Please enter a search or \"!EXIT\" to terminate");
            System.out.println("=================================================================================================================");
            userInput = keyboard.nextLine();
            if(!userInput.equals("!EXIT")){
                System.out.println("=================================================================================================================");
                System.out.println("Search for: " + userInput);
                System.out.println("=================================================================================================================");
                recordSearchA = addressBook.searchForRecords(userInput);
                if(recordSearchA==null){
                    System.out.println("No records found for search: " + userInput);
                }
                else{
                    for(A4_Q5_DataModel rec:recordSearchA){
                        rec.printRecord();
                    }
                }
            }
        }
    }
}
