package assign05;

import java.util.ArrayList;

/**
 * Quicksort using a pluggable pivot-selection strategy. Never switches to
 * insertion sort.
 *
 * @param <E> the type of elements in the list being sorted
 */
public class QuickSorter<E extends Comparable<? super E>> implements Sorter<E> {

    private final PivotChooser<E> chooser;

    /**
     * Creates a quick sorter that uses the given pivot-selection strategy.
     *
     * @param chooser the strategy used to select a pivot element
     */
    public QuickSorter(PivotChooser<E> chooser) {
        this.chooser = chooser;
    }

    /**
     * Driver method: sorts the list in ascending order.
     *
     * @param list the list to sort
     */
    @Override
    public void sort(ArrayList<E> list) {
        if (list == null || list.size() < 2)
            return;
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * Recursive quicksort on list[left..right], inclusive.
     *
     * Recurses on the smaller partition and loops on the larger one, which keeps
     * the call stack at O(log N) depth even when a bad pivot makes the running
     * time quadratic. The algorithm and its comparisons are unchanged.
     *
     * @param list  the list being sorted
     * @param left  the leftmost index of the range to sort
     * @param right the rightmost index of the range to sort
     */
    private void quickSort(ArrayList<E> list, int left, int right) {
        while (left < right) {
            int pivotIndex = chooser.getPivotIndex(list, left, right);
            int p = partition(list, left, right, pivotIndex);

            if (p - left < right - p) {
                quickSort(list, left, p - 1);
                left = p + 1;
            } else {
                quickSort(list, p + 1, right);
                right = p - 1;
            }
        }
    }

    /**
     * Partitions list[left..right] around the element at pivotIndex. Afterward,
     * everything left of the returned index is less than or equal to the pivot,
     * and everything right of it is greater than or equal to the pivot.
     *
     * @param list       the list being sorted
     * @param left       the leftmost index of the range
     * @param right      the rightmost index of the range
     * @param pivotIndex the index of the chosen pivot
     * @return the final index of the pivot
     */
    private int partition(ArrayList<E> list, int left, int right, int pivotIndex) {
        E pivot = list.get(pivotIndex);
        swap(list, pivotIndex, right); // move pivot out of the way

        int i = left;
        int j = right - 1;
        while (true) {
            // Stopping on elements equal to the pivot keeps partitions balanced
            // when the list has many duplicates.
            while (i < right && list.get(i).compareTo(pivot) < 0)
                i++;
            while (j > left && list.get(j).compareTo(pivot) > 0)
                j--;
            if (i >= j)
                break;
            swap(list, i, j);
            i++;
            j--;
        }

        swap(list, i, right); // put pivot in its final place
        return i;
    }

    /**
     * Swaps two elements of the list.
     *
     * @param list the list
     * @param i    index of the first element
     * @param j    index of the second element
     */
    private void swap(ArrayList<E> list, int i, int j) {
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}