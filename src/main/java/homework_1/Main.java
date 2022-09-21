package homework_1;


public class Main {
    public static void main(String[] args) {
        MyMap<String, String> map = new MyHashMap<>();
        map.put("C", "Chicken");
        map.put("A", "Android");
        map.put("B", "Beta");
        //map.remove("C");
        System.out.println(map.get("C"));
        System.out.println(map.get(("A")));
        System.out.println(map.get("B"));
        map.removeAll();
        System.out.println(map.get("C"));
        System.out.println(map.get(("A")));
        System.out.println(map.get("B"));
    }
}
