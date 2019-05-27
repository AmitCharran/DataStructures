package Iterator;

import Stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

//This the same as array stack but it has an iterable inner class
public class ItStack<T> implements Stack<T>, Iterable<T> {

    T[] data;
    int top;

    @SuppressWarnings("unchecked")
    public ItStack() {
        data = (T[]) new Object[1000];
        top = -1;
    }

    public int size() {
        return top + 1;
    }

    public boolean empty() {
        return top == -1;
    }

    public void push(T x) throws Exception {
        if (size() == 1000)
            throw new Exception("Stack is full");
        data[++top] = x;
    }

    public T pop() throws Exception {
        if (empty())
            throw new Exception("Stack is empty!");
        return data[top--];
    }

    public Iterator<T> iterator() {
        return new StackIterator(top);
    }

    public String toString() {
        StringBuilder ans = new StringBuilder("ItStack<T>: ");
        for (T x : data)
            ans.append(x + " ");
        return ans.toString();
    }


    //This is the Stack inner class
    private class StackIterator implements Iterator<T> {

        private int n;

        public StackIterator(int top) {
            n = top + 1;
        }

        public boolean hasNext() {

            return 0 <= n;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            n = n - 1;
            return data[n];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
