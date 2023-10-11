package lesson33.containers;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MapTester {
    public static void main(String[] args) {
//        Map<String, Integer> map = new HashMap<>(); // not Thread safe
//        Map<String, Integer> map = new Hashtable<String, Integer>(); // Thread safe, but slow
//        Map<String, Integer> map = new ConcurrentHashMap<>(); // Thread safe
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>()); // Thread safe wrapper

//        List<String> names = new ArrayList<>(); // not Thread safe
//        List<String> names = new Vector<>(); // Thread safe, but slow
        List<String> names = Collections.synchronizedList(new ArrayList<>()); // Thread safe wrapper

        Set<String> countries = Collections.synchronizedSet(new HashSet<>()); // Thread safe wrapper

        System.out.println(
                parallelAdd(map, 100)
        );
    }

    private static List<Integer> parallelAdd(Map<String, Integer> map, int times) {
        List<Integer> result = new ArrayList<>(); // times значений
        for (int i = 0; i < times; i++) {
            map.put("test", 0);
            ExecutorService service = Executors.newFixedThreadPool(4);
            for (int j = 0; j < 10; j++) {
                service.submit(
                        () -> {
                            for (int k = 0; k < 10; k++) {
                                map.computeIfPresent(
                                        "test",
                                        (key, value) -> value + 1
                                );
                            }
                        }
                );
            }
            service.shutdown();
            try {
                service.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            result.add(map.get("test"));
        }
        return result;
    }
}
