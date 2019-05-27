package AVLTree;

import BinaryTree.BinTree;
import BinaryTree.BinaryNode;
import MapAndSetCode.Set;
import Tree.TNode;

import java.util.ArrayList;
import java.util.Iterator;

public class AVLTree<K extends Comparable<K>> extends BinTree<K> implements Set<K> {
    public AVLTree(){
        super();
    }
    // new add and remove method are similar to those fot BESTs except
    // 1. they only ever add AVLNodes(AVLNodes have fixHt methods)
    // 2. they finish by rebalancing at the node that was changed

    public void addRoot(K data) throws Exception{
        if(root != null){
            throw new Exception("The Tree is not Empty");
        }
        root = new AVLNode<K>(null,null,null,data);
        size++;
    }

    //This method should be private, b/c it should only be a utility function
    //for when the user adds to the Tree
    public void addLeft(AVLNode<K> node, K data) throws Exception{
        if(node.getLeft() != null){
            throw new Exception("Attempt to replace existing node.");
        }
        //Like the method says, only adds AVLNodes. When you add AVLNodes, the height is fixed
        //set the left of the node given(so its left child) to a new AVLNode given the data
        //That new AVLNode's parent will be the node given in the paramenter
        node.setLeft(new AVLNode<K>(node,null,null,data));
        size++;
        rebalance(node);
    }

    public void addRight(AVLNode<K> node, K data) throws Exception{
        //checks if the node already exists
        if(node.getRight() != null){
            throw new Exception("Attempt to replace existing node.");
        }
        //set the right of the node given(so its right child) to a new AVLNode given the data
        //That new AVLNode's parent will be the node given in the paramenter
        node.setRight(new AVLNode<K>(node,null,null,data));
        size++;
        //rebalance with the node given
        rebalance(node);
    }

    public void remove(K data) throws Exception{
        AVLNode<K> target = findNode(data);

        if(target == null || !target.getData().equals(data)){
            throw new Exception("Target data not found");
        }
        //remove Node returns parent
        AVLNode<K> parent = (AVLNode<K>) removeNode(target);
        rebalance(parent);
    }

    ////////////////////
    ///The Most Important Method... Rebalancing

        //// decide whether and which rotation can fix balance at node and then
            //recursively rebalance parent if needed

        public void rebalance(AVLNode<K> node){
            if(node == null) return;
            int oldMaxHt = AVLNode.max(node.leftChildHt, node.rightChildHt);
            int newMaxHt= node.fixHt();

            //if condition is true.. rotation required
            if(newMaxHt > node.leftChildHt + 1 || newMaxHt > node.rightChildHt + 1){

                // Code gets weird here
                // get an unchecked cast warning... don't know why...
                //warning is yellow lines like duplicate code warning
                // line 89-90 needs to be different so I don't get warnings
                @SuppressWarnings("unchecked")
                //node's children
                AVLNode<K> leftChild = (AVLNode<K>) node.getLeft();
                @SuppressWarnings("unchecked")
                AVLNode<K> rightChild = (AVLNode<K>) node.getRight();


                //node's left child children
                @SuppressWarnings("unchecked")
                AVLNode<K> leftChildLC = (AVLNode<K>) leftChild.getLeft();
                AVLNode<K> leftChildRC =(AVLNode<K>) leftChild.getRight();

                //node's left child left child children
                AVLNode<K> leftChildLCLC = (AVLNode<K>) leftChild.getLeft().getLeft();
                AVLNode<K> leftChildLCRC = (AVLNode<K>) leftChild.getLeft().getRight();

                //node's left child right child children
                AVLNode<K> leftChildRCLC = (AVLNode<K>) leftChildRC.getLeft();
                AVLNode<K> leftChildRCRC = (AVLNode<K>) leftChildRC.getRight();



                //node's right child children
                @SuppressWarnings("unchecked")
                AVLNode<K> rightChildLC = (AVLNode<K>) rightChild.getLeft();
                AVLNode<K> rightChildRC=(AVLNode<K>) rightChild.getRight();

                //node's right child left child children
                AVLNode<K> rightChildLCLC = (AVLNode<K>) rightChildLC.getLeft();
                AVLNode<K> rightChildLCRC = (AVLNode<K>) rightChildLC.getRight();

                //node's right child right child children
                AVLNode<K> rightChildRCLC = (AVLNode<K>) rightChildRC.getLeft();
                AVLNode<K> rightChildRCRC = (AVLNode<K>) rightChildRC.getRight();




                ///The 4 conditions and rotations///////
                //Single Rotate Left
                if(node.leftChildHt > node.rightChildHt && leftChild.leftChildHt
                        >= leftChild.rightChildHt) { // single rotate left
                        rebuildNode(node, leftChild,leftChildLC,node,
                                leftChildLCLC,leftChildLCRC,
                                leftChildRC, rightChild);
                }
                //Double Left Rotate
                else if(node.leftChildHt > node.rightChildHt){
                    rebuildNode(node,leftChildRC,leftChild,node,
                            leftChildLC,leftChildRCLC,
                            leftChildRCRC,rightChild);
                }
                //Single Rotate Right
                else if(rightChild.rightChildHt >= rightChild.leftChildHt){
                    rebuildNode(node,rightChild,node, leftChildRC,
                            leftChild,rightChildLC,
                            leftChildRCLC,rightChildRCRC);
                }
                //Double Rotate Right
                else{
                    rebuildNode(node,rightChildLC,node,rightChild,
                            leftChild,rightChildLCLC,rightChildLCRC,
                            rightChildRC);

                }

                newMaxHt = node.fixHt();

                //This travels up to parent to rebuildNode
                if(oldMaxHt != newMaxHt) rebalance((AVLNode<K>)node.getParent());
            }

            //rebuildNode methods is rebuilt so that it puts the nodes in certain parameter
            // to the left, right ,top... and that's how the Nodes are rotated

            //there is always 4 subtrees to work with, almost 100%(I don't know of a case where it's not)
            //of the time >= 2 will be null
            //but that doesn't matter

            //place them in the order subtree1 - 4 (in, levelOrder Traversal)
            //with this condition subtree1 will always be the smallest since it is going
            //by AVLTree structure
            //subtree4 is always largest.... (or null)

            //this function does both double and single rotation

        }

    private void rebuildNode(AVLNode<K> node, AVLNode<K> putTop, AVLNode<K> putLeft,
                            AVLNode<K> putRight, AVLNode<K> subTree1, AVLNode<K>
                                    subTree2, AVLNode<K> subTree3, AVLNode<K> subTree4) {

            //creates a node and gives it the subtrees
            //notice it is a left node and it has subTree 1 and 2. subTree 1 and 2 and the lowest values
            //lower values usually go to the left side in BST (AVL uses  BST except it rebalances)
        AVLNode<K> newLeft = new AVLNode<K>(node, subTree1,subTree2,putLeft.getData());
            //Sames comment as above except right has larger values in nodes
        AVLNode<K> newRight = new AVLNode<K>(node, subTree3,subTree4, putRight.getData());
                            //Set subTrees to new parent if it is not null
            if(subTree1 != null) subTree1.setParent(newLeft);
            if(subTree2 != null) subTree2.setParent(newLeft);
            if(subTree3 != null) subTree3.setParent(newRight);
            if(subTree4 != null) subTree4.setParent(newRight);

            newLeft.fixHt(); //fix height if needed
            newRight.fixHt();

            //now just set the rotations
            node.setData(putTop.getData());
            node.setLeft(newLeft);
            node.setRight(newRight);
    }
    public AVLNode<K> findNode(K target){
        if(root() == null){
            return null;
        }
        return recursiveFindNode((AVLNode<K>) root(), target);
    }
    protected AVLNode<K> recursiveFindNode(AVLNode<K> node, K target){
        int comparison = target.compareTo(node.getData()); //this is like true or false except it 1,0,-1

        if(comparison == 0){ return node;} //in this tree there are no duplicate codes;
        //return the node it is in

        //Edge Case check if other node is not null, b/c if it is comparison = -1
        if(comparison < 0 && node.getLeft() != null){
            //if this(target) is smaller than node.getData then go left(b/c it will have to be on the left)
            return recursiveFindNode((AVLNode<K>) node.getLeft(), target);
        }

        if(comparison > 0 && node.getRight() != null){
            //if this(target) is greater than node.getData it goes right of that node
            return recursiveFindNode((AVLNode<K>) node.getRight(), target);
        }

        //This confuses me b/c if it is not in the tree and it cannot be found, this code will still return a node
        return node;
        //with contains function if !node.getData().equals(target), then return false
        //This is probably one of those functions that need to be private
        //it's helpful to the code in this way but it doesn't make sense if you take this function out of this code and look at it by itself
        //because it returns a node even if it is not found
    }

    public void add(K newData) throws Exception{
        AVLNode<K> node = findNode(newData);
        //if node == null then there is no Tree
        if(node == null){
            addRoot(newData);
        }
        else if(node.getData().compareTo(newData) > 0){
            addLeft(node,newData);
        }
        else if(node.getData().compareTo(newData) < 0){
            addRight(node,newData);
        }
        else{
            //This basically does nothing. This node value is equal to new Data
            //Only limitation I can think of is if there is max size to the tree
            //then it will replace a node with a new value
            node.setData(newData);
        }
    }

    public boolean contains(K target){
        AVLNode<K> node = findNode(target);
        if(node == null || !node.getData().equals(target)){
            return false;
        }
        return true;
    }

    public boolean isEmpty(){
        return root == null;
    }



    public K get(K partialData){
        //partialData is what user is searching for
        //it looks stupid but in the Key, Value (Map) set up, the user will give a Key
        //it will be converted to "partialData" and then return it only if it is in the code
        AVLNode<K> node = findNode(partialData);
        if(node == null || !node.getData().equals(partialData)){
            return null;
        }
        return node.getData();
    }

    public Iterator<K> iterator(){
        //use inOrder function in TNode class
       ArrayList<TNode> list = inOrder();
       ArrayList<K> dataList = new ArrayList<K>();
       for(TNode node: list){
           dataList.add(((AVLNode<K>)node).getData());
       }
       return dataList.iterator();
    }

    public void dumpData(){
        System.out.println(treePrint(null));
    }

    //When removeing from a BST or AVLTree, we can only promote from a neighbor in order
    //Also other people usually call this method successor
    @Override
protected BinaryNode descendant(BinaryNode node){
        BinaryNode lower = node.getLeft();

        //This would be the number closest to it in value b/c BST structure

        ///Go down left then right to get value closet to it
        //if no left
        //go down right then left to get the value closest to it

        if(lower != null){
            //if there is a left child
            while(lower.getRight() != null){
                lower = lower.getRight();
            }
            return lower;
        }

        lower = node.getRight();
        if(lower != null){
            while(lower.getLeft()!= null){
                lower = lower.getRight();
            }
        }
        return null;
    }

}
