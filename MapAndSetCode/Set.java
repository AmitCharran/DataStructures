package MapAndSetCode;

import java.util.Iterator;

//Difference between Set and Map... Map has two elements K, V. Key and Value
//Set only has one element Entry
//but... in these cases Entry has a K, and V as well
public interface Set<E> extends Iterable<E> {
    public int size();

    public boolean isEmpty();

    public E get(E k);

    public void add(E k) throws Exception;

    public void remove(E k) throws Exception;

    public Iterator<E> iterator();

    public void dumpData();

}
