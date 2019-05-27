package GeneralTreeCode;

import Tree.TNode;

import java.util.ArrayList;
import java.util.Iterator;
//I don't see how the children have children b/c its TNode
//TNode is only an interface
//But it adds GTNode as TNode?
//So it does use GTNode


public class GTNode<T> implements TNode {
    T data;
    GTNode<T> parent;
    public ArrayList<TNode> children;

    public GTNode(T d, GTNode<T> p){
        parent = p;
        data = d;
        children = new ArrayList<TNode>();
    }

    //

    public Iterator<TNode> children(){
        return children.iterator();
    }
    public TNode getParent(){
        return parent;
    }
    public String printData(){
        return data.toString();
    }
    public void add(GTNode<T> n){
        children.add(n);
    }
    public void add(int i, GTNode<T> n){
        children.add(i,n);
    }
    public GTNode<T> get(int i){
        return (GTNode<T>) children.get(i);
    }
    public int numberChildren(){
        return children.size();
    }

    public int size(){
        int count = 1;
        Iterator<TNode> x = children();
        while(x.hasNext()){
            count +=((GTNode<T>) x.next()).size();
        }
        return count;
    }

    public void remove(GTNode<T> x){
        children.remove(x);
    }



}
