package Stack;

public class ArrayStack<T> implements Stack<T> {
    private  T data[];
    private int top;

    public ArrayStack(){
        data = (T[]) new Object[1000];
        top = -1;
    }

    public int size(){
        return top + 1;
    }
    public boolean empty(){
        return top == -1;
    }
    public void push(T x) throws Exception{
        if(size() == 1000){
            throw new Exception("Stack Full");
        }
        data[++top] = x;
    }
    public T pop() throws Exception{
        if(empty()){
            throw new Exception("Stack Empty");
        }
        return data[top--];
    }

    public String toString(){
        StringBuilder ans = new StringBuilder("Array Stack<T>: ");
        for(int i = top; i >= 0; i--){
            ans.append(data[i] + " -> ");
        }
        return ans.toString();
    }




}
