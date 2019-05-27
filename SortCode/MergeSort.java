package SortCode;

public class MergeSort<K extends Comparable<K>> extends Sort<K> {

    public MergeSort(K[] x){
        //From the sort class
        super(x);
    }

    public int partition(int low, int high){

        return(low + high + 1)/2;
    }

    //here we just compare the arrays and merge them together
    public void merge(int low, int mid,int high){
        // create an array of the same size as the one you are compareing and sorting
        // Make sure that one is also comparable
        // low is usually 0. and high + 1 is usually the size of the array
        K m[] = (K[])new Comparable[high + 1 - low];

        //x starts at left side(index 0 to mid-1) of the array
        //y starts at right side(mid to last index) of the array
        int x = low, y  = mid, z = 0;

        //add to array until x is greater than mid.
        //code may be finished after that, if not add all values from the right side sorted
        while(x < mid){
            //if y is less than or equal to y
            //&& y is smaller than x
            //add array[y] to  m list
            if(y <= high && array[y].compareTo(array[x]) < 0){
                //increment y whenever you add from right side of array
                m[z++] = array[y++];
            }
            else{
                //increment x whenever you from left side of the array
                m[z++] = array[x++];
            }
        }
        //add the rest if you did not finish
        while(y <= high){
            m[z++] = array[y++];
        }
        //z is the end of the array


        //replaces values in the array you just sorted into m. use same index you start with
        for(int i = low; i <= high; i++){
            array[i] = m[i-low];
        }
    }

    public String sortType(){
        return "Merge Sort";
    }


}
