package info.smartkit.verbose_dollop;

import info.smartkit.verbose_dollop.utils.Flattener;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by yangboz on 1/8/16.
 */
public class FlattenerTests {
    /**
     * Helper-method for testing.
     *
     * @param list - flat list of Integers.
     * @return - array with the same objects.
     */
    private static Integer[] toArray(List<Integer> list) {
        return list.toArray(new Integer[list.size()]);
    }

    @Test
    public void testFlatten() {


        // test for null
        Assert.assertNull(Flattener.flatten(null));

        // empty array
        Assert.assertArrayEquals(
                "empty array",
                new Integer[]{},
                toArray(Flattener.flatten(new Object[]{}))
        );

        // un-nested array
        Assert.assertArrayEquals(
                "flat Array",
                new Integer[]{1, 2, 3, 4, 5},
                toArray(Flattener.flatten(new Object[]{1, 2, 3, 4, 5}))
        );

        // two level nested array
        Assert.assertArrayEquals(
                "flatten a two level nested array",
                new Integer[]{1, 2, 3, 4, 5, 6, 7},
                toArray(Flattener.flatten(new Object[]{1, 2, new Object[]{3, 4, 5}, 6, 7}))
        );

        // three level nested array
        Assert.assertArrayEquals(
                "flatten a three level nested array",
                new Integer[]{1, 2, 3, 4, 5, 6, 7},
                toArray(Flattener.flatten(new Object[]{1, 2, new Object[]{3, new Object[]{4, 5}}, 6, 7}))
        );

    }
}
