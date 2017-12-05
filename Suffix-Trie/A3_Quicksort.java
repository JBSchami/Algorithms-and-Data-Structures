/**
 * Quicksort implementation in Java
 * @author   Jonathan Bedard Schami <jbeda091@uottawa.ca>
 * @version  1.0
 * @since    1.0
 */
public class A3_Quicksort {
    private String array[];
    private int length;

    /**
     * Calls the quick sorting algorithm
     * @param inputArr - The input array that needs to be sorted
     */
    public void sort(String[] inputArr){
        if (inputArr == null || inputArr.length == 0){
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        doQuickSort(0, length-1);
    }

    /**
     * The quick sorting algorithm. Uses recursion to sort through the whole array
     * @param start - The first position of the array
     * @param end - The last position of the array
     */
    private void doQuickSort(int start, int end){
        int i = start;
        int j = end;

        String pivot = array[start+(end-start)/2];

        while(i<=j){
            while(array[i].compareTo(pivot)<0){
                i++;
            }
            while(array[j].compareTo(pivot)>0){
                j--;
            }
            if (i<=j){
                swap(i,j);
                i++;
                j--;
            }
        }
        if (start < j)
            doQuickSort(start,j);
        if (i < end)
            doQuickSort(i, end);
    }

    /**
     * Swaps the position of two values in an array
     * @param i - Position of element 1, future position of element 2
     * @param j - Position of element 2, future position of element 1
     */
    private void swap(int i, int j){
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

