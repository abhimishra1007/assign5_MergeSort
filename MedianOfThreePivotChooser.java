package assign05;

import java.util.ArrayList;

/**
 * Pivot chooser that selects the median of the first, middle, and last elements
 * of the range.
 *
 * @param <E> the type of elements in the list
 */
public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

    /**
     * Returns the index of the median of the first, middle, and last elements.
     *
     * @param list       the list being sorted
     * @param leftIndex  the leftmost index of the range
     * @param rightIndex the rightmost index of the range
     * @return the index of the median-of-three element
     */
    @Override
    public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
        int mid = leftIndex + (rightIndex - leftIndex) / 2;
        E a = list.get(leftIndex);
        E b = list.get(mid);
        E c = list.get(rightIndex);

        if (a.compareTo(b) <= 0) {
            if (b.compareTo(c) <= 0)
                return mid;                                    // a <= b <= c
            return (a.compareTo(c) <= 0) ? rightIndex : leftIndex; // b is largest
        } else {
            if (a.compareTo(c) <= 0)
                return leftIndex;                              // b < a <= c
            return (b.compareTo(c) <= 0) ? rightIndex : mid;   // a is largest
        }
    }
}