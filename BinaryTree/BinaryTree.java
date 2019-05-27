package BinaryTree;

import Tree.Tree;
import Tree.TNode;
import java.util.ArrayList;

public abstract class BinaryTree extends Tree {

    public BinaryTree(){
        super();
    }

    public void addRoot(BinaryNode node) throws Exception{
        //this is to initialize the tree
        //throws an error if you try to initialize twice
        if(root != null)
        {
            throw new Exception("The tree is not empty");
        }
        root = node;
        size++;
    }

    public void addLeft(BinaryNode node, BinaryNode newChild) throws Exception{
        if(node.getLeft() != null) throw new Exception("Left child already exists");
        node.setLeft(newChild);
        newChild.setParent(node);
        size++;
    }

    public void addRight(BinaryNode node, BinaryNode newChild) throws Exception{
        if(node.getRight() != null){
            throw new Exception("Right child already exists");
        }
        node.setRight(newChild);
        newChild.setParent(node);
        size++;
    }

    //removes a leaf but promotes and removes a descendant otherwise
    //returns the parent of the node that removed node
    public BinaryNode removeNode(BinaryNode node){
        if(isLeaf(node)){//base case
            BinaryNode parent = (BinaryNode) node.getParent();
            if(parent == null){
                root = null;
            }
            else{
                parent.removeChild(node);
            }
            size--;
            return parent;
        }
        BinaryNode lower = descendant(node);
        promote(lower, node);
        return removeNode(lower);
    }
    protected abstract void promote(BinaryNode lower, BinaryNode node);
    protected abstract BinaryNode descendant(BinaryNode node);

    public ArrayList<TNode> inOrder(){
        ArrayList<TNode> ans = new ArrayList<>();
        inOrder((BinaryNode)root(), ans);
        return ans;
    }
    public void inOrder(BinaryNode node, ArrayList<TNode> v){
        if(node == null) return;

        inOrder(node.getLeft(), v);
        v.add(node);
        inOrder(node.getRight(), v);
    }

    public ArrayList<TNode> flatOrder(){
        return inOrder();
    }

}
