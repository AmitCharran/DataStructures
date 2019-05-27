package Node;

public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(){
        data = null;
        next = null;
    }
    public Node (T data, Node<T> node){
        this.data = data;
        this.next = node;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    //Print Method
    public String toString(){
        Node<T> seenBefore = this; //to check for loops
        StringBuilder ans = new StringBuilder("");
        int count = 0;
        Node<T> node = this;
        while(node != null){
            ans.append(node.getData());
            ans.append(" --> ");
            node = node.getNext();

            //Checks if we are in a loop
            //This moves half speed while the actual while loop moves full speed
            //*speed means jumping from one node to the next
            if(++count % 2 == 0) seenBefore = seenBefore.getNext();
            if(node == seenBefore){
                ans.append("...");
                break;
            }
        }
        return ans.toString() + " = ";
    }

}
