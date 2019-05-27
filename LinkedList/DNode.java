package LinkedList;

public class DNode<T> {
    private T data;
    private  DNode<T> prev,next;

    public DNode(T d, DNode<T> p, DNode<T> n){
        data = d;
        next = n;
        prev = p;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DNode<T> prev) {
        this.prev = prev;
    }

    public DNode<T> getNext() {
        return next;
    }

    public void setNext(DNode<T> next) {
        this.next = next;
    }
}
