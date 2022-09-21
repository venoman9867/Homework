package homework_1;

public interface MyMap<K,V> extends Iterable<V>{
   //Методы необходимые по условию задачи
   Object put(K key, V value);//добавление
   V get(K key);//получить значение по ключу
   void remove(K key);//удалить значение по ключу
   void removeAll();//удалить все
   Object[] sort();//вывести отсортированный массив по ключу
   int size();//размер
   boolean isEmpty();

   //внутренний интерфейс, пара элементов ключ-значение
   interface Entry<K,V>{
      K getKey();
      V getValue();
   }
}
