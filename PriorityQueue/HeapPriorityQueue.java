package PriorityQueue;

import java.util.Iterator;

//priority queue but in a heap data structure
//heap data is a (nearly)complete binary tree and satisfies the heap property
//remove is log(n), add is also log(n)
public class HeapPriorityQueue<K extends Comparable<K>> implements PriorityQueue<K> {
    private K data[];
    private int size;
    private int capacity;

    //constructor
    public HeapPriorityQueue(){
        capacity = 100;
        size = 0;
        data= (K[]) new Comparable[capacity];
    }

    public HeapPriorityQueue(int c){
        capacity = c;
        size = 0;
        data = (K[])new Comparable[c];
    }

    public void add(K x) throws Exception{
        if(size >= capacity - 1){
            throw new Exception("Priority Queue Full");
        }
        data[size++] = x;
        bubbleUp(size - 1);
    }

    public K removeMin() throws Exception{
        if(size <= 0) {
            throw new Exception("Priority Queue Empty");
        }
        swapData(0, --size);
        bubbleDown(0);
        return data[size];
    }

    public void swapData(int n, int m){
        K temp = data[n];
        data[n] = data[m];
        data[m] = temp;
    }

    //int n is the index
    private void bubbleUp(int n){
        if(n <= 0) return;//at root

        K dn = data[n]; //node data
        K dp = data[(n - 1)/2]; //parent data

        //if current node is greater than its parent,everything is good
        if(dn.compareTo(dp) >= 0){
            return;
        }

        //if current node is not greater than it's parent, swap the nodes
        //and call bubble up with the parents node
        //that node(parent node) might need to be replaced with it's parent
        swapData(n, (n-1)/2);
        bubbleUp((n-1) / 2);
    }

    public void bubbleDown(int n){
        if( 2 * n + 1 >= size){
            return;//is leaf
        }

        K dn = data[n]; //data of node
        K dl = data[2*n+1];//left child

        K dr = dl;//it is not right child(it's left child) but we need to initialize it for comparison

        //This is where we get the actual right child if it is there
        if(2*n+2 < size){
            dr = data[2*n+2]; //right child
        }

        //If both right child and left child are greater than the parent, then everything is good
        if(dn.compareTo(dl) < 0 && dn.compareTo(dr) < 0){
            return;
        }

        //Swap the smaller of the two. Swap the smaller one with the node(it's parent)
        //if dr == dl, the code still works
        if(dr.compareTo(dl)< 0){
            swapData(n, 2*n+2);
            bubbleDown(2*n+2);
        }
        else{
            swapData(n, 2*n+1);
            bubbleDown(2*n+1);
        }
    }

    //heap creation
    public void heapify(Iterator<K> x) throws Exception{
        //Grab all the elements and add them into our array
        //If needed, stop right before capacity is filled
        while(x.hasNext()){
            if(size + 1 == capacity){
                break;
            }
            data[size++] = x.next();
        }
        int n= size/2;

        //Sort the array in our min heap priority structure
        while(--n >= 0){
            bubbleDown(n);
        }

        if(x.hasNext()){
            throw new Exception("Heap is Full");
        }
    }

}
