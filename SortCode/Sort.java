package SortCode;

//Divide and conquer algorithms (in arrays)
public abstract class Sort<K extends Comparable<K>> {
    public K array[];
    public int capacity;

    public Sort(K []x){
        array = x;
        capacity = array.length;
    }

    public void sort(int low, int high){

        if(low >= high) {
            return;
        }
        //partition is divide
        //diving the array into two parts
        int mid = partition(low, high);

        //this is the recursive part
        sort(low, mid-1); //left side of the array

        //this is the recursive part
        sort(mid, high);  //right side of the array

        merge(low,mid,high);
    }

    //in this abstract class these are the abstract methods
    //that will make all the merges different
    //Notice it all has partitions
    public abstract int partition(int low, int high);

    public abstract void merge(int low, int mid, int high);

    public abstract  String sortType();

    public void print(){
        for(int i = 0; i < capacity; i++){
            System.out.println(" " + array[i] + " ");
        }
        System.out.println();
    }



}
