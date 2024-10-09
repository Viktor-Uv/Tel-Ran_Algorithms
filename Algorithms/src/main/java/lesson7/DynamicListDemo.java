package lesson7;

public class DynamicListDemo {
    public static void main(String[] args) {
        DynamicList dynamicList = new DynamicList();
        dynamicList.push(12);
        dynamicList.push(8);
        dynamicList.push(5);
        dynamicList.push(2);
        System.out.println(dynamicList);
        for (int i = 0, n = dynamicList.getSize(); i < n; i++) {
            System.out.println(dynamicList.pop() + " <- " + dynamicList);
        }
        dynamicList.offer(12);
        dynamicList.offer(8);
        dynamicList.offer(5);
        dynamicList.offer(2);
        System.out.println(dynamicList);
    }
}