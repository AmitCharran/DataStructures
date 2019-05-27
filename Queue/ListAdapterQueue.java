package Queue;

import LinkedList.LinkedList;
import Stack.ListAdapterStack;

public class ListAdapterQueue<T> implements Queue<T>{
    private LinkedList<T> l;

    public ListAdapterQueue(){
        l = new LinkedList<T>();
    }
    public int size(){
        return l.size();
    }
    public boolean empty(){
        return l.isEmpty();
    }
    public void enqueue(T d){
        l.addTail(d);
    }
    public T dequeue() throws Exception{
        return l.removeHead();
    }
    public String toString(){
        return l.toString();
    }
}
