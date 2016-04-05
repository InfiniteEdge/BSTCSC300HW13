package com.example.awesomefat.bst_csc300_spring2016;

import java.util.Stack;

/**
 * Created by awesomefat on 3/3/16.
 */
public class BST
{
    private BinaryTree root;



    public BST()
    {
        this.root = null;
    }

    public boolean isOutOfBalance()
    {
        //boolean-exp?true-stmt:false-stmt
        return this.root.isOutOfBalance();
    }

    public void howAreWeOutOfBalance(char val)
    {
        //where are we out of balance initially? left or right?
        BSTCore.outOfBalanceInitial = "right";
        if(val <= this.root.getPayload())
        {
            BSTCore.outOfBalanceInitial = "left";
        }

        //where are we out of balance secondarily? left or right?
        BSTCore.outOfBalanceSecondarily = this.root.outOfBalanceSecondarily(val, "DEFAULT TURN");

        //Finaly print out how we are out of balance
        System.out.println("Out of balance: " + BSTCore.outOfBalanceInitial + " - " + BSTCore.outOfBalanceSecondarily);
    }

    public void rebalanceTree(String outOfBalanceInitial, String OutOfBalanceSecondarily)
    {
        //preserves our root node in a separate BST object
        BSTCore.tempTree = this.root;
        if(BSTCore.outOfBalanceInitial == "right" && BSTCore.outOfBalanceSecondarily == "right")
        {
            this.root = this.root.getRightTree();
            this.root.setLeftTree(BSTCore.tempTree);
        }
        if(BSTCore.outOfBalanceInitial == "left" && BSTCore.outOfBalanceSecondarily == "left")
        {
            //sets 'b' (in this specific example) as the new root node.
            this.root = this.root.getLeftTree();

            //sets f and g (in this specific example) as the new right tree to the 'b' root node.
            this.root.setRightTree(BSTCore.tempTree);


            System.out.println(this.root.getPayload());
            System.out.println(this.root.getLeftTree().getPayload());
            System.out.println(this.root.getLeftTree().getLeftTree().getPayload());
            System.out.println(this.root.getRightTree().getPayload());
            System.out.println(this.root.getRightTree().getRightTree().getPayload());

        }


    }

    public void add(char payload)
    {
        if(this.root == null)
        {
            this.root = new BinaryTree(payload);
        }
        else
        {
            //we need to add to the tree
            this.root.add(payload);
        }
    }

    public void visitLevelOrder()
    {
        Stack<BinaryTree> answerStack = new Stack<BinaryTree>();
        Stack<BinaryTree> contentStack = new Stack<BinaryTree>();
        Stack<BinaryTree> childStack = new Stack<BinaryTree>();
        Stack<BinaryTree> tempStack = new Stack<BinaryTree>();

        if(this.root == null)
        {
            System.out.println("Empty Tree");
        }
        else
        {
            childStack.push(this.root);
            while(!childStack.isEmpty() || !tempStack.isEmpty())
            {
                //clear child stack by adding its children to tempStack and then popping
                while(!childStack.isEmpty())
                {
                    //push the left and right children of peek childStack onto tempStack
                    if(childStack.peek().getLeftTree() != null)
                    {
                        tempStack.push(childStack.peek().getLeftTree());
                    }
                    if(childStack.peek().getRightTree() != null)
                    {
                        tempStack.push(childStack.peek().getRightTree());
                    }

                    //pop from childStack and push onto contentStack
                    contentStack.push(childStack.pop());
                }

                //move contents of tempStack onto childStack in REVERSE
                while(!tempStack.isEmpty())
                {
                    //pop -> push onto childStack
                    childStack.push(tempStack.pop());
                }
            }

            //we now know that child and temp stacks are empty and all of our
            //nodes are in contentStack in REVERSE order, so we need to reverse them again
            while(!contentStack.isEmpty())
            {
                //pop -> push onto answerStack
                answerStack.push(contentStack.pop());
            }

            //our final answer is answerStack, we can visit the nodes in pop order
            String answer = "";
            while(!answerStack.isEmpty())
            {
                //pop and display value
                answer += answerStack.pop().getPayload() + "\t";
            }
            System.out.println(answer);
        }
    }

    public void visitPreOrder()
    {
        if(this.root == null)
        {
            System.out.println("Empty Tree");
        }
        else
        {
            System.out.println("Pre-Order: " + this.root.displayPreOrder());
        }
    }

    public void visitPostOrder()
    {
        if(this.root == null)
        {
            System.out.println("Empty Tree");
        }
        else
        {
            System.out.println("Post-Order: " + this.root.displayPostOrder());
        }
    }
    public void visitInOrder()
    {
        if(this.root == null)
        {
            System.out.println("Empty Tree");
        }
        else
        {
            System.out.println("In-Order: " + this.root.displayInOrder());
        }
    }
}
