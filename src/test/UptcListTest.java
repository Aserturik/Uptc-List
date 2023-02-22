package test;

import co.edu.uptc.model.dinamic.UptcList;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class UptcListTest  {
    @Test
    public void testIsEmpty() {
        List list = new UptcList();
        assertTrue(list.isEmpty());
        list.add("aaa");
        assertFalse(list.isEmpty());
        list.add("aaa");
        list.remove(0);
        assertFalse(list.isEmpty());
        list.remove(0);
        assertTrue(list.isEmpty());
    }


    @Test
    public void testIndexOf() {
        List list = new UptcList();

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        Object tmp = list.get(2);

        assertEquals(list.indexOf("aaa"), 0);
        assertEquals(list.indexOf(tmp), 2);
        assertEquals(list.indexOf("xxx"), -1);
    }

    @Test
    public void testLastIndexOf() {
        List list = new UptcList();
        String a = "ddd";
        list.add("aaa");
        list.add(a);
        list.add("ccc");
        list.add(a);
        assertEquals(list.lastIndexOf(a), 3);
        list.remove(3);
        assertEquals(list.lastIndexOf(a), 1);
        assertEquals(list.lastIndexOf("xxx"), -1);
    }

    @Test
    public void testIterator() {
        // TODO VALUDAR CON UPTCLIST
        List list = new UptcList();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        Iterator it = list.iterator();

        int pos = 0;
        while (it.hasNext()) {
            assertEquals(it.next(), list.get(pos));
            pos++;
        }
        pos = 0;
        for (Object a : list) {
            assertEquals(a, list.get(pos));
            pos++;
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorRemoveException() {
        List list = new UptcList();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        Iterator it = list.iterator();
        it.next();
        it.next();
        list.remove(2);
        it.next();
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd() {
        List list = new UptcList();

        list.add("1");
        list.add("2");
        list.add("3");

        assertEquals(list.get(0), "1");
        assertEquals(list.get(1), "2");
        assertEquals(list.get(2), "3");
        list.get(-2);
    }

    @Test
    public void testAdd2() {
        List list = new UptcList();
        System.out.println("Start: " + new Date());
        //for(int i = 0;i<10050000;i++){
        for (int i = 0; i < 10050; i++) {
            list.add("value: " + i);   //6 seg
        }
        System.out.println("Finish: " + new Date());

        assertEquals(list.get(0), "value: 0");

    }

    @Test
    public void testAddIndex() {
        List list = new UptcList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(1, "a");
        assertEquals(list.get(1), "a");
        list.add(2, "b");
        assertEquals(list.get(2), "b");
        list.add(5, "c");
        assertEquals(list.get(5), "c");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexOut1() {
        List list = new UptcList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(10, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexOut2() {
        List list = new UptcList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(-1, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSize() {
        List list = new UptcList();
        assertEquals(list.size(), 0);

        for (int i = 0; i < 40; i++) {
            list.add("value: " + i);
        }
        assertEquals(list.size(), 40);
        list.remove(2);
        assertEquals(list.size(), 39);
        list.remove(38);
        assertEquals(list.size(), 38);
        list.remove(38);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDelete() {
        List list = new UptcList();
        list.add("1");
        list.add("2");
        list.add("3");
        assertEquals(list.size(), 3);
        Object aux = list.remove(0);
        assertEquals(aux, "1");
        assertEquals(list.size(), 2);
        assertEquals(list.get(1), "3");
        aux = list.remove(1);
        assertEquals(aux, "3");
        assertEquals(list.size(), 1);
        list.remove(-1);
        list.remove(10);
    }




    @Test
    public void testListIterator() {
        List list = new UptcList();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        ListIterator listIterator = list.listIterator();
        assertEquals(listIterator.nextIndex(), 0);
        assertEquals(listIterator.previousIndex(), -1);

        listIterator.next();
        listIterator.next();
        listIterator.next();
        assertEquals(listIterator.nextIndex(), 3);
        assertEquals(listIterator.previousIndex(), 2);
        listIterator.next();
        assertEquals(listIterator.nextIndex(), 4);
        assertEquals(listIterator.previousIndex(), 3);

        listIterator = list.listIterator();
        listIterator.next();
        listIterator.remove();
        assertEquals(listIterator.next(), "2222");
    }

    @Test
    public void testListIteratorRemove() {
        List list = new UptcList();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        ListIterator listIterator = list.listIterator();
        System.out.println(listIterator.next());
        // System.out.println( listIterator.next());
        System.out.println();
        listIterator.remove();
        System.out.println(listIterator.next());
        System.out.println();
        for (Object o : list) {
            System.out.println(o);
        }


    }
    @Test
    public void testListIteratorNextPrevious() {
        List list = new UptcList();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        ListIterator listIterator = list.listIterator();
        int pos = 0;
        while (listIterator.hasNext()) {
            Object aaa = listIterator.next();
            assertEquals(aaa, list.get(pos++));
        }
        pos = list.size();
        while (listIterator.hasPrevious()) {
            pos--;
            assertEquals(listIterator.previous(), list.get(pos));
        }

    }

    @Test
    public void testListIteratorAdd() {
        List list = new UptcList();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");

        ListIterator listIterator = list.listIterator();
        listIterator.add("n5555");
        listIterator.add("n6666");
        assertEquals(list.get(0), "n5555");
        assertEquals(list.get(1), "n6666");
        assertEquals(list.get(2), "1111");
        assertEquals(list.get(3), "2222");

    }

    @Test(expected = IllegalStateException.class)
    public void testListIteratorSetException(){
        List list = new UptcList();
        ListIterator listIterator = list.listIterator();
        listIterator.set("aaa");
        list = new UptcList();
        list.add("1111");
        listIterator = list.listIterator();
        listIterator.set("aaa");
    }

    @Test
    public void testListIteratorSet(){
        List list = new UptcList();
        list.add("1111");
        ListIterator listIterator = list.listIterator();
        listIterator.next();
        listIterator.set("aaa");

        list = new UptcList();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        listIterator = list.listIterator();
        listIterator.next();
        listIterator.set("aaa");

        listIterator.next();
        listIterator.set("bbb");
        listIterator.next();
        listIterator.set("ccc");

    }

    @Test(expected = NoSuchElementException.class)
    public void testListIteratorAddException() {
        List list = new UptcList();
        ListIterator listIterator = list.listIterator();
        listIterator.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testListIteratorIndex() {
        List list = new UptcList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        ListIterator listIterator = list.listIterator(2);
        assertEquals(listIterator.next(), "ccc");

        listIterator = list.listIterator(23);
        System.out.println(listIterator.next());
    }

    @Test
    public void testToArray() {
        List list = new UptcList();
        Object[] objs = list.toArray();
        assertEquals(objs.length, 0);
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        objs = list.toArray();
        assertEquals(objs.length, 3);
        System.out.println("objs 1= " + objs[1]);
        assertEquals(objs[2], "cccc");
    }
    @Test
    public void testToArrayArray() {
        List al = new UptcList();
        al.add("aa");
        al.add("bb");
        al.add("cc");
        al.add("dd");

        Object[] arr = {};
        //new Object[10];
        arr = al.toArray(new Object[2]);
        assertEquals(arr[0], "aa");
        assertEquals(arr[1], "bb");
        assertEquals(arr[2], "cc");
        assertEquals(arr[3], "dd");
        System.out.println("aqui pasa");

        arr = al.toArray(new Object[6]);
        assertEquals(arr[0], "aa");
        assertEquals(arr[1], "bb");
        assertEquals(arr[2], "cc");
        assertEquals(arr[3], "dd");
        assertEquals(arr[4], null);
        assertEquals(arr[5], null);
    }


    @Test
    public void testRemoveAllCollection() {
        List list = new UptcList();
        list.add("aaa");
        list.add("ccc");
        list.add("bbb");

        List list2 = new UptcList();
        list2.add("aaa1");
        list2.add("ccc1");

        assertFalse(list.removeAll(list2));

        list2 = new UptcList();
        list2.add("aaa");
        list2.add("ccc");
        assertTrue(list.removeAll(list2));

    }


    @Test
    public void testRetainAll() {
        List list = new UptcList();
        list.add("1");
        list.add("3");
        list.add("2");

        List list2 = new UptcList();
        list2.add("1");
        list2.add("2");
        assertTrue(list.retainAll(list2));
        assertEquals(list.size(),2);
        assertEquals(list.get(0),"1");

    }


    @Test
    public void testContains() {
        List list = new UptcList();
        list.add("1");
        list.add("2");
        list.add("3");
        assertFalse(list.contains("aux"));
        Object aux = list.get(1);
        assertTrue(list.contains(aux));
        assertTrue(list.contains("2"));
    }


    @Test
    public void testContainsAll() {
        List list = new UptcList();
        list.add("1");
        list.add("3");
        list.add("2");

        List list2 = new UptcList();
        list2.add("3");
        list2.add("1");
        list2.add("33");

        assertFalse(list.containsAll(list2));

        list2 = new UptcList();
        list2.add("3");
        list2.add("1");
        list2.add("3");
        assertTrue(list.containsAll(list2));

    }


    @Test
    public void testSubList(){
        List list = new UptcList();
        for (int i = 0; i < 10; i++) {
            list.add("aa:"+i);
        }
        List sublist =  list.subList(2,6);

        assertEquals(sublist.get(0),"aa:2");
        assertEquals(sublist.get(1),"aa:3");
        assertEquals(sublist.get(2),"aa:4");
        assertEquals(sublist.get(3),"aa:5");

        sublist =  list.subList(2,2);
        assertEquals(sublist.size(),0);
        assertThrows(IndexOutOfBoundsException.class, () ->list.subList(2,30));
        assertThrows(IllegalArgumentException.class,()->list.subList(5,3));

    }

    @Test
    public void testAddAll(){
        List list = new UptcList();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");

        List list1 = new UptcList();

        assertFalse(list.addAll(list1));

        list1.add("111");
        list1.add("222");
        list1.add("333");
        assertTrue(list.addAll(list1));

        assertEquals(list.size(),8);
        assertEquals(list.get(4),"ee");
        assertEquals(list.get(5),"111");
        assertEquals(list.get(6),"222");
        assertEquals(list.get(7),"333");
    }

}
