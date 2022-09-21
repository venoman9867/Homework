package homework_1;


public class Main {
    public static void main(String[] args) {
        MyMap<String, String> map = new MyHashMap<>();
        map.put("C", "C");
        map.put("A", "A");
        map.put("B", "B");
        Object[] objects=map.sort();
        for (Object key:objects){
            System.out.println(key);
        }
    }
}
