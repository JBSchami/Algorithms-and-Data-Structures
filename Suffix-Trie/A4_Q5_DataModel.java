/**
 * Code used to implement data model required for records in
 * a COMP5511 assignmetn, It is an extension of the A3_Q7_DataModel
 * Re-used here for Assignment 4
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @version  1.0
 * @since    1.0
 */


public class A4_Q5_DataModel extends A3_Q7_DataModel {
    String firstName;
    String lastName;

    /**
     * default constructor
     */
    public A4_Q5_DataModel(){
        firstName = null;
        lastName = null;
    }

    /**
     * Constructor when data is provided
     * @param data the record to convert into the appropriate data format
     */
    public A4_Q5_DataModel(String data){
        String[] entries = data.split(":", 4);
        name = entries[0];
        email = entries[1];
        university = entries[2];
        country = entries[3];

        String temp = name.replaceAll(", ", ":");
        String[] namesList = temp.split(":", 2);
        firstName = namesList[1];
        lastName = namesList[0];
    }

    /**
     * Getter for first name of user in record
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the last name of the user in the record
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }
}
