package Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* Test Cases Ran:
*   [4,5,2,7,91]
*       - 7 for left-most item
*       - 10 for right-most item
*
*   [55,6,0,1,222,3,5,77,7,7]
*       - 23 for the left-most item
*       - 19 for the right most item
*
*   [15,23,13,27,12,10,20,25]
*       - 14 for the left-most item
*       - 18 for the right most item
* */

public class Sorting {
    static long comparisons = 0;
    public static Integer[] readArrFile() throws IOException {
        // Reading the input file
        List<Integer> listOfInteger= new ArrayList<Integer>();
        BufferedReader bfReader = new BufferedReader(new FileReader("src/Project1/QuickSort.txt"));
        String line = bfReader.readLine();

        while(line != null){
            listOfInteger.add(Integer.parseInt(line));
            line = bfReader.readLine();
        }

        bfReader.close();

        return listOfInteger.toArray(new Integer[0]);
    }
    static void swap(Integer[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partitionLast(Integer[] arr, int low, int high){
        swap(arr, low, high);

        int pivotPoint = low;
        int j = low;

        for (int i = low + 1; i <= high; i++){
            if (arr[pivotPoint] > arr[i]){
                j++;
                swap(arr, i, j);
            }
        }
        pivotPoint = j;
        swap(arr, low, pivotPoint);

        return pivotPoint;
    }

    public static int partitionFirst(Integer[] arr, int low, int high){
        int pivotPoint = low;
        int j = low;

        for (int i = low + 1; i <= high; i++){
            if (arr[pivotPoint] > arr[i]){
                j++;
                swap(arr, i, j);
            }
        }
        pivotPoint = j;
        swap(arr, low, pivotPoint);

        return pivotPoint;
    }

    public static void quickSort(Integer[] arr, int low, int high, boolean leftPivot){

        if (high > low && leftPivot){
            int pivotPoint = partitionFirst(arr, low, high);
            comparisons += (pivotPoint-1 - low); // I do not subtract 1 because my highs and lows are indexes
            quickSort(arr, low, pivotPoint-1, leftPivot);
            comparisons += (high - pivotPoint+1);
            quickSort(arr,pivotPoint+1, high, leftPivot);
        } else if (high > low) {
            int pivotPoint = partitionLast(arr, low, high);
            comparisons += (pivotPoint-1 - low); // I do not subtract 1 because my highs and lows are indexes
            quickSort(arr, low, pivotPoint-1, leftPivot);
            comparisons += (high - pivotPoint+1);
            quickSort(arr,pivotPoint+1, high, leftPivot);
        }
    }

    public static void main(String[] args) throws IOException {

//        Integer [] arr1 = new Integer[]{15,23,13,27,12,10,20,25};
//        quickSort(arr1, 0, arr1.length-1, true);
//        System.out.println(comparisons);
//
//        comparisons = 0;
//        Integer [] arr2 = new Integer[]{15,23,13,27,12,10,20,25};
//        quickSort(arr2, 0, arr2.length-1, false);
//        System.out.println(comparisons);

//        Integer [] arr1 = new Integer[]{17,87,48,8,47};
//        quickSort(arr1, 0, arr1.length-1, false);
//        System.out.println(comparisons);
//
//        comparisons = 0;
//        Integer [] arr2 = new Integer[]{17,87,48,8,47};
//        quickSort(arr2, 0, arr2.length-1, true);
//        System.out.println(comparisons);


        Integer [] arr1 = readArrFile();
        quickSort(arr1,0, arr1.length-1, true);
        System.out.println(comparisons);

        comparisons = 0;
        Integer [] arr2 = readArrFile();
        quickSort(arr2,0, arr2.length-1, false);
        System.out.println(comparisons);
    }
}
