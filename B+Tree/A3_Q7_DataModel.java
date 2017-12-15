/*
COMP5511, Desai, Assignment 3 - Question 7
avirup CHATTAPADAY, 26451640, av_chatt@encs.concordia.ca
brandon HANDFIELD, 40070779, b_handfi@encs.concordia.ca
jonathan BEDARD SCHAMI, 40050610, j_edars@encs.concordia.ca
*/

/**
 * Code used to implement the record structure for the B+Tree
 * required for Assignment 3 Question 7
 * Re-used here for Assignment 3, to sort the data in the assignment 3
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @author   Brandon Handfield <b_handfi@encs.concordia.ca>
 * @author   Avirup Chattapadday <av_chatt@encs.concordia.ca>
 * @version  1.0
 * @since    1.0
 */

public class A3_Q7_DataModel {
    protected String name;
    protected String email;
    protected String university;
    protected String country;

    /**
     * Constructor for the data model
     * @param data a string containing all the records info, seperated by :
     */
    public A3_Q7_DataModel(String data){
        String[] entries = data.split(":", 4);
        name = entries[0];
        email = entries[1];
        university = entries[2];
        country = entries[3];
    }

    public A3_Q7_DataModel(String data, int nada){
        name = data;
        email = null;
        university = null;
        country = null;
    }

    /**
     * Default constructor for null record
     */
    public A3_Q7_DataModel(){
        name = null;
        email = null;
        university = null;
        country = null;
    }

    /**
     * Getter for name
     * @return record name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for e-mail
     * @return record email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for univeristy
     * @return record university name
     */
    public String getUniversity() {
        return university;
    }

    /**
     * Getter for country
     * @return record country
     */
    public String getCountry() {
        return country;
    }

    /**
     * displays record in console
     */
    public void printRecord(){
        if(name != null){
            System.out.println(name + ": " + email + ": " + university + ": " + country);
        }
        else{
            System.out.println("Record does not exist");
        }
    }
}
