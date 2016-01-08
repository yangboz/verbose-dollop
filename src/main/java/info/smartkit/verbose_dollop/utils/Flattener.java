package info.smartkit.verbose_dollop.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangboz on 1/8/16.
 */
public class Flattener {
    /**
     * Flattens an array of arbitrarily nested arrays of integers into
     * a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4]
     *
     * @param nestedNumbers - array ob objects (either plain integers or nested arrays of integers)
     * @return - flat array of Integers
     */
    public static List<Integer> flatten(Object[] nestedNumbers) {

        if (nestedNumbers == null) return null;

        List<Integer> flattenedNumbers = new ArrayList<>();

        for (Object element : nestedNumbers) {
            if (element instanceof Integer) {
                flattenedNumbers.add((Integer) element);
            } else {
                flattenedNumbers.addAll(flatten((Object[]) element));
            }
        }
        return flattenedNumbers;
    }

}
