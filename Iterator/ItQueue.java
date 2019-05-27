package Iterator;

import java.util.Iterator;

import Node.Node;
import Queue.LinkedQueue;
import Queue.Queue;

public class ItQueue<T> extends LinkedQueue<T> implements Queue<T>,Iterable<T> {
    public Iterator<T> iterator() {
        return new QueueIterator<T>(front);
    }

    public String toString() {
        String ans = "ItQueue<T>: ";
        for (T x : this)
            ans += x + " ";
        return ans;
    }

}

class QueueIterator<T> implements Iterator<T> {
    private Node<T> current;

    public QueueIterator(Node<T> c) {
        current = c;
    }

    public T next() {
        T answer = current.getData();
        current = current.getNext();
        return answer;
    }

    public boolean hasNext() {
        return current != null;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}