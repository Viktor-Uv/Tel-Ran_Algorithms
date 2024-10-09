package lesson15.hashmap;

public class MyHashMapTester {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put("Egypt", "Cairo");
        map.put("Sweden", "Stockholm");
        map.put("Norway", "Oslo");
        map.put("Australia", "Canberra");
        map.put("Canada", "Ottawa");
        System.out.println(map);
        System.out.println(map.size()); // 5
        System.out.println(map.get("Canada")); // Ottawa
        System.out.println(map.get("USA")); // null
        System.out.println(map);
        System.out.println("Remove Egypt=" + map.remove("Egypt") + " <- " + map);
        System.out.println(map.size()); // 4
        System.out.println(map.contains("Norway")); // true
        System.out.println(map.contains("Ireland")); // false
    }
}
