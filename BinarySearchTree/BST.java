package BinarySearchTree;

import BinaryTree.BinNode;
import BinaryTree.BinTree;
import BinaryTree.BinaryNode;
import Tree.TNode;

import java.util.ArrayList;
import java.util.Iterator;


//Binary Search Tree, so with this stucture, if the number you are adding is smaller
// than the node it will go left of the node and if it larger it will go right of the node

//pay attention to the descendant/successor method because it is seems to be different than AVLTrees.
public class BST<K extends Comparable<K>> extends BinTree<K> {

    public BinNode<K> findNode(K target){
        if(root() == null){
            return null;
        }
        return recursiveFindNode((BinNode<K>) root(), target);
    }

    //either we find the node containing the target or the future parent of a Node that would contain the target
    //by future parent it means, the node the target will be the child of
    //meaning this is used for the add/remove function (findNode at least)
    private BinNode<K> recursiveFindNode(BinNode<K> node, K target) {
        int comparison = target.compareTo(node.getData());
        if(comparison == 0) return node;
        if(comparison < 0 && node.getLeft()!= null){
            return recursiveFindNode((BinNode<K>) node.getLeft(),target);
        }
        if(comparison > 0 && node.getRight() != null){
            return recursiveFindNode((BinNode<K>)node.getRight(),target);
        }
        return node;
    }

    public void remove(K target) throws Exception{
        BinNode<K> n = findNode(target);
        if(n == null || !n.getData().equals(target)){
            throw new Exception("Target not present");
        }
        //This function is from the binaryNode class
        //Note: there will be a different implementation of descendant/successor in this class
        removeNode(n);
    }

    public void add(K newData)throws Exception{
        BinNode<K> node = findNode(newData);
        //***Find node will find the node you will add child too
        if(node == null){
         addRoot(newData);
        }
        else if((node.getData()).compareTo(newData) > 0){
            //addLeft is node you're adding is smaller than the node you are looking at
            addLeft(node,newData);
        }
        else if((node.getData()).compareTo(newData) < 0){
            addRight(node,newData);
        }
        else {
            node.setData(newData);
        }
    }

    public boolean contains(K target){
        BinNode<K> node = findNode(target);
        if(node == null || !node.getData().equals(target)){
            return false;
        }
        return true;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public K get(K partialData){
        BinNode<K> node = findNode(partialData);
            if(node == null || !node.getData().equals(partialData)){
                return null;
            }
            return node.getData();
    }

    public Iterator<K> iterator(){
        ArrayList<TNode> list = inOrder();
        ArrayList<K> dataList = new ArrayList<K>();
        for(TNode node: list) dataList.add(((BinNode<K>) node).getData());
        return dataList.iterator();
    }

    public void dumpData(){
        System.out.println(treePrint(null));
    }

    // when removing from a BST, we can only promote from a neighbor in order
    @Override
    protected BinaryNode descendant(BinaryNode node){
        BinaryNode lower = node.getLeft();

        if(lower != null) {
            //Notice this is a while loop
            //so it goes to the left to get the lower number than itself
            //then it goes all the way to the right, to get the highest lower number
            //So basically that number thats closest to it but lower
            while (lower.getRight() != null) {
                lower = lower.getRight();
            }
            return lower;
        }
            //this should not be lower, it should be "higher but closest value to it"
            lower = node.getRight();
            if(lower != null){
                while(lower.getLeft() != null){
                        lower = lower.getLeft();
                    }
                return lower;
                }
                return null;
            }

    }




