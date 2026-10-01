package assign05;

import java.util.ArrayList;

/**
 * Merge sort which sorts using insertion sort when the array size is small enough.
 *
 * @param <E> the type of elements in the list being sorted
 * @author Thakshbir Singh Dhillon and Abhinav Mishra
 * @version 09-29-2026
 */
public class MergeSorter<E extends Comparable<? super E>> implements Sorter<E> 
{

    private int threshold;
    /**
     * Creates a merge sorter that switches to insertion sort for smaller lists.
     *
     * @param threshold - size at which we switch to insertion sort
     * @throws IllegalArgumentException if threshold is not positive
     */
    public MergeSorter(int threshold) {
        if (threshold <= 0) 
        {
            throw new IllegalArgumentException();
        }
        this.threshold = threshold;
    }

    /**
     * Driver method fot sortting the list in ascending order.
     *
     * @param list- the list to sort
     */
    @Override
    public void sort(ArrayList<E> list) 
    {
    	//if the list is empty or the list is 1 element which is already sorted
        if (list == null || list.size() < 2)
            return;

        // making a temporary empty list so we dont have to initialise every single time.
        ArrayList<E> temp = new ArrayList<E>(list.size());
        for (int i = 0; i < list.size(); i++) 
        {
        	temp.add(null);
        }
        //calling the recurssive method with temp array
        mergeSort(list, temp, 0, list.size() - 1, this.threshold);
    }

    /**
     * private recusrive method for sorting the list using merge sort and then merging them.
     */
    private void mergeSort(ArrayList<E> list, ArrayList<E> temp, int left, int right, int thresh) 
    {
    	//base case
        if (right - left +1  <= thresh)
        {
            insertionSort(list, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(list, temp, left, mid, thresh); //recursing the left arraylist
        mergeSort(list, temp, mid + 1, right, thresh); //recursing the right arraylist
        merge(list, temp, left, mid, right);//merging the lists after they sort
    }

    /**
     * Merges the sorted ranges list[left..mid] and list[mid+1..right].
     */
    private void merge(ArrayList<E> list, ArrayList<E> temp, int left, int mid, int right) {

	    int i = left;// starting index for the left array
	    int j = mid + 1; //starting index for the right array
	    int k = left; //starting index of the temp array

	    while (i <= mid && j <= right) 
	    {
	        if (list.get(i).compareTo(list.get(j)) <= 0) 
	        {
	            temp.set(k, list.get(i));
	            i++;
	        }
	        else 
	        {
	            temp.set(k, list.get(j));
	            j++;
	        }
	        k++;
	    }

	    // adds the left over elements from the left array
	    while (i <= mid) 
	    {
	        temp.set(k, list.get(i));
	        i++;
	        k++;
	    }

	    //adds the left over elements from the right array
	    while (j <= right) 
	    {
	        temp.set(k, list.get(j));
	        j++;
	        k++;
	    }

	    //coppying the temp list to normal list
	    for (k = left; k <= right; k++) 
	    {
	        list.set(k, temp.get(k));
	    }
	 }

    /**
     * Insertion sort for implementing on ArrayList with a start and end index when the threshold is less than 0.
     */
    private void insertionSort(ArrayList<E> list, int start, int end) 
    {
        for (int i = start + 1; i <= end; i++) 
        {
            E key = list.get(i);
            int j = i - 1;
            while (j >= start && list.get(j).compareTo(key) > 0) 
            {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}