package assign05;

import java.util.ArrayList;
import java.util.Random;

/**
 * Pivot chooser that selects a uniformly random element of the range.
 *
 * @param <E> the type of elements in the list
 */
public class RandomPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {

    private final Random rng = new Random();

    /**
     * Returns the index of a random element in the range.
     *
     * @param list       the list being sorted
     * @param leftIndex  the leftmost index of the range
     * @param rightIndex the rightmost index of the range
     * @return a random index in [leftIndex, rightIndex]
     */
    @Override
    public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
        return leftIndex + rng.nextInt(rightIndex - leftIndex + 1);
    }
}