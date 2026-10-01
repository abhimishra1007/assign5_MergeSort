package assign05;

import java.util.ArrayList;

/**
 * Quick sort that uses a pivot chooser to sort the list.
 *
 * @param <E> the type of elements in the list being sorted
 * @author Thakshbir Singh Dhillon and Abhinav Mishra
 * @version 09-29-2026
 */
public class QuickSorter<E extends Comparable<? super E>> implements Sorter<E> 
{

    private PivotChooser<E> chooser;

    /**
     * Constructor that uses the given pivot chooser.
     *
     * @param chooser - the strategy used to select a pivot element
     */
    public QuickSorter(PivotChooser<E> chooser) 
    {
        this.chooser = chooser;
    }

    /**
     * Sorts the list in ascending order.
     *
     * @param list the list to sort
     */
    @Override
    public void sort(ArrayList<E> list) 
    {
    	//if the list is empty or has a single element
        if (list == null || list.size() < 2) 
        {
            return;
        }
        //recursive call
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * recursive method that sorts using quick sort
     *
     * @param list -the list being sorted
     * @param left -the leftmost index
     * @param right- the rightmost index
     */
    private void quickSort(ArrayList<E> list, int left, int right) 
    {
    	//base case
        if (left >= right) 
        {
            return;
        }
        int pivotIndex = chooser.getPivotIndex(list, left, right);
        int finalPivotIndex = seperate(list, left, right, pivotIndex);
        quickSort(list, left, finalPivotIndex - 1);//left seperated array
        quickSort(list, finalPivotIndex + 1, right);// right seperated array
    }

    /**
     * seperates the list around the pivot and return the index of the pivot.
     *
     * @return n int that is the final index of the pivot
     */
    private int seperate(ArrayList<E> list, int left, int right, int pivotIndex) 
    {
        E pivot = list.get(pivotIndex);
        swap(list, pivotIndex, right);//moving pivot to the end
        int i = left;//index which is to be swapped
        for (int j = left; j < right; j++) 
        {
            if (list.get(j).compareTo(pivot) < 0) 
            {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, i, right);//moving pivot back
        return i;//the index were the pivot was at the end.
    }

    /**
     * helper method that swaps two elements in the list.
     */
    private void swap(ArrayList<E> list, int left, int right)
    {

        E temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }
}