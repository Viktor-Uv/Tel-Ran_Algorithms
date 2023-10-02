package lesson29;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.*;

public class Homework14Test {
    private static Homework14.MyGenericLinkedList<Object> list = new Homework14.MyGenericLinkedList<>();

    @BeforeClass
    public static void init() {
        list.add("one");
        list.add(2);
        list.add(3.0);
        list.add(true);
    }

    @Before
    public void prep() {
        if (!list.contains("one")) {
            list.add("one");
        }
    }

    @Test
    public void testSize() {
        System.out.println("Testing .size()");
        Homework14.MyGenericLinkedList<Object> list2 = new Homework14.MyGenericLinkedList<>();
        assertEquals(
                0,
                list2.size()
        );

        list2.add('a');
        list2.add("2");
        assertEquals(
                2,
                list2.size()
        );
        list2.remove(0);
        assertEquals(
                1,
                list2.size()
        );
    }

    @Test
    public void testContains() {
        System.out.println("Testing .contains(T)");
        assertTrue(
                list.contains("one")
        );
    }

    @Test
    public void testSet() {
        System.out.println("Testing .set(int, T)");
        int sizeBefore = list.size();
        list.set(2, 3.14f);
        assertEquals(
                3.14f,
                list.get(2)
        );
        assertEquals(
                sizeBefore,
                list.size()
        );
    }

    @Test
    public void testAdd() {
        System.out.println("Testing .add(T)");
        int sizeBefore = list.size();
        Object newObject = new Object();
        list.add(newObject);
        assertThat(
                list.get(list.size() - 1),
                is(newObject)
        );
        assertThat(list.size(), equalTo(sizeBefore + 1));
    }

    @Test
    public void testAddByIndex() {
        System.out.println("Testing .add(int, T)");
        int sizeBefore = list.size();
        Object oldObject = list.get(0);
        Object newObject = new String("one");
        list.add(0, newObject);
        assertThat(
                list.get(0),
                is(newObject)
        );
        assertThat(
                list.get(1),
                is(oldObject)
        );
        assertThat(list.size(), equalTo(sizeBefore + 1));
    }

    @Test
    public void testRemove() {
        System.out.println("Testing .remove(int)");
        Object toRemove = list.get(0);
        int sizeBefore = list.size();
        list.remove(0);
        assertFalse(list.contains(toRemove));
        assertEquals(
                sizeBefore - 1,
                list.size()
        );
    }

    @Test
    public void testGet() {
        System.out.println("Testing .get(int)");
        Object newObject = new int[]{1, 2, 3};
        list.add(0, newObject);
        assertThat(
                list.get(0),
                is(newObject)
        );
    }

    @Test
    public void testToString() {
        list.add(4);
        assertThat(
                list,
                hasToString(list.toString())
        );
        assertThat(
                list.toString(),
                allOf(
                        startsWith("["),
                        containsString(", "),
                        endsWith("]")
                )
        );
    }
}
