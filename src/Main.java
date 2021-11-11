import java.util.Arrays;

/**
 * Runs a program that initializes a set of arrays and calls the methods of the Sorter class for counting sort, radix sort, and bucket sort on them.
 */
public class Main {
    public static void main(String[] args){
       //Counting sort using array nums1 and output
       int[] nums1 =  {71, 72, 21, 81, 41, 22, 31, 51, 42, 82, 32, 61, 43, 62, 23};
       int[] nums2 = nums1; //old reference of nums1 to be used in bucket sort
       int[] output = new int[nums1.length];
       Sorter tester = new Sorter(nums1);
       sop("Input array: " + Arrays.toString(nums1));
       tester.countingSort(nums1, output, 9);
       sop("");

//      Radix sort on an array of randomly generated 5-digit hex strings
        String[] hex = randomHexadecimalArray(30);
        sop("Input array: " + Arrays.toString(hex));
        sop("Sorted array: " + Arrays.toString(tester.radixSort(hex, 5)));
        sop("");
//      Bucket sort on nums2
        sop("Input array: " + Arrays.toString(nums2));
        sop("Sorted array: " + Arrays.toString(tester.bucketSort(nums2)));
    }

    static String hexadecimal = "0123456789ABCDEF";

    /**
     * Creates an array of a given size containing 5-digit hexadecimals strings
     * @param size the given size of the array
     * @return the array of hexadecimal strings
     */
    static String[] randomHexadecimalArray(int size){
        String[] array = new String[size];
        for(int i=0;i<size; i++){
            //c1 - c5 each represent a random hexadecimal digit
            char c1 = hexadecimal.charAt(random(0,15));
            char c2 = hexadecimal.charAt(random(0,15));
            char c3 = hexadecimal.charAt(random(0,15));
            char c4 = hexadecimal.charAt(random(0,15));
            char c5 = hexadecimal.charAt(random(0,15));
            //c1 - c5 are concatenated to form a randomly generated 5-digit hex string
            String s = Character.toString(c1) + Character.toString(c2) + Character.toString(c3) + Character.toString(c4) + Character.toString(c5);
            array[i] = s;
        }
        return array;
    }

    /**
     * returns a random integer between a minimum and maximum index
     * @param min the minimum index
     * @param max the maximum index
     * @return a random integer between the given indexes
     */
    static int random(int min, int max){
        return min + (int)(Math.random()*((max - min) + 1));
    }

    /**
     * Prints out a given message
     * @param s the message to print out
     */
    static void sop(String s){
        System.out.println(s);
    }
}
