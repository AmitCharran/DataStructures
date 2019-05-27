package AVLTree;

import BinaryTree.BinNode;


public class AVLNode<K extends Comparable<K>> extends BinNode<K> {
    public int leftChildHt, rightChildHt;

    //Just a simple max function... can just use Math.max instead...
    public static int max(int x, int y){
        if(x > y){
            return x;}
        return y;
    }


    //EveryNode that has been created or changed has this method called on it
    //This method is not recursive and does not go through the tree from top to bottom
    //or even go through every single node
    //It trusts that leftChildHt and rightChildHt is correct
    //That way this method is time complexity O(1)
    public int fixHt(){
        leftChildHt = rightChildHt = -1;
        AVLNode<K> l = (AVLNode<K>) getLeft();
        AVLNode<K> r = (AVLNode<K>) getRight();

        if(l != null){
            leftChildHt = 1 + max(l.leftChildHt,l.rightChildHt);
        }
        if(r != null){
            rightChildHt = 1 + max(r.leftChildHt, r.rightChildHt);
        }
        if(leftChildHt > rightChildHt){
            return leftChildHt;
        }
        return rightChildHt;
    }

    public AVLNode(AVLNode<K> parent, AVLNode<K> leftChild, AVLNode<K> rightChild, K d){
        super(parent, leftChild, rightChild, d);
        fixHt();
    }


}
