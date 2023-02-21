package co.edu.uptc.model.vector;

import co.edu.uptc.model.dinamic.UptcList;

import java.util.*;

public class VectorList implements List{
    Object vector[] = {};
    @Override
    public int size() {
        return vector.length;
    }
    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < vector.length; i++) {
            if(vector[i] == o){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < vector.length; i++) {
            if(vector[i] == o){
                index = i;
            }
        }
        return index;
    }

    @Override
    public boolean isEmpty() {
        return (vector.length == 0);
    }

    @Override
    public boolean contains(Object o) {
        return (indexOf(o) != -1);
    }

    @Override
    public Object[] toArray() {
        return vector;
    }

    @Override
    public boolean add(Object o) {
        Object tmp[] = new Object[vector.length +1];
        for (int i = 0; i < vector.length; i++) {
            tmp[i] = vector[i];
        }
        tmp[vector.length] = o;
        vector = tmp;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        int a = indexOf(o);
        remove(a);
        return false;
    }

    @Override
    public void clear() {
        Object[] aux = {};
        vector = aux;
    }

    @Override
    public Object get(int index) {
        return vector[index];
    }

    @Override
    public Object set(int index, Object element) {
        vector[index] = element;
        return null;
    }

    @Override
    public void add(int index, Object element) {
        Object tmp[] = new Object[vector.length+1];
        for (int i = 0; i < index; i++) {
            tmp[i] = vector[i];
        }

        for (int i = index+1; i < vector.length+1; i++) {
            tmp[i] = vector[i];
        }
    }

    @Override
    public Object remove(int index) {
        Object aux[] = new Object[vector.length -1];
        for (int i = 0; i < aux.length ; i++) {
            if(i < index){
                aux[i] = vector[i];
            } else if (i == index || i > index) {
                aux[i] = vector[i+1];
            }
        }
        vector = aux;
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        List aux = new UptcList();
        for (int i = fromIndex; i < toIndex; i++) {
            aux.add(vector[i]);
        }
        return aux;
    }

    @Override
    public Iterator iterator() {
        Iterator iterator = new Iterator() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return (get(index+1) == null);
            }

            @Override
            public Object next() {
                return vector[index++];
            }
        };
        return iterator;
    }

    // iterator y listIterator aun por testear
    @Override
    public ListIterator listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator listIterator(int index) {
        return new ListIterator() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return (get(index+1) == null);
            }

            @Override
            public Object next() {
                return vector[index++];
            }

            @Override
            public boolean hasPrevious() {
                return (get(index-1) == null);
            }

            @Override
            public Object previous() {
                if (index == 0) {
                    throw new NoSuchElementException("No previous element");
                }
                return vector[index--];
            }

            @Override
            public int nextIndex() {
                return index+1;
            }

            @Override
            public int previousIndex() {
                return index-1;
            }

            @Override
            public void remove() {
                VectorList.this.remove(index);
            }

            @Override
            public void set(Object o) {
                VectorList.this.set(index, o);
            }

            @Override
            public void add(Object o) {
                VectorList.this.add(index, o);
            }
        };
    }

    @Override
    public Object[] toArray(Object[] a) {
        return vector;
    }

    // ---------------------------------------------------------

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }
    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }
}
