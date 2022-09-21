package homework_1;

import java.util.Arrays;
import java.util.Iterator;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private final int DEFAULT_INITIAL_CAPACITY = 16;// дефолтный размер массива
    private final float DEFAULT_LOAD_FACTOR = 0.75f;// значение определяющее когда нужно расширить hashmap
    Node[] table = new Node[DEFAULT_INITIAL_CAPACITY];// сам массив
    private int size = 0;

    @Override
    public Object put(K key, V value) {
        //вычисляем хэш значение нашего ключа
        int hashValue = hash(key);
        //потом место где он должен храниться
        int i = indexFor(hashValue, table.length);
        //если в ячейке уже есть данные и ключ тот же перезаписываем
        for (Node node = table[i]; node != null; node = node.next) {
            Object k;
            if (node.hash == hashValue && ((k = node.key) == key || key.equals(k))) {
                Object oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        }
        // Если в позиции i нет данных или есть данные в позиции i, но ключ - это новый ключ, добавьте узел
        addEntry(key, value, hashValue, i);
        return null;
    }


    @Override
    public Object get(Object key) {
        // Вычисляем значение хеша по ключу
        int hashValue = hash(key);
        // По значению хеша и длине связанного списка получаем индекс позиции вставки
        int i = indexFor(hashValue, table.length);
        for (Node node = table[i]; node != null; node = node.next) {
            if (node.key.equals(key) && hashValue == node.hash) {
                return node.value;
            }
        }
        return null;
    }


    // Добавить элементы в Entry
    // hashvalue --- значение хеша
    // я --- индексная позиция
    public void addEntry(Object key, Object value, int hashValue, int i) {
        // Если согласованная длина массива превышена, расширяем емкость
        if (++size >= table.length * DEFAULT_LOAD_FACTOR) {
            Node[] newTable = new Node[table.length << 1];
            // копировать массив
            //System.arraycopy(table,0,newTable,0,table.length);
            transfer(table, newTable);
            table = newTable;
        }
        // получить данные в i
        Node eNode = table[i];
        // Добавить узел, указать узел рядом с предыдущим узлом
        table[i] = new Node(hashValue, key, value, eNode);
    }

    public void transfer(Node[] src, Node[] newTable) {// src ссылается на старый массив Entry
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {// пройти старый массив Entry
            Node e = src[j]; // Получить каждый элемент старого массива Entry
            if (e != null) {
                src[j] = null; // Освободить ссылку на объект старого массива Entry (после цикла for старый массив Entry больше не ссылается ни на какие объекты)
                do {
                    Node next = e.next;
                    int i = indexFor(e.hash, newCapacity); //! ! Пересчитать положение каждого элемента в массиве
                    e.next = newTable[i]; // Mark [1]
                    newTable[i] = e; // Поместить элемент в массив
                    e = next; // Доступ к элементам в следующей цепочке ввода
                } while (e != null);
            }
        }
    }

    @Override
    public void removeAll() {
        MyHashMap.Node[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }

    @Override
    public void remove(K key) {
        //тут надо вычислить хеш по кею удалить узел с таким же хеш кодом
        // и связать узлы которые оборвались если они есть
        //наверное еще проверить по equals вдруг там разные объекты с одинаковым хешкодом
    }

    @Override
    public Object[] sort() {
        Object[] array = new Object[table.length];
        for (int i = 0; i < table.length; i++) {
            array[i] = table[i].getKey();//метод выдает ошибку на этой строке потому что я пытаюсь по всему массиву пройтись
            //когда у меня значения кладутся лишь в некоторые ячейки по хешкоду NullPointerExepction
        }
        Arrays.sort(array);
        return array;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    //хеш-функция вычисляющая позицию в массиве для вставки
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    //вычисляем в какую ячейку массива положим ноду
    public int indexFor(int hashValue, int length) {
        return hashValue % length;
    }

    //это наш узел
    static class Node implements MyMap.Entry {

        int hash;// тут есть хэш значение
        Object key;// есть ключ
        Object value;//есть значение
        Node next;// ссылка на следующий узел

        public Node(int hash, Object key, Object value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //метод для получения ключа
        @Override
        public Object getKey() {
            return key;
        }

        //метод для получения значения
        @Override
        public Object getValue() {
            return value;
        }
    }

}

