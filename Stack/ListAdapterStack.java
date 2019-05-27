package Stack;

import LinkedList.LinkedList;

public class ListAdapterStack<T> implements Stack<T> {
    private LinkedList<T> l;

    public ListAdapterStack(){
        l = new LinkedList<T>();
    }

    public int size(){
        return l.size();
    }

    public boolean empty(){
        return l.isEmpty();
    }
    public void push(T d){
        l.addHead(d);
    }
    public T pop() throws Exception{
        return l.removeHead();
    }
    public String toString(){
        return l.toString();
    }

}
