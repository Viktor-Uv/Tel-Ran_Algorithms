package lesson29;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/*
Shortened Hamcrest documentation with examples:
https://gist.github.com/andriidemus/bd3e7e74c9f89218d4bfae3d910cc36e
 */

public class HamcrestIntro {
    @Test
    public void testArray() {
        String[] words = {"hello", "world", "equator", "sun"};
        assertThat(words, arrayWithSize(4));
        assertThat(words, hasItemInArray("sun"));
    }

    @Test
    public void testList() {
        List<Integer> list = Arrays.asList(5, 2, 4);
        assertThat(list, hasSize(3));
        assertThat(list, contains(5, 2, 4)); // именно в таком порядке
        assertThat(list, containsInAnyOrder(2, 4, 5));
        assertThat(list, everyItem(greaterThan(1)));
    }

    @Test
    public void stringListTest() {
        List<String> strings = Arrays.asList("Hell", "Hello");
        LambdaMatcher<String> lengthTester = new LambdaMatcher<>(
                s -> s.length() > 3
        );
        assertThat(
                strings,
                everyItem(
                        allOf( // для каждого элемента каждый из предикатов
                                startsWith("H"),
                                containsString("ll"),
                                lengthTester
                        )
                )
        );
    }

    @Test
    public void mapTest() {
        Map<String, Integer> people = new HashMap<>();
        people.put("Max", 23);
        people.put("Masha", 26);
        // проверьте что размер мапы равен 2
        assertEquals(
                "Testing people size",
                2,
                people.size()
        );
//        assertThat(people, hasSize(2)); // Matcher is only for Collections, Map is not a Collection

        // проверьте что есть запись "Max" -> 23
        assertSame( // Solution 1
                "Testing K:V pair",
                23,
                people.get("Max")
        );
        assertThat( // Option 2
                people,
                hasEntry("Max", 23)
        );

        // проверьте что есть запись с ключом Masha
        // проверьте что есть запись со значением 26
        assertThat(
                people,
                hasKey("Masha")
        );
        assertThat(
                people,
                hasValue(26)
        );

        // проверьте что все ключи начинаются с "Ma"
        assertThat(
                people.keySet(), // сделать Collection из ключей
                everyItem( // теперь можно использовать everyItem() для элементов Collection
                        startsWith("Ma")
                )
        );

        // убедитесь что все значения больше 20
        // и меньше 30
        assertThat(
                people.values(),
                allOf( // Every matcher
                        everyItem( // All items in Collection
                                greaterThan(20)
                        ),
                        everyItem( // All items in Collection
                                lessThan(30)
                        )
                )
        );
    }
}
