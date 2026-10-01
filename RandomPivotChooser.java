package assign05;

import java.util.ArrayList;
import java.util.Random;
/**
 * Pivot chooseer, chooses a random element as the pivot in the given indexes.
 *
 * @param <E> the type of elements in the list being sorted
 * @author Thakshbir Singh Dhillon and Abhinav Mishra
 * @version 09-29-2026
 */
public class RandomPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> 
{
	 private Random rng = new Random();//random object

	@Override
	public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) 
	{
		//inclusive of both indexes
		int random =  leftIndex + rng.nextInt(rightIndex - leftIndex + 1);
		return random;
	}

}
