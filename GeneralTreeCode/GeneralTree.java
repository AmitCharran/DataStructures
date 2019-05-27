package GeneralTreeCode;

import Tree.Tree;

public class GeneralTree<T> extends Tree {

    //read comments in GTNode.java
    //I think this is how GTNode connects with general tree.
    //here we have GTNodes with children as GTNode
    public GeneralTree(){
        super();
    }
    public void add(T x){
        //this is to initialize the root
        //cannot initialize the root twice
        if(root != null){
            throw new RuntimeException();
        }
        root = new GTNode<T>(x, null);
        size++;
    }

    public void add(GTNode<T> position, T data){
        //Remember GTNode adds to children from(an ArrayList) the class GTNode
        position.add(new GTNode<T>(data,position));
        size++;
    }

    public void add(GTNode<T> position, int i, T data){
        if(position.numberChildren() < i || i < 0){
            throw new RuntimeException("Illegal Index within node");
        }
        position.add(i, new GTNode<T>(data,position));
        size++;
    }

    public GTNode<T> remove(GTNode<T> node){
        GTNode<T> parent = (GTNode<T>) node.getParent();
        //remove all of its children also. That's why size is like that
        size -= node.size();
        if(parent == null){
            root = null;
        }else {
            parent.remove(node);
        }
        return parent;
    }

}
