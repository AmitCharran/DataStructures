package Stack;

import Node.Node;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;

    public LinkedStack(){
        top = null;
        size = 0;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public T pop() throws Exception {
        if(empty()){
            throw new Exception("Empty");
        }
        T ans = top.getData();
        top = top.getNext();
        size--;
        return ans;
    }

    @Override
    public void push(T x){
        Node<T> newTop = new Node<T>(x,top);
        top = newTop;
        size++;
    }
    public String toString(){
        StringBuilder ans = new StringBuilder("Linked Stack<T>: ");
        Node<T> n = top;
        while(n != null){
            ans.append(n.getData() + " --> ");
            n = n.getNext();
            //maybe add a code to check for loops
            //but maybe don't need to
        }
        return ans.toString();
    }


}
