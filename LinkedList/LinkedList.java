package LinkedList;

import Node.Node;

//So in this we have a node with only next
//But you can add from the end and the beginning. Hence head and tail
public class LinkedList<T> {
    private Node<T> head, tail;
    private int size;

    public LinkedList(){
        head = tail = null;
        size = 0;
    }

    //utility methods
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    //methods to change the list
    public void addHead(T d){
        Node<T> n = new Node<T>(d, head);
        head = n;
        size++;
        if(tail == null){
            tail = head;
        }
    }
    public void addTail(T d){
        Node<T> n = new Node<T>(d, null);
        if(tail == null) head = tail = n;
            else{
                tail.setNext(n);
                tail = n;
        }
            size++;
    }

    public T removeHead() throws Exception{
        if(head == null){
            throw new Exception("Empty List");
        }
        Node<T> n =  head;
        head = head.getNext();
        if(head == null){
            tail = null;
        }
        size--;
        return n.getData();
    }

    public String toString(){
        StringBuilder ans= new StringBuilder("");
        Node<T> n = head;
        Node<T> seenBefore = head;
        int counter = 0;
        ans.append("(H) --> ");
        while(n != null){
            ans.append(n.getData() + "");
            ans.append(" --> ");
            n = n.getNext();
            if(++counter%2 == 0){
                seenBefore = n.getNext();
            }
            if(n == seenBefore){
                ans.append(" ... Stuck in a loop");
                return ans.toString();
            }
        }
        ans.append("(T)");
        return ans.toString();
    }
}
