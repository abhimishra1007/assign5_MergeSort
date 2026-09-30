package assign05;

import java.util.ArrayList;

/**
 * Merge sort with a threshold below which sublists are sorted using insertion sort.
 *
 * @param <E> the type of elements in the list being sorted
 */
public class MergeSorter<E extends Comparable<? super E>> implements Sorter<E> {

    private final int threshold;

    /**
     * Creates a merge sorter that switches to insertion sort for small sublists.
     *
     * @param threshold the sublist size at which to switch to insertion sort
     * @throws IllegalArgumentException if threshold is not positive
     */
    public MergeSorter(int threshold) {
        if (threshold <= 0)
            throw new IllegalArgumentException("Threshold must be positive, got: " + threshold);
        this.threshold = threshold;
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

        // If the list is smaller than the threshold, use the list size instead.
        // A local variable is used so the stored threshold is unchanged for later calls.
        int effectiveThreshold = Math.min(threshold, list.size());

        // Allocate the temporary storage once, instead of once per merge.
        ArrayList<E> temp = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++)
            temp.add(null);

        mergeSort(list, temp, 0, list.size() - 1, effectiveThreshold);
    }

    /**
     * Recursive merge sort on list[left..right], inclusive.
     */
    private void mergeSort(ArrayList<E> list, ArrayList<E> temp, int left, int right, int thresh) {
        if (right - left + 1 <= thresh) {
            insertionSort(list, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(list, temp, left, mid, thresh);
        mergeSort(list, temp, mid + 1, right, thresh);
        merge(list, temp, left, mid, right);
    }

    /**
     * Merges the sorted ranges list[left..mid] and list[mid+1..right].
     */
    private void merge(ArrayList<E> list, ArrayList<E> temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            // Using <= keeps the sort stable.
            if (list.get(i).compareTo(list.get(j)) <= 0)
                temp.set(k++, list.get(i++));
            else
                temp.set(k++, list.get(j++));
        }
        while (i <= mid)
            temp.set(k++, list.get(i++));
        while (j <= right)
            temp.set(k++, list.get(j++));

        for (k = left; k <= right; k++)
            list.set(k, temp.get(k));
    }

    /**
     * Insertion sort on list[left..right], inclusive.
     */
    private void insertionSort(ArrayList<E> list, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            E key = list.get(i);
            int j = i - 1;
            while (j >= left && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}