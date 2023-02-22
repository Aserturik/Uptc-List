package co.edu.uptc.model.dinamic;

import co.edu.uptc.pojos.Node;

import java.util.*;

public class UptcList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int countIt;

    public UptcList() {
        head = null;
        size = 0;
    }
    private boolean isHeadNull(){
        return (head == null);
    }

    @Override
    public void add(int index, T element) {
        Node node = new Node<>(element);

        if(index== 0){
            node.setNext(head);
            head = node;
        }else if(index<0 || index > size){
            throw new IndexOutOfBoundsException();
        }else {
            getNode(index - 1).setNext(new Node<>(element, getNode(index)));
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " ouObject of bounds for length " + size);
        }
        return getNode(index).getValue();
    }

    public Node<T> getNode(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " ouObject of bounds for length " + size);
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp;
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
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public T[] toArray() {
        T[] toArray = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            toArray[i] = getNode(i).getValue();
        }
        return toArray;
    }

    @Override
    public <T1>T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) toArray();
        }
        T1[] aux = (T1[]) new Object[a.length];
        for (int i = 0; i < size; i++) {
            aux[i] = (T1) getNode(i).getValue();
        }
        if (a.length > size) {
            for (int i = size; i < a.length; i++) {
                aux[i] = null;
            }
        }
        return aux;
    }

    @Override
    public boolean add(T element) {
        if(isHeadNull()){
            head = new Node<T>(element);
            tail = head;
        }else{
            tail.setNext(new Node<>(element));
            tail = tail.getNext();
        }
        countIt++;
        size ++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
        countIt++;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        if (element == null) throw new IllegalArgumentException("The element can not be null");
        if (isEmpty()) throw new IllegalStateException("The list is empty");
        Node<T> toReturn = getNode(index);
        if (index == 0) {
            head = new Node<>(element, toReturn.getNext());
        } else if (index == size - 1 || index == size) {
            if (index == size) toReturn = tail;
            getNode(index - 1).setNext(new Node<>(element));
        } else {
            getNode(index - 1).setNext(new Node<>(element, toReturn.getNext()));
        }
        countIt++;
        return toReturn.getValue();
    }

    @Override
    public T remove(int index) {
        Node<T> aux = head;
        if(index == 0){
            head = head.getNext();
        }else if(index > size-1 || index < 0){
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> tmp = getNode(index - 1);
            aux = tmp.getNext();
            if (aux.getNext() != null) {
                tmp.setNext(aux.getNext());
            } else {
                tmp.setNext(null);
            }
        }
        countIt++;
        size--;
        return aux.getValue();
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        Node<T> tmp = head;
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
        Node<T> tmp = head;
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
            private int countIterator = countIt;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next(){
                if (index == size) {
                    throw new NoSuchElementException("No next element");
                }
                if (index > size || countIt != countIterator) {
                    throw new ConcurrentModificationException();
                }
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
                private int countIterator = countIt;

                @Override
                public boolean hasNext() {
                    return getNode(i + 1) == null;
                }

                @Override
                public T next() {
                    if (countIt != countIterator) throw new ConcurrentModificationException();
                    if (i == size) throw new NoSuchElementException("No next element");
                    return getNode(i++).getValue();
                }

                @Override
                public boolean hasPrevious() {
                    if (i == 0) return false;
                    return getNode(i - 1) == null;
                }

                @Override
                public T previous() {
                    if (countIterator != countIt) throw new ConcurrentModificationException();
                    if (i == 0) throw new NoSuchElementException("No previous element");
                    return getNode(--i).getValue();
                }

                @Override
                public int nextIndex() {
                    return i;
                }

                @Override
                public int previousIndex() {
                    return i-1;
                }

                @Override
                public void remove() {
                    if (countIt != countIterator) throw new ConcurrentModificationException();
                    if (i > 0) UptcList.this.remove(--i);
                    else if (i == 0) UptcList.this.remove(i);
                    countIterator++;
                }

                @Override
                public void set(Object o) {
                    if (countIterator != countIt) throw new ConcurrentModificationException();
                    UptcList.this.set(i, (T) o);
                    countIterator++;
                }

                @Override
                public void add(Object o) {
                    UptcList.this.add(i++, (T) o);
                }
            };
        }
    }

    @Override
    public boolean addAll(Collection c) {
        if (c == null) throw new NullPointerException("Collection is null");
        if (c.size() == 0) return false;
        for (Object o : c) {
            add((T) o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (c == null) throw new NullPointerException("Collection is null");
        if (c.size() == 0) return false;
        if (index < 0 || index > size)throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        Node<T> node = getNode(index);
        tail = getNode(index-1);
        addAll(c);
        tail.setNext(node);
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c == null) throw new NullPointerException("Collection is null");
        if (c.size() == 0) return false;
        boolean changed = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(get(i))) {
                remove(i);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c == null) throw new NullPointerException("Collection is null");
        if (c.size() == 0 || c.size() > size) return false;
        boolean changed = false;
        for (Object obj : c) {
            for (int i = 0; i < size; i++) {
                if (obj.equals(get(i))) {
                    remove(obj);
                    changed = true;
                }
            }
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c.size() > size) return false;
        int count = 0;
        for (Object object : c) {
            for (int i = 0; i < size; i++) {
                if (get(i).equals(object) && count < c.size()) {
                    count++;
                } else if (count == c.size()) {
                    return true;
                }
            }
        }
        return false;
    }
}
