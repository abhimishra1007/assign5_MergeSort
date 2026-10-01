package assign05;

import java.util.ArrayList;
/**
 * Pivot chooseer, chooses the median of the three elements as the pivot
 *
 * @param <E> the type of elements in the list being sorted
 * @author Thakshbir Singh Dhillon and Abhinav Mishra
 * @version 09-29-2026
 */
public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E>
{
	/**
     * Returns the index of the median from the first, middle, and last element
     *
     * @param list - the list being sorted
     * @param leftIndex  - the leftmost index of the range
     * @param rightIndex - the rightmost index of the range
     * @return an int that is the index of the median of the three elements
     */
	@Override
	public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) 
	{
	    int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
	    E left = list.get(leftIndex);
	    E mid = list.get(midIndex);
	    E right = list.get(rightIndex);

	    if (left.compareTo(mid) <= 0 && mid.compareTo(right) <= 0) 
	    {
	        return midIndex;
	    }

	    if (right.compareTo(mid) <= 0 && mid.compareTo(left) <= 0) 
	    {
	        return midIndex;
	    }

	    if (mid.compareTo(left) <= 0 && left.compareTo(right) <= 0) {
	        return leftIndex;
	    }

	    if (right.compareTo(left) <= 0 && left.compareTo(mid) <= 0) {
	        return leftIndex;
	    }

	    return rightIndex;
	}

}
