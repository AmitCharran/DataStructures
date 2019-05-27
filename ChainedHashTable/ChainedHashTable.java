package ChainedHashTable;

import MapAndSetCode.Set;
import java.util.ArrayList;
import java.util.Iterator;

//Use this kind of structure when you use RADIX sort
public class ChainedHashTable<E> implements Set<E> {
    int size;
    int capacity;
    ArrayList<E> bucket[]; //ArrayList inside an Array

    ChainedHashTable(){
        this(1000);
    }
    public ChainedHashTable(int cap){
        capacity = cap;
        bucket = (ArrayList<E>[]) new ArrayList[cap];
        size = 0;
        for(int i = 0;i < cap; i++){
            bucket[i] = null; //initialized all of the ArrayList to null
        }
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public E get(E k){
        int h = k.hashCode()% capacity;

        //h is the hash index
        if(bucket[h] != null){
            for(int i = 0; i < bucket[h].size(); i++){
                E p = bucket[h].get(i); //p == the values in the bucket
                    if(p.equals(k)){
                        return p;
                    }//compare that value(p) to k, and if it is there. return it
            }
            //this is contains function but returns the function
        }
        return null; //if you can't find k
    }

    public boolean contains(E k){
        return get(k) != null;
    }

    public void add(E k){
        int h = k.hashCode() % capacity;
        if(bucket[h] == null){
            bucket[h] = new ArrayList<>();
        }
        //if value is already in here, rewrite it
        //this can be a new value
        for(int i =0; i < bucket[h].size(); i++){
            E p = bucket[h].get(i);
            if(p.equals(k)){
                bucket[h].set(i,k);
                return;
            }
        }
        bucket[h].add(k);
        size++;
    }

    public void remove(E k){
        int h = k.hashCode()%capacity;
        if(bucket[h] != null){
            for(int i = 0; i < bucket[h].size(); i++)
            {
                //search the arraylist that corresponds with the hashcode and if you find a match remove it
                //also decrement size
                E p = bucket[h].get(i);
                if(p.equals(k)){
                    bucket[h].remove(i);
                    size--;
                    return;
                }
            }
        }
    }

    //So check in the array if there is an non-Empty ArrayList. Print that non-empty ArrayList
    //Then check next element in the array
    public void dumpData(){
        //go through every element and look for a value, that's why its i< capacity
        for(int i = 0; i < capacity; i++){
            //if bucket[i] == null go to next element..,
            if(bucket[i] != null){
                System.out.println("" + i + ": ");

                //this is printing from the array list
                for(int j = 0; j < bucket[i].size(); j++){
                    E p = bucket[i].get(j);
                    System.out.print(p);
                    if(j < bucket[i].size() - 1) System.out.print(", ");
                }
                System.out.println();
            }
        }

    }

    //Same process as dumpData but put everything in one ArrayList
    //return ArrayList.iterator()
    public Iterator<E> iterator(){
        ArrayList<E> content= new ArrayList<E>();
        for(int i = 0; i < capacity; i++){
            if(bucket[i] != null){
                for(int j = 0; j < bucket[i].size();j++){
                    content.add(bucket[i].get(j));
                }
            }
        }
        return content.iterator();
    }

}
