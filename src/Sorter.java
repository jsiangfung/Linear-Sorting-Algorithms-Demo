import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * A class representing a group of linear sorting algorithms - counting sort, radix sort, and bucket sort
 */
public class Sorter {
    int[] nums;
    final String hexadecimal = "0123456789ABCDEF";

    /**
     * Constructor for the Sorter class
     * @param nums a given array of integers
     */
    public Sorter(int[] nums) {
        this.nums = nums;
    }

    /**
     * Counting sort algorithm that sorts a given array of integers of range k and returns the sorted output
     * @param unsorted the unsorted array of integers
     * @param output the new array to put the sorted integers into
     * @param k the range for the values in the given unsorted array of integers
     */
    public void countingSort(int[] unsorted, int[] output, int k){
        sop("Running counting sort...");
        int[] counter = new int[k+1]; //Initialize array used for counting
        //Sets every value in counter to 0
        sop("Setting C[0...k] to 0");
        for(int i=0; i<= k; i++){
            counter[i] = 0;
        }
        sop(Arrays.toString(counter));
        //counts the number of times each value at unsorted[j] appears
        sop("");
        sop("C[0...k] now contains the number of elements equal to i = {0...k} in A");
        for(int j=0; j<unsorted.length; j++){
            //divide by 10 since I'm using the first digit of the number to compare relative indexes of equal numbers to show this algorithm is stable
            counter[unsorted[j]/10] = counter[unsorted[j]/10] +1;
        }
        sop(Arrays.toString(counter));
        //Sets counter[i] to be the index where the value is inserted into the output array
        sop("");
        sop("C[0...k] now tallies what the correct index for the value at A to be put in B");
        for(int i=1; i<= k; i++){
            counter[i] = counter[i] + counter[i-1];
        }
        sop(Arrays.toString(counter));
        //Sets unsorted[j] in the correct sorted location in the output array based on the indexes stored in counter
        sop("");
        sop("Assigning values from A to B");
        for(int j=unsorted.length-1; j>=0 ; j--) {
            output[counter[unsorted[j]/10]-1] = unsorted[j];
            counter[unsorted[j]/10] = counter[unsorted[j]/10] - 1;
            sop("C: " + Arrays.toString(counter));
            sop("B: " + Arrays.toString(output));
        }
        sop("Sorted array: " + Arrays.toString(output));
    }

    /**
     * Counting sort algorithm that sorts a given array of hexadecimal strings of range k on the given digit
     * and returns the sorted output
     * @param unsorted the unsorted array of integers
     * @param output the new array to put the sorted integers into
     * @param k the range for the values in the given unsorted array of integers
     * @param digit the digit of the values to sort
     */
    public void countingSort(String[] unsorted, String[] output, int k, int digit){
        sop("Running counting sort...");
        //Initializes the array that counts values in unsorted
        int[] counter = new int[k+1];
        //Sets every value in counter to 0, or in this case with hexadecimal strings, to null.
        sop("Setting C[0...k] to 0");
        for(int i=0; i<= k; i++){
            counter[i] = 0;
        }
        sop(Arrays.toString(counter));
        //Counts the number of times each value at the digit appears in unsorted
        sop("");
        sop("C[0...k] now contains the number of elements equal to i = {0...k} in A");
        for(int j=0; j<unsorted.length; j++){
            //hexadecimal.indexOf(unsorted[j].charAt(digit)) helps assign a numerically appropriate number to the hexadecimal for comparisons
            counter[hexadecimal.indexOf(unsorted[j].charAt(digit))] = counter[hexadecimal.indexOf(unsorted[j].charAt(digit))] +1;
        }
        sop(Arrays.toString(counter));
        //Sets counter[i] to be the index where the value is inserted into the output array
        sop("");
        sop("C[0...k] now tallies what the correct index for the value at A to be put in B");
        for(int i=1; i<= k; i++){
            counter[i] = counter[i] + counter[i-1];
        }
        sop(Arrays.toString(counter));
        //Sets unsorted[j] in the correct sorted location in the output array based on the indexes stored in counter
        sop("");
        sop("Assigning values from A to B");
        for(int j=unsorted.length-1; j>=0 ; j--) {
            output[counter[hexadecimal.indexOf(unsorted[j].charAt(digit))]-1] = unsorted[j];
            counter[hexadecimal.indexOf(unsorted[j].charAt(digit))] = counter[hexadecimal.indexOf(unsorted[j].charAt(digit))] - 1;
            sop("C: " + Arrays.toString(counter));
            sop("B: " + Arrays.toString(output));
        }
        sop("Sorted array: " + Arrays.toString(output));
    }

    /**
     * Radix sort algorithm that calls counting sort on each digit, starting from the first, of each value in the given array of hexadecimals
     * and then returns the sorted array
     * Note: This algorithm relies on a specialized counting sort algorithm that only works with hexadecimals
     * @param unsorted the unsorted array of hexadecimal strings
     * @param d amount of digits to sort
     * @return the original array after sorting it
     */
    public String[] radixSort(String[] unsorted, int d){
        sop("Running radix sort...");
        for(int i=d-1; i>=0; i--){
            String[] sorted = new String[unsorted.length];
            //counting sort on digit i
            sop("");
            sop("Running counting sort on digit " + (d-i) + "...");
            countingSort(unsorted, sorted, hexadecimal.length()-1, i);
            unsorted = sorted; //this makes the algorithm stable since the sorted array of the previous digit is used as the unsorted array
            sop("Finished sorting on digit " + (d-i));
        }
        return unsorted;
    }

    /**
     * Bucket sort algorithm that assigns similar values from a given array of integers into buckets that are sorted
     * with insertion sort and concatenated together to form a sorted array of integers
     * @param unsorted the given array of integers
     * @return the original given array of integers, now sorted
     */
    public int[] bucketSort(int[] unsorted){
        int n = unsorted.length;
        LinkedList<Integer>[] buckets = new LinkedList[n];
        sop("Running bucket sort...");
        //Adds a bucket(linked list) containing integers to each index of the array 'buckets'
        for(int i=0; i<n; i++){
            buckets[i] = new LinkedList();
        }
        //Assigns similar values in 'unsorted' to buckets and ensures no bucket is too full
        for(int i=0; i<n; i++) {
            double constant = 0.01; // 1/100 since we want our unsorted[i] to be a number between 0 to 1.0
            buckets[(int)(constant * n * unsorted[i])].add(unsorted[i]);
        }
        //Sorts the values in each bucket using insertion sort
        for(int i=0;i<n;i++){
            Collections.sort(buckets[i]); //With how small our array sizes are, the sort method from the Collections class will use insertion sort
        }
        //Concatenate the lists B[0], B[1]... B[n-1] together in order
        int k = 0;
        for(int i=0; i<buckets.length;i++){
            for(int j=0;j<buckets[i].size();j++){
                unsorted[k++] = buckets[i].get(j); //still O(n) since we traverse through n elements
            }
        }
        return unsorted;
    }


    /**
     * Prints out a given message
     * @param s the message to print out
     */
    public void sop(String s){
        System.out.println(s);
    }
}
