package com.example.awesomefat.bst_csc300_spring2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BST tree = new BST();
        String vals = "fgbbb";
        for(int i = 0; i < vals.length(); i++)
        {
            tree.add(vals.charAt(i));
        }

        System.out.println("Out of balance: " + tree.isOutOfBalance());
        tree.howAreWeOutOfBalance('b');

        if(tree.isOutOfBalance())
        {
            tree.rebalanceTree(BSTCore.outOfBalanceInitial, BSTCore.outOfBalanceSecondarily);
        }

        System.out.println("Out of balance: " + tree.isOutOfBalance());




        //tree.visitPreOrder();
        //tree.visitPostOrder();
        //tree.visitInOrder();
        //tree.visitLevelOrder();
    }
}
