package Queue;

public class ArrayQueue<T> implements Queue<T> {
    private T data[];
    private int front,rear,size,capacity;
    //front and rear is to wrap around the array

    public ArrayQueue(){
        capacity = 1000;
        data = (T[]) new Object[capacity];
        front = rear = size = 0;
    }
    public ArrayQueue(int c){
        capacity = c;
        data = (T[]) new Object[capacity];
        front = rear = size = 0;
    }
    public int size(){
        return size;
    }

    public boolean empty(){
        return size == 0;
    }

    public void enqueue(T x) throws Exception{
        if(size() == capacity){
            throw new Exception("Queue full");
        }
        data[rear++] = x;
        if(rear == capacity){
            rear = 0;
        }
        size++;
    }

    public T dequeue() throws Exception{
        if(empty()){
            throw new Exception("Queue Empty");
        }
        T answer = (T)data[front++];
        if(front == capacity){
            front = 0;
        }
        size--;
        return answer;
    }

    public String toString(){
        int i, j;
        StringBuilder ans = new StringBuilder("Array Queue<T>: ");
        for(i= 0,j=front;i<size;i++,j++ ){
            if(j == capacity){
                j = 0;
            }
            ans.append(data[j] + " -> ");
        }
        return ans.toString();
    }


}
