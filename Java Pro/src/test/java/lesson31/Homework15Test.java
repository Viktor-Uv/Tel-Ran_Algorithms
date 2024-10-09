package lesson31;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * JavaPro. Homework #15
 *
 * @author Viktor Uvarchev
 * @version 08 Oct 2023
 */

public class Homework15Test {
    private List<String> testList;

    @Before
    public void prep() {
        testList = Arrays.asList(
                "Physics", "Psychology", "Philosophy", "Politics", "Programming"
        );
    }

    @Test
    public void getElementsTest() {
        assertThat(
                "Test returned type",
                Homework15.getElements(testList, 0),
                instanceOf(Collection.class)
        );

        assertThat(
                "Test returned List's size",
                Homework15.getElements(testList, 1, 2),
                hasSize(2)
        );

        assertThat(
                "Check first and last elements returned",
                Homework15.getElements(testList, 0, 4),
                equalTo(
                        Arrays.asList("Physics", "Programming")
                )
        );

        assertThat(
                "Check all elements returned",
                Homework15.getElements(testList, 0, 1, 2, 3, 4),
                equalTo(testList)
        );

        assertThat(
                "Check one element returned, given that two out of three indexes are invalid",
                Homework15.getElements(testList, -3, 2, 6),
                equalTo(List.of("Philosophy"))
        );
    }
}
