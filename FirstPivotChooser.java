package assign05;

import java.util.ArrayList;
/**
 * Pivot chooser, chooses the first element as the pivot.
 *
 * @param <E> the type of elements in the list being sorted
 * @author Thakshbir Singh Dhillon and Abhinav Mishra
 * @version 09-29-2026
 */
public class FirstPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

	/**
     * Returns the index of the first element in the range.
     *
     * @param list - the list being sorted
     * @param leftIndex  - the leftmost index of the range
     * @param rightIndex - the rightmost index of the range
     * @return leftIndex
     */
    @Override
    public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) 
    {
        return leftIndex;
    }

	
}
