package Stack;

public interface Stack<T> {
    public T pop() throws Exception;
    public void push(T x) throws Exception;
    public boolean empty();
    public int size();
}
