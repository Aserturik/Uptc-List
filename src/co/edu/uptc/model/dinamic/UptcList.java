package co.edu.uptc.model.dinamic;

import co.edu.uptc.pojos.Node;

import java.util.*;

public class UptcList implements List {
    private Node head;
    private int size;

    public UptcList() {
        head = null;
        size = 0;
    }
    public boolean isHeadNull(){
        return (head == null);
    }

    @Override
    public void add(int index, Object element) {
        Node node = new Node(element);

        if(index== 0){
            node.setNext(head);
            head = node;
        }else if(index<0 || index > size){
            throw new IndexOutOfBoundsException();
        }else {
            Node tmp = getNode(index-1);
            node.setNext(tmp.getNext());
            tmp.setNext(node);
            size++;
        }
    }

    @Override
    public Object get(int index) {
        Node tmp = head;
        if(index > size+1 || index < 0){
            throw new IndexOutOfBoundsException();
        }else {
            for (int i = 0; i < index; i++) {
                tmp = tmp.getNext();
            }
        }
        return tmp.getValue();
    }

    public Node getNode(int index){
        Node tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.getNext();
        }
        return tmp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
    }

    @Override
    public Object[] toArray() {
        Object[] objs = new Object[size];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = getNode(i).getValue();
        }
        return objs;
    }

    @Override
    public Object[] toArray(Object[] a) {
        Object[] arr;
        if(a.length <= size){
            arr = toArray();
        }else{
            arr = new Object[size+(a.length-size)];
            for (int i = 0; i < arr.length; i++) {
                if(i < size){
                    arr[i] = getNode(i).getValue();
                }else {
                    arr[i] = null;
                }
            }
        }
        return arr;
    }

    @Override
    public boolean add(Object element) {
        if(isHeadNull()){
            head = new Node(element);
        }else{
            getNode(size-1).setNext(new Node(element));
        }
        size ++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) != -1){
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public Object set(int index, Object element) {
        if(index == 0){
            if(isHeadNull()){
                throw new IllegalStateException();
            }else {
                head.setValue(element);
            }
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else{
            Node tmp = getNode(index-1);
            tmp.getNext().setValue(element);
        }
        return null;
    }

    @Override
    public Object remove(int index) {
        Node aux = head;
        if(index == 0){
            head = head.getNext();
        }else if(index > size-1 || index < 0){
            throw new IndexOutOfBoundsException();
        } else {
            Node tmp = getNode(index - 1);
            aux = tmp.getNext();
            if (aux.getNext() != null) {
                tmp.setNext(aux.getNext());
            } else {
                tmp.setNext(null);
            }
        }
        size--;
        return aux.getValue();
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        Node tmp = head;
        for (int i = 0; i < size; i++) {
            if(tmp.getValue() == o){
                index = i;
                break;
            }
            tmp = tmp.getNext();
        }
        return index;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        List list = new UptcList();
        if(fromIndex < 0 || toIndex > size-1){
            throw new IndexOutOfBoundsException();
        } else if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        } else {
            for (int i = fromIndex; i < toIndex; i++) {
                list.add(get(i));
            }
        }
        return list;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        Node tmp = head;
        for (int i = 0; i < size; i++) {
            if(tmp.getValue() == o){
                index = i;
            }
            tmp = tmp.getNext();
        }
        return index;
    }

    @Override
    public Iterator iterator() {
        Iterator iterator = new Iterator() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Object next(){
                return getNode(index++).getValue();
            }
        };
        return iterator;
    }

    @Override
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator listIterator(int index) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }else {
            return new ListIterator() {
                private int i = index;

                @Override
                public boolean hasNext() {
                    return getNode(i + 1) == null;
                }

                @Override
                public Object next() {
                    if((i+1 > size)){
                        throw new NoSuchElementException();
                    }
                    return getNode(i++).getValue();
                }

                @Override
                public boolean hasPrevious() {
                    if (i == 0) return false;
                    return getNode(i - 1) == null;
                }

                @Override
                public Object previous() {
                    return getNode(--i).getValue();
                }

                @Override
                public int nextIndex() {
                    return i + 1;
                }

                @Override
                public int previousIndex() {
                    return i - 1;
                }

                @Override
                public void remove() {
                    if (i > 0) UptcList.this.remove(--i);
                    else if (i == 0) UptcList.this.remove(i);
                }

                @Override
                public void set(Object o) {
                    UptcList.this.set(i, o);
                }

                @Override
                public void add(Object o) {
                    UptcList.this.add(i++, o);
                }
            };
        }
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] temp = c.toArray();
        boolean isNotNull;
        if(c.size() == 0){
            isNotNull = false;
        }else {
            for (int i = 0; i < temp.length; i++) {
                add(temp[i]);
            }
            isNotNull = true;
        }
        return isNotNull;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        Node aux = head;
        Node tempNode;
        int tempSize = size;
        boolean isAdd = false;
        if (index == 0) {
            tempNode = aux;
            clear();
            addAll(c);
            Node tmp = head;
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            tmp.setNext(tempNode);
            size += tempSize;
            isAdd = true;
        } else {
            aux = getNode(index - 1);
            tempNode = aux.getNext();
            aux.setNext(null);
            tempSize = size - (index);
            size = index;
            addAll(c);
            Node tmp = head;
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
            }
            tmp.setNext(tempNode);
            size += tempSize;
            isAdd = true;
        }
        return isAdd;
    }

    @Override
    public boolean retainAll(Collection c) {
        Object[] temp = c.toArray();
        Node aux = head;
        boolean contains = false;
        int count = 0;
        for (int i = 0; i <= size; i++) {
            contains = false;
            for (int j = 0; j < temp.length; j++) {
                if (aux.equals(temp[j])) {
                    aux = aux.getNext();
                    contains = true;
                    count++;
                }
            }
            if (contains == false) {
                aux = aux.getNext();
                remove(count);
                if (i != 0)
                    i -= 1;
            }
        }
        return contains;
    }

    @Override
    public boolean removeAll(Collection c) {
        Object[] temp = c.toArray();
        Node aux = head;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < temp.length; j++) {
                if (aux.equals(temp[j])) {
                    remove(i);
                    if (i != 0)
                        i -= 1;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] temp = c.toArray();
        Node aux = head;
        boolean contain = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < temp.length && contain == true; j++) {
                if (aux.equals(temp[j])) {
                    contain = true;
                } else {
                    contain = false;
                }
            }
        }
        return contain;
    }
}
